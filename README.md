#### Blueprint 

| PlaylistController                              | PlaylistService                            | PlaylistRepository |
|-------------------------------------------------|--------------------------------------------|--------------------|
| createPlaylist(playListName) (name - required)  | Playlist createPlaylist(playListName)      | save               |
| addSong to Playlist (playlistId, songName)      | updatePlaylist  -> validate if song exists | save               |
| deleteSong from playlist (playlistId, songName) | updatePlaylist -> validate if song exists  | save               |
									

- Open Question : can two playlists have same name ? 
 - Allow duplicate name, and identify uniquely from id
 
 
 ResponseBody : 
 
 ```json
 {
 	"data": {
 	"playlist":{
	 		"id": ""		
	 		"songs" :[
	 			"songNames","songNames"
	 		]
 		}
 	},
 	"message":"string"
 }
 
 ```
 