#### Blueprint 

PlaylistController                    PlaylistService         PlaylistRepository

createPlaylist                     Playlist createPlaylist(playListName)            save
(name - required)

addSong to Playlist                    updatePlaylist          save
(playlistId, songName)

deleteSong from playlist			   updatePlaylist          save
(playlistId, songName)

									validate if song exists
									

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
 