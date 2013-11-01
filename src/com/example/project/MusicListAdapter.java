package com.example.project;
import imageLoader.ImageLoader;

import java.util.ArrayList;

import videos.Video;

import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;


public class MusicListAdapter extends BaseAdapter{
	private Context context;
	private Activity activity;
	private ArrayList<Video> items;
	private LayoutInflater inflater;
	private static final String TAG= "lalala";
	
	public MusicListAdapter (Activity activity, ArrayList<Video> items2){
		Log.d(TAG,"CREATE ADAPTER");
		this.activity=activity;
		this.items=items2;
		this.context = context;
		this.inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	static class Item {
		TextView play;
		TextView text;
		TextView time;
		TextView views;
		ImageView image;
		TextView author;
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent) { 
		Item item;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.musiclist_item, null);
			item = new Item();
			item.text = (TextView)convertView.findViewById(R.id.musicName);
			item.text.setSelected(true);
			item.time = (TextView)convertView.findViewById(R.id.musicTime);
			item.views = (TextView)convertView.findViewById(R.id.musicViews);
			item.author = (TextView)convertView.findViewById(R.id.musicAuthor);
			item.image = (ImageView)convertView.findViewById(R.id.musicImage);
			convertView.setTag(item); 
		} else { 
			item = (Item)convertView.getTag(); 
		}
			item.text.setText(items.get(position).getTitle());
			int time = Integer.parseInt(items.get(position).getDuration());
			if(time < 60){
				item.time.setText("0:" + Integer.toString(time));
			}else{
				int minutes = (int)time/60;
				int sec = time - minutes*60;
				if(sec < 10){
				item.time.setText(Integer.toString(minutes) + ":0" + Integer.toString(sec));
				}else{
				item.time.setText(Integer.toString(minutes) + ":" + Integer.toString(sec));
				}
			}
			item.views.setText(items.get(position).getnViews()+ " Views");
			item.author.setText(items.get(position).getAuthor());
			ImageLoader il = new ImageLoader(context);
			il.DisplayImage(items.get(position).getThumbnail(), item.image);
			
			for (int i = 0; i < items.size(); i++) {
				Log.d("id playlist", items.get(i).getAuthor());
				Log.d("id title", items.get(i).getTitle());
				Log.d("id thumbnail", items.get(i).getThumbnail());
				Log.d("id num videos",items.get(i).getnViews());
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