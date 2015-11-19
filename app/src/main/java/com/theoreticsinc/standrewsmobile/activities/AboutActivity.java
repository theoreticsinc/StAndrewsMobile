package com.theoreticsinc.standrewsmobile.activities;

import com.theoreticsinc.standrewsmobile.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class AboutActivity extends Activity {

	PopupWindow popUp;
	PopupWindow popUp2;
	LinearLayout layout;
	boolean click = true;
	ViewGroup.LayoutParams params;
	TextView tv;
	AdView mAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aboutus);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
		wm.getDefaultDisplay().getMetrics(displayMetrics);
		final int w = displayMetrics.widthPixels;
		final int h = displayMetrics.heightPixels;

		layout = new LinearLayout(this);
		tv = new TextView(this);
		popUp = new PopupWindow(this);
		popUp2 = new PopupWindow(this);
		TextView vs = (TextView) findViewById(R.id.vs);
		vs.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (click) {
					popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
					popUp.update(0, 0, w, h - 20);
					click = false;
				} else {
					popUp.dismiss();
					click = true;
				}
			}

		});
		TextView vs1 = (TextView) findViewById(R.id.vs1);
		vs1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (click) {
					popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
					popUp.update(0, 0, w, h - 20);
					click = false;
				} else {
					popUp.dismiss();
					click = true;
				}
			}

		});
		TextView ms = (TextView) findViewById(R.id.ms);
		ms.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (click) {
					popUp2.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
					popUp2.update(0, 0, w, h - 20);
					click = false;
				} else {
					popUp2.dismiss();
					click = true;
				}
			}

		});
		/*
		params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		tv.setPadding(5, 0, 5, 0);
		tv.setTextColor(Color.WHITE);
		tv.setText(R.string.vision);
		layout.addView(tv, params);*/

		LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.visionstatement, null);
		ImageView back = (ImageView) layout.findViewById(R.id.backButton);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popUp.dismiss();
			}
		});
		popUp.setContentView(layout);
		View layout2 = layoutInflater.inflate(R.layout.missionstatement, null);
		ImageView back2 = (ImageView) layout2.findViewById(R.id.backButton);
		back2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				popUp2.dismiss();
			}
		});
		popUp2.setContentView(layout2);

		final HorizontalScrollView mainScrollView = (HorizontalScrollView) findViewById(R.id.mainScrollView);
		mainScrollView.smoothScrollTo(0,0);
		ImageView clubButton = (ImageView) findViewById(R.id.clubButton);
		//clubButton.getLayoutParams().width = w;
		clubButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("Club Clicked" + w);
				mainScrollView.smoothScrollTo(1000, 0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
