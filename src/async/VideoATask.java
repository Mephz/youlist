package async;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.eclipsesource.restHttp.RestHttpClient;
import com.example.project.R;

import videos.Playlist;
import videos.Video;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class VideoATask extends AsyncTask<String, Void, ArrayList<Video>>{

	public OnAppRequest request;
	public Activity activity;
	ArrayList<Video> results = new ArrayList<Video>();
	
	public VideoATask(OnAppRequest request, Activity activity){
		super();
		this.request = (OnAppRequest) activity;
		this.activity = activity;
	}
	

	@Override
	protected ArrayList<Video> doInBackground(String... arg0) {
		Log.d("Did I get In?", "Yes");
		String json = RestHttpClient.getRequest(arg0[0], null);
		Log.d("Try Catch","Going to Try");
		try {
		Log.d("Try Catch","Inside Try");
		JSONObject jobj = new JSONObject(json);
		JSONObject test = jobj.getJSONObject("feed");
		JSONArray feed = test.getJSONArray("entry");
		

		
		for (int i = 0; i < feed.length(); i++) {
			//gets music title
			Log.d("Arraylist Cicle","Getting Items");
			JSONObject tempItem = feed.getJSONObject(i);
			JSONObject videoTitle = tempItem.getJSONObject("title");
			
			String title = videoTitle.getString("$t");
			Log.d("Title",title);
			
			// gets playlists thumbnails
			JSONObject playlistThumbnail = tempItem.getJSONObject("media$group");
			JSONArray mediaThumbnail = playlistThumbnail.getJSONArray("media$thumbnail");
			JSONObject thumbnail = mediaThumbnail.getJSONObject(1);
			String image = thumbnail.getString("url");
			Log.d("Title",image);
			
			// get video author name
			
			JSONArray videoOwner = tempItem.getJSONArray("author");
			JSONObject tempName = videoOwner.getJSONObject(0);
			JSONObject authorName = tempName.getJSONObject("name");
			String author = authorName.getString("$t");
			Log.d("Title",author);
			
			//get video time
			JSONObject videoDuration = tempItem.getJSONObject("media$group");
			JSONObject videoTime = videoDuration.getJSONObject("yt$duration");
			String duration = videoTime.getString("seconds");
			
			//get video
			JSONObject videoIdentifier = tempItem.getJSONObject("media$group");
			JSONObject tempIdentifer = videoIdentifier.getJSONObject("yt$videoid");
			String videoID = tempIdentifer.getString("$t");
			
			
			//get video views
			
			JSONObject videoViews = tempItem.getJSONObject("yt$statistics");
			String nViews = videoViews.getString("viewCount");
			Video tempVideo = new Video(videoID,title, author, duration, nViews, image);
			results.add(tempVideo);	
			
		}
		
		}catch (JSONException e) {
			e.printStackTrace();
		}
		Log.d("Returning Results", Boolean.toString(results.isEmpty()));
		return results;
	}
	protected void onPreExecute() {
		
	}
	
	protected void onPostExecute(ArrayList<Video> results) {
		Log.d("Post Execute List", "I'm in");
		ProgressBar pb = (ProgressBar)activity.findViewById(R.id.progressbarmusic);
		pb.setVisibility(View.GONE);
		this.request.onMusicTaskCompleted(results);
	}
}	


