package com.musicshare.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.musicshare.entity.Playlist;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayListResponse {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Playlist data;
}
