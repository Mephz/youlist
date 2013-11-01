package com.example.project;
import imageLoader.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import videos.*;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;


public class PlaylistAdapter extends BaseAdapter{
	
	private Activity activity;
	private ArrayList<Playlist> items;
	private LayoutInflater inflater;

	

	 public static Bitmap getBitmapFromURL(String src) {
	        try {
	            Log.e("src",src);
	            URL url = new URL(src);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoInput(true);
	            connection.connect();
	            InputStream input = connection.getInputStream();
	            Bitmap myBitmap = BitmapFactory.decodeStream(input);
	            Log.e("Bitmap","returned");
	            return myBitmap;
	        } catch (IOException e) {
	            e.printStackTrace();
	            Log.e("Exception",e.getMessage());
	            return null;
	        }
	    }
	
	
	
	public PlaylistAdapter (Activity activity, ArrayList<Playlist> items2){
		
		this.activity=activity;
		this.items=items2;
		this.inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	static class Item { 
		TextView playlistName;
		TextView videoCount;
		LinearLayout imageUrl;
	}
	
	@SuppressWarnings("deprecation")
	public View getView(int position, View convertView, ViewGroup parent) { 
		Item item;
		if (convertView == null) { 
			convertView = inflater.inflate(R.layout.playlist_item, null); 
			item = new Item();
			item.playlistName = (TextView)convertView.findViewById(R.id.playlistName);
			item.videoCount = (TextView)convertView.findViewById(R.id.playlistCount);
			item.imageUrl = (LinearLayout)convertView.findViewById(R.id.playlistBackground);

			
			
			
			convertView.setTag(item); 
		} else { 
			item = (Item)convertView.getTag(); 
		}
			
			item.playlistName.setText(items.get(position).getTitle());
			item.videoCount.setText(Integer.toString(items.get(position).getVideoCount()) +" Videos");
			
			Bitmap bimage =  getBitmapFromURL((items.get(position).getThumbnailURL()));
			@SuppressWarnings("deprecation")
			BitmapDrawable bg = new BitmapDrawable(bimage);
			item.imageUrl.setBackgroundDrawable(bg);
			
			for (int i = 0; i < items.size(); i++) {
				Log.d("id playlist", items.get(i).getId());
				Log.d("id title", items.get(i).getTitle());
				Log.d("id thumbnail", items.get(i).getThumbnailURL());
				Log.d("id num videos", Integer.toString(items.get(i).getVideoCount()));
			}
			
			
		return convertView; 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int index) {
		items.get(index);
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	} 
	
	
}