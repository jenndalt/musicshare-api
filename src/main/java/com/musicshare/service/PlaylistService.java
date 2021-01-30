package com.musicshare.service;

import org.springframework.stereotype.Service;

import com.musicshare.entity.Playlist;
import com.musicshare.entity.Song;
import com.musicshare.exception.SongNotFoundException;
import com.musicshare.repository.PlaylistRepository;
import com.musicshare.repository.SongRepository;

@Service
public class PlaylistService {

	private PlaylistRepository playlistRepository;
	private SongRepository songRepository;

	public PlaylistService(PlaylistRepository playlistRepository, SongRepository songRepository) {
		this.playlistRepository = playlistRepository;
		this.songRepository = songRepository;
	}

	public Playlist createPlaylist(String playlistName) {
		Playlist playlist = new Playlist(playlistName);
		return playlistRepository.save(playlist);
	}

	public Playlist addSongToPlaylist(Long id, String name) throws Exception {
		Playlist existingPlaylist = playlistRepository.findById(id).get();
		validateIfSongExists(name);
		existingPlaylist.getSongs().add(new Song(name));
		return playlistRepository.save(existingPlaylist);
	}

	public Playlist deleteSongFromPlaylist(long id, String name) {
		Playlist existingPlaylist = playlistRepository.findById(id).get();
		existingPlaylist.getSongs().remove(new Song(name));
		return playlistRepository.save(existingPlaylist);
	}
	
	private void validateIfSongExists(String songName) throws SongNotFoundException {
		songRepository.findById(songName).orElseThrow(()->new SongNotFoundException());
	}

}
