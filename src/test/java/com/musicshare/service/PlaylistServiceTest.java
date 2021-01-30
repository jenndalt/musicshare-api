package com.musicshare.service;

import com.musicshare.entity.Playlist;
import com.musicshare.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaylistServiceTest {

    PlaylistService playlistService;
    PlaylistRepository playlistRepository;

    @BeforeEach
    public void init(){
        playlistRepository = mock(PlaylistRepository.class);
        playlistService = new PlaylistService(playlistRepository);
    }

    @Test
    public void createPlayList(){
        String playlistName = "MyPlaylist";
        Playlist expectedPlaylist = Playlist.builder().name(playlistName).id(1l).songs(new ArrayList<>()).build();

        when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(expectedPlaylist);
        Playlist actualPlayList = playlistService.createPlaylist(playlistName);

        assertEquals(expectedPlaylist.getName(), actualPlayList.getName());
        assertEquals(expectedPlaylist.getId(), actualPlayList.getId());
        assertTrue(actualPlayList.getSongs().isEmpty());

    }



}