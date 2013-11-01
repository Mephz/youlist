package com.example.project;

import org.apache.http.client.UserTokenHandler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends Activity {

	private static final String TAG = "New Method";
	protected static final int TYPE_HANDLE = 0;
	private static Context context;
	private static Activity activity;
	EditText mEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    protected void onStart(){
    	super.onStart();
    }
    protected void onDestroy(){
    	super.onDestroy();
    }
    protected void onResume(){
    	super.onResume();
    	
    	Button Button1 = (Button) findViewById(R.id.appLogo);
    	mEdit = (EditText)findViewById(R.id.getUserName);
    	Button1.setOnClickListener(onClickListener);
    	context = getApplicationContext();
    	activity = this;
    }
    
    OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			NetworkActivity checkConnect = new NetworkActivity();
			String state = checkConnect.isConnected(context);
			String userName = mEdit.getText().toString().trim();
			if (state.equals("Wifi") || state.equals("Mobile")){
				if (userName.isEmpty() == true){
					Toast.makeText(context, "Insert a Youtube Username", Toast.LENGTH_SHORT).show();
				}else{
				Intent intent = new Intent(getApplicationContext(),PlaylistActivity.class);
				intent.putExtra("Username", mEdit.getText().toString());
				startActivity(intent);
				}
			}else{
				Toast.makeText(context, "TURN ON UR INTERNET SHITHEAD", Toast.LENGTH_SHORT).show();
			}
			

		}
	};
	


}