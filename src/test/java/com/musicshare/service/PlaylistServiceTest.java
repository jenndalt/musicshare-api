package com.musicshare.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.musicshare.entity.Playlist;
import com.musicshare.entity.Song;
import com.musicshare.exception.SongNotFoundException;
import com.musicshare.repository.PlaylistRepository;
import com.musicshare.repository.SongRepository;

class PlaylistServiceTest {

	PlaylistService playlistService;
	PlaylistRepository playlistRepository;
	SongRepository songRepository;

	@BeforeEach
	public void init() {
		playlistRepository = mock(PlaylistRepository.class);
		songRepository = mock(SongRepository.class);
		playlistService = new PlaylistService(playlistRepository,songRepository);
	}

	@Test
	public void createPlayList() {
		String playlistName = "MyPlaylist";
		Playlist expectedPlaylist = Playlist.builder().name(playlistName).id(1l).songs(new ArrayList<>()).build();

		when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(expectedPlaylist);
		Playlist actualPlayList = playlistService.createPlaylist(playlistName);

		assertEquals(expectedPlaylist.getName(), actualPlayList.getName());
		assertEquals(expectedPlaylist.getId(), actualPlayList.getId());
		assertTrue(actualPlayList.getSongs().isEmpty());

	}

	@Test
	public void addSongsToPlaylist() throws Exception {
		String playlistName = "MyPlaylist";
		List<Song> songList = new ArrayList<>();
		songList.add(new Song("Toxic"));
		Playlist expectedPlaylist = Playlist.builder().name(playlistName).id(1l).songs(songList).build();		
		
		when(playlistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedPlaylist));
		
		when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(expectedPlaylist);		
		Playlist actualPlaylist = playlistService.addSongToPlaylist(1l, "Toxic");
		
		assertEquals(expectedPlaylist.getName(), actualPlaylist.getName());
		assertEquals(expectedPlaylist.getId(), actualPlaylist.getId());
		assertFalse(actualPlaylist.getSongs().isEmpty());
		assertEquals("Toxic",actualPlaylist.getSongs().get(0).getName());
	}

	@Test
	public void deleteSongFromPlaylist() throws Exception {
		String playlistName = "MyPlaylist";
		List<Song> songList = new ArrayList<>();
		songList.add(new Song("Toxic"));
		Playlist expectedPlaylist = Playlist.builder().name(playlistName).id(1l).songs(songList).build();

		when(playlistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedPlaylist));

		when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(expectedPlaylist);
		Playlist actualPlaylist = playlistService.deleteSongFromPlaylist(1l, "Toxic");

		assertEquals(expectedPlaylist.getName(), actualPlaylist.getName());
		assertEquals(expectedPlaylist.getId(), actualPlaylist.getId());
		assertTrue(actualPlaylist.getSongs().isEmpty());
	}
	
	@Test
	public void addSongsToPlaylist_WhenSongIsInValid() throws Exception {
		String playlistName = "MyPlaylist";
		List<Song> songList = new ArrayList<>();
		Playlist expectedPlaylist = Playlist.builder().name(playlistName).id(1l).songs(songList).build();		
		when(songRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		when(playlistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedPlaylist));
		
		
		when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(expectedPlaylist);		
		SongNotFoundException expected =  assertThrows(SongNotFoundException.class,
				() -> playlistService.addSongToPlaylist(1l, "Avengers")); 
		
		assertEquals("Song does not exist", expected.getMessage());

	}
}