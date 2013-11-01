package com.example.project;

import java.util.ArrayList;

import videos.Playlist;
import videos.Video;
import youtube.PlayerViewActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import async.OnAppRequest;
import async.VideoATask;

public class MusicListActivity extends Activity implements OnAppRequest{
	private Context context;
	ArrayList<Video> items =  new ArrayList<Video>();
	private static OnAppRequest request;
	private static Activity activity;
	private String plname1 = null;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		activity = this;
		request = this;
		setContentView(R.layout.activity_music_list);
		VideoATask videoTask = new VideoATask(request, activity);
		Intent myIntent = getIntent();
		plname1 = myIntent.getStringExtra("PlaylistName");
		String url = myIntent.getStringExtra("PlaylistURL");
		videoTask.execute(url);
		Log.d("Testing","Running AsyncTask");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	OnItemClickListener onItemClickListener = new OnItemClickListener(){
		
		public void onItemClick(AdapterView<?> adapter, View view, int pos, long id){
				//Toast.makeText(getApplicationContext(), "Clicked ON:" + items.get(pos).getVideoId(), Toast.LENGTH_SHORT).show();
				Intent mainIntent = new Intent(MusicListActivity.this, PlayerViewActivity.class);
				mainIntent.putExtra("videoID", items.get(pos).getVideoId());
				MusicListActivity.this.startActivity(mainIntent);
		}
	};
	
    protected void onResume(){
    	super.onResume();
    	
    	Button Button1 = (Button) findViewById(R.id.backButton);
    	Button1.setOnClickListener(onClickListener);
    	context = getApplicationContext();
    }
    
    OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		    finish();
		}
	};
	
	
	private void processa_items(ArrayList<Video> items){
		Log.d("TROLL","ON CREATE");
		context = getApplicationContext();
		TextView tx = (TextView) findViewById(R.id.playlistName);
		tx.setText("Now Playing: "+ plname1);
		tx.setSelected(true);
		ListView listView = (ListView) findViewById(R.id.musiclist_view);
		listView.setAdapter(new MusicListAdapter(this,items));
		listView.setOnItemClickListener(onItemClickListener);

	}

	@Override
	public void onMusicTaskCompleted(ArrayList<Video> results) {
		Log.d("Music Task @ Activity", "In");
		items = results;
		Log.d("Processing Items", "In");
		processa_items(items);
	}

	@Override
	public void onAsyncTaskCompleted(ArrayList<Playlist> results) {
		// TODO Auto-generated method stub
		
	}

}

