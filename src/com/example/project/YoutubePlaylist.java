package com.example.project;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import videos.Playlist;

import android.R.integer;
import android.util.Log;

import com.eclipsesource.restHttp.RestHttpClient;

public class YoutubePlaylist {
	
	public ArrayList<Playlist> jsonPlaylistParser(String json) throws Exception {
		
		ArrayList<Playlist> results = new ArrayList<Playlist>();
		
		JSONObject jobj = new JSONObject(json);
		JSONObject test = jobj.getJSONObject("feed");
		JSONArray feed = test.getJSONArray("entry");
		
		
		Log.d("numero de playlists :", feed.length()+"");
		
		
		// gets playlistID to later get videos within this playlist
		for (int i = 0; i < feed.length(); i++) {
			
			// gets playlists IDs 
			JSONObject tempItem = feed.getJSONObject(i);
			JSONObject playlistTemp = tempItem.getJSONObject("yt$playlistId");
			Log.d("debug json statistics", playlistTemp.getString("$t"));
			String playlistID = playlistTemp.getString("$t");
			
			// gets plalists titles
			JSONObject playlistName = tempItem.getJSONObject("title");
			Log.d("playlist name: ", playlistName.getString("$t"));
			String playlistTitle = playlistName.getString("$t");
			
		
			// gets playlists thumbnails
			JSONObject playlistThumbnail = tempItem.getJSONObject("media$group");
			JSONArray mediaThumbnail = playlistThumbnail.getJSONArray("media$thumbnail");
			JSONObject thumbnail = mediaThumbnail.getJSONObject(1);
			
			Log.d("thumbnail url: ", thumbnail.getString("url"));
			String thumbnailUrl = thumbnail.getString("url");
			
			
			// gets playlist item count
			JSONArray gdFeedLink = tempItem.getJSONArray("gd$feedLink");
			JSONObject playlistCount = gdFeedLink.getJSONObject(0);
			
			Log.d("numero de videos: ", playlistCount.getString("countHint"));
			int nVideos = Integer.parseInt(playlistCount.getString("countHint"));
			
			
			Playlist temp = new Playlist(playlistID, playlistTitle, thumbnailUrl, nVideos);
			results.add(temp);
			temp = null;
		}
		
		for (int i = 0; i < results.size(); i++) {
			Log.d("arraylist: ", results.get(i).getId() + " | " + results.get(i).getTitle());
			
		}
		
		return results;
		
	}
	
	
	// returns the jsons in String format
	public String getJson(String url){
		
		String result;
		try {	    	
			result = RestHttpClient.getRequest(url, null);
			Log.d("webservice output: ", "RESULT:" + result);

			
		} catch(Exception e) {
			return null;
		}
		return result;  
	}
        

}
