package com.musicshare.service;

import com.musicshare.entity.Playlist;
import com.musicshare.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    private PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(String playlistName) {
        Playlist playlist = Playlist.builder().name(playlistName).build();
        return playlistRepository.save(playlist);
    }
}
