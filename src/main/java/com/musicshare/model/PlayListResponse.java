package com.musicshare.model;

import com.musicshare.entity.Playlist;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayListResponse {
    private String message;
    private Playlist data;
}
