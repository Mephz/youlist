package youtube;

/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayerView;
import com.example.project.MusicListActivity;
import com.example.project.R;


/**
 * A simple YouTube Android API demo application which shows how to create a simple application that
 * displays a YouTube Video in a {@link YouTubePlayerView}.
 * <p>
 * Note, to use a {@link YouTubePlayerView}, your activity must extend {@link YouTubeBaseActivity}.
 */
public class PlayerViewActivity extends YouTubeFailureRecoveryActivity implements OnFullscreenListener {
   String videoURL="";
   private static final int PORTRAIT_ORIENTATION = Build.VERSION.SDK_INT < 9
	        ? ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
	        : ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
    
   private boolean fullscreen;
	
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.playerview_demo);
    //changeValues();
    Intent myIntent = getIntent();
    videoURL = myIntent.getStringExtra("videoID");
    Log.d("VideoURL",videoURL);
    YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
    youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    
  }
  

  
  @Override
  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
   
   if (!wasRestored) {
	   player.cueVideo(videoURL);
   }
  }

  @Override
  protected YouTubePlayer.Provider getYouTubePlayerProvider() {
	  return (YouTubePlayerView) findViewById(R.id.youtube_view);
  }


	  
	  @Override
	  public void onFullscreen(boolean isFullscreen) {
		  fullscreen = isFullscreen;
	  }
	  

}
