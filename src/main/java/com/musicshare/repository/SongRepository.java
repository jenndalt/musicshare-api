package com.musicshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicshare.entity.Song;

public interface SongRepository extends JpaRepository<Song, String> {
}
