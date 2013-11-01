package async;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eclipsesource.restHttp.RestHttpClient;
import com.example.project.R;
import com.example.project.YoutubePlaylist;

import videos.Playlist;
import videos.Video;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class PlaylistATask extends AsyncTask<String, Void, ArrayList<Playlist>>{
	
	public OnAppRequest request;
	public Activity activity;
	public PlaylistATask(OnAppRequest request,Activity activity) {
		super();
		this.request = (OnAppRequest) activity;
		this.activity = activity;
		// TODO Auto-generated constructor stub
	}
	@Override
	protected ArrayList<Playlist> doInBackground(String... arg0) {
		String json = RestHttpClient.getRequest(arg0[0], null);
		ArrayList<Playlist> results = new ArrayList<Playlist>();
		try {
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
		} catch (JSONException e) {
			
		}
		Log.d("Returning Results", "In");
		return results;
		
		
	}
	
	
	protected void onPreExecute() {
		
	}
	
	protected void onPostExecute(ArrayList<Playlist> results) {
		ProgressBar pb = (ProgressBar)activity.findViewById(R.id.progressbarpl);
		pb.setVisibility(View.GONE);
		this.request.onAsyncTaskCompleted(results);
	}
}
