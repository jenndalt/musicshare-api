package com.musicshare.exception;

public class SongNotFoundException extends Exception {

	public SongNotFoundException() {
		super("Song does not exist");
	}

}
