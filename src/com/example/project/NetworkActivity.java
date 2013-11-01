package com.example.project;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkActivity {

	
	public String isConnected(Context context) {
		
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
				if (activeInfo != null && activeInfo.isConnected()) {
				
				boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
				boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
					if(wifiConnected == true){
						return "Wifi";
					}else if(mobileConnected == true){
						return "Mobile";
						
				}
			}
				return "No Connection";
	}
}