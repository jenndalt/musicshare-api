package com.musicshare.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Song {

    @Id
    private String name;
}
