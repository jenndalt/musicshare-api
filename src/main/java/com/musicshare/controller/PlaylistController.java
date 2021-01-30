package com.musicshare.controller;

import com.musicshare.model.PlayListRequest;
import com.musicshare.model.PlayListResponse;
import com.musicshare.service.PlaylistService;
import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/playlist")
@Api("PlayList")
public class PlaylistController {

    private PlaylistService service;

    public PlaylistController(PlaylistService service) {
        this.service = service;
    }

    @PostMapping(value = {"/{playListName}", "/"})
    public PlayListResponse createPlayList(@PathVariable(value = "playListName", required = false) String playListName) {
        PlayListResponse response = null;
        if (StringUtils.hasText(playListName)) {
            response = PlayListResponse.builder()
                    .message("playlist created successfully.")
                    .data(service.createPlaylist(playListName))
                    .build();
        } else {
            response = PlayListResponse.builder()
                    .message("playlist name is required.")
                    .build();
        }
        return response;
    }

    @PutMapping
    public PlayListResponse addSongToPlaylist(@RequestBody PlayListRequest playListRequest) throws Exception {

        return PlayListResponse.builder()
                .message("songs added to playlist successfully.")
                .data(service.addSongsToPlaylist(playListRequest.getPlayListId(), playListRequest.getSongName()))
                .build();
    }

    @DeleteMapping
    public PlayListResponse deleteSongFromPlaylist(@RequestBody PlayListRequest playListRequest) throws Exception {

        return PlayListResponse.builder()
                .message("a song deleted from playlist successfully.")
                .data(service.removeSongFromPlaylist(playListRequest.getPlayListId(), playListRequest.getSongName()))
                .build();
    }
}
