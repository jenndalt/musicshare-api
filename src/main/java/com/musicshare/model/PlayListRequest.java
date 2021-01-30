package com.musicshare.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayListRequest {

    private long playListId;
    private String songName;
}
