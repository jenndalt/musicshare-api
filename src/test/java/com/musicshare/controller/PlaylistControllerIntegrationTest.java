package com.musicshare.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicshare.entity.Playlist;
import com.musicshare.entity.Song;
import com.musicshare.model.PlayListRequest;
import com.musicshare.repository.PlaylistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlaylistControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaylistRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePlaylist() throws Exception {
        mockMvc.perform(post("/api/v1/playlist/{playListName}", "playList1")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.message").value("playlist created successfully."));

    }
    @Test
    @DisplayName("Add song to a existing playlist - name: playList1")
    public void testAddSongToExistingPlayList() throws Exception {
        List<Song> songList = new ArrayList<>();
        songList.add(Song.builder().name("Toxic").build());

        final Playlist playListAdded = repository.save(Playlist.builder().name("playList1").build());
        mockMvc.perform(put("/api/v1/playlist")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PlayListRequest.builder().playListId(playListAdded.getId()).songName("Toxic").build())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("playList1"))
                .andExpect(jsonPath("$.data.songs.length()").value(1))
                .andExpect(jsonPath("$.data.songs[0].name").value("Toxic"))
                .andExpect(jsonPath("$.message").value("songs added to playlist successfully."));
    }
}