package com.example.project;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.json.JSONException;

import videos.Playlist;
import videos.Video;

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
import async.PlaylistATask;
import async.VideoATask;

public class PlaylistActivity extends Activity implements OnAppRequest{
	
	private Context context;
	private static OnAppRequest request;
	private static Activity activity;
	ArrayList<Playlist> temp;
	String Username;
	int maxResultsPlaylist = 30;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity = this;
		request = this;
		String json = "";
		Intent myIntent = getIntent();
		Username = myIntent.getStringExtra("Username");
		
  		
  		
		PlaylistATask task = new PlaylistATask(request, activity);
		task.execute("https://gdata.youtube.com/feeds/mobile/users/"+Username+"/playlists?max-results="+maxResultsPlaylist+"&alt=json");
	}

	
	public void onAsyncTaskCompleted (ArrayList<Playlist> results){
		temp = results;
		
		processa_items(temp);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	OnItemClickListener onItemClickListener = new OnItemClickListener(){
		
		public void onItemClick(AdapterView<?> adapter, View view, int pos, long id){
				//Toast.makeText(getApplicationContext(), "Clicked ON:" + temp.get(pos).getId(), Toast.LENGTH_SHORT).show();
				   Intent intent = new Intent(getApplicationContext(),MusicListActivity.class);
				   intent.putExtra("PlaylistURL", "https://gdata.youtube.com/feeds/api/playlists/"+temp.get(pos).getId()+"?v=2&alt=json");
				   intent.putExtra("PlaylistName",temp.get(pos).getTitle());
				   startActivity(intent);
		}
	};
	
	
	private void processa_items(ArrayList<Playlist> results){
		
		context = getApplicationContext();
		TextView tx = (TextView) findViewById(R.id.userNameHeader);
		tx.setText(Username + " Playlists");
		tx.setSelected(true);
		ListView listView = (ListView) findViewById(R.id.list_view);
		//listView.addFooterView(getLayoutInflater().inflate(R.layout.footer, null));
		listView.setAdapter(new PlaylistAdapter(this,results));
		listView.setOnItemClickListener(onItemClickListener);
	}

	
	protected void onResume (){
		
		super.onResume();
		context = getApplicationContext();
		request = this;
		activity = this;
	   	Button Button1 = (Button) findViewById(R.id.backButton);
    	Button1.setOnClickListener(onClickListener);
    }
    
    OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		    finish();
		}
	};



	@Override
	public void onMusicTaskCompleted(ArrayList<Video> results) {
		// TODO Auto-generated method stub
		
	}


}
