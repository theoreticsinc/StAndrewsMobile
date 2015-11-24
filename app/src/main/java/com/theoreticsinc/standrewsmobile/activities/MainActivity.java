package com.theoreticsinc.standrewsmobile.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.theoreticsinc.standrewsmobile.R;

public class MainActivity extends Activity {

	private static final int SWIPE_MIN_DISTANCE = 5;
	private static final int SWIPE_THRESHOLD_VELOCITY = 300;
	
	private int mActiveFeature = 0;
	private GestureDetector mGestureDetector;
	private ScrollView sv = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		int w = displayMetrics.widthPixels;
		int h = displayMetrics.heightPixels;
//		DisplayMetrics dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		int h = dm.heightPixels;
//		int w = dm.widthPixels;
		sv = (ScrollView) findViewById(R.id.scrollView1);
		ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
		ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
		LinearLayout ll = (LinearLayout) findViewById(R.id.mainLayout);
		iv1.getLayoutParams().height = h - 70;
		iv2.getLayoutParams().height = 70;
		ll.getLayoutParams().height =  h;
		sv.getLayoutParams().height =  h;
		sv.setOnTouchListener(new View.OnTouchListener() {
 			@Override
 			public boolean onTouch(View v, MotionEvent event) {
 				//If the user swipes
 				System.out.println("If the user scroll");
 				/*if (mGestureDetector.onTouchEvent(event)) {
 					return true;
 				}*/
 				if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL ){
 					//int scrollX = getScrollX();
 					int featureHeight = v.getMeasuredHeight();
 					int featureWidth = v.getMeasuredWidth();
 					//mActiveFeature = ((scrollX + (featureWidth/2))/featureWidth);
 					int scrollTo = mActiveFeature*featureWidth;
 					
 					//sv.smoothScrollTo(0, 100);
 					sv.fullScroll(ScrollView.FOCUS_DOWN);
 					System.out.println("ACTION_UP scroll");
 					return true;
 				}
 				else{
 					return false;
 				}
 			}
		});
		
		ImageView btn1 = (ImageView) findViewById(R.id.btn1);
		ImageView btn2 = (ImageView) findViewById(R.id.btn2);
		ImageView btn3 = (ImageView) findViewById(R.id.btn3);
		ImageView btn4 = (ImageView) findViewById(R.id.btn4);
		ImageView btn5 = (ImageView) findViewById(R.id.btn5);
		ImageView btn6 = (ImageView) findViewById(R.id.btn6);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), AboutActivity.class);
				startActivity(i);
				finish();
			}
		});

		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(), SASLifeActivity.class);
				startActivity(i);
				finish();
				/*try {
					String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
					ParseInstallation.getCurrentInstallation().put("Unique_Id", android_id);
					ParseInstallation.getCurrentInstallation().save();

					ParseUser.enableAutomaticUser();
					ParseACL defaultACL = new ParseACL();
					// Optionally enable public read access.
					defaultACL.setPublicReadAccess(true);
					ParseACL.setDefaultACL(defaultACL, true);

					ParseObject testObject = new ParseObject("TestObject");
					testObject.put("foo", "SchoolApp");
					testObject.save();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}*/
			}
		});

		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), MinistryActivity.class);
				startActivity(i);
				finish();
			}
		});

		//mGestureDetector = new GestureDetector(new MyGestureDetector());

		new BackgroundSave().execute(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return super.onOptionsItemSelected(item);
	}
	
	class MyGestureDetector extends SimpleOnGestureListener {
 		@Override
 		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
 			try {
 				//right to left
 				System.out.println("onFling");
  				if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//int featureHeight = getMeasuredHeight();
					//mActiveFeature = (mActiveFeature < (mItems.size() - 1))? mActiveFeature + 1:mItems.size() -1;
  					System.out.println("onFling right to left");
  					sv.smoothScrollTo(0, 1500);
 					//fullScroll(FOCUS_DOWN);
 					return true;
 				}
   				//left to right
 				else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					//int featureHeight = getMeasuredHeight();
					//mActiveFeature = (mActiveFeature > 0)? mActiveFeature - 1:0;
 					System.out.println("onFling left to right");
					sv.smoothScrollTo(0, 1500);
					//fullScroll(FOCUS_DOWN);
					return true;
				}
			} catch (Exception e) {
			        Log.e("Fling", "There was an error processing the Fling event:" + e.getMessage());
			}
			return true;
		}
	}

	public class BackgroundSave extends AsyncTask {
		public BackgroundSave(){
		}
		@Override
		protected Object doInBackground(Object... arg0) {
			try{
				String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
				ParseInstallation.getCurrentInstallation().put("Unique_Id", android_id);
				ParseInstallation.getCurrentInstallation().saveInBackground();

				ParseUser.enableAutomaticUser();
				ParseACL defaultACL = new ParseACL();
				// Optionally enable public read access.
				// defaultACL.setPublicReadAccess(true);
				ParseACL.setDefaultACL(defaultACL, true);

				ParseObject testObject = new ParseObject("TestObject");
				testObject.put("foo", "SchoolApp");
				testObject.saveInBackground();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle("Really Exit?")
				.setMessage("Are you sure you want to exit?")
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						//DashboardActivity.super.onBackPressed();
						finish();
					}
				}).create().show();
	}
	
}
