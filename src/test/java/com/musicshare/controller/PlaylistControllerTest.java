package com.musicshare.controller;

import com.musicshare.entity.Playlist;
import com.musicshare.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {PlaylistController.class})
class PlaylistControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService service;

    @Test
    public void testCreatePlaylist() throws Exception {
        when(service.createPlaylist(any())).thenReturn(Playlist.builder().name("playList1").id(1l).songs(new ArrayList<>()).build());
        ;
        mockMvc.perform(post("/api/v1/playlist/{playListName}", "playList1")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("playlist created successfully."));
        verify(service, times(1)).createPlaylist(any());
    }
}