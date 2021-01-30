package com.musicshare.controller;

import com.musicshare.entity.Playlist;
import com.musicshare.model.PlayListResponse;
import com.musicshare.service.PlaylistService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/playlist")
@Api("PlayList")
public class PlaylistController {

    private PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping("/{playListName}")
    public PlayListResponse createPlayList(@PathVariable String playListName) {

        PlayListResponse response = PlayListResponse.builder()
                .message("playlist created successfully.")
                .data(service.createPlaylist(playListName))
                .build();
        return response;
    }
}
