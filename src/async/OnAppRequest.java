package async;

import java.util.ArrayList;

import videos.Playlist;
import videos.Video;

public interface OnAppRequest {


		public void onAsyncTaskCompleted(ArrayList<Playlist> results);
		public void onMusicTaskCompleted(ArrayList<Video> results);
		}
