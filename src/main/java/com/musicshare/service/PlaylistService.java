package com.musicshare.service;

import org.springframework.stereotype.Service;

import com.musicshare.entity.Playlist;
import com.musicshare.entity.Song;
import com.musicshare.repository.PlaylistRepository;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(String playlistName) {
        Playlist playlist = new Playlist(playlistName);
        return playlistRepository.save(playlist);
    }

	public Playlist addSongsToPlaylist(Long id, String name) throws Exception {
		Playlist existingPlaylist = playlistRepository.findById(id).get();
		existingPlaylist.getSongs().add(new Song(name));
		return playlistRepository.save(existingPlaylist);		
		
	}

}
