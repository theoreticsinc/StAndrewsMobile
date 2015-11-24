package com.theoreticsinc.standrewsmobile.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.theoreticsinc.standrewsmobile.R;
import com.theoreticsinc.standrewsmobile.utils.GSONParser;
import com.theoreticsinc.standrewsmobile.utils.LazyAdapter;

import java.io.ByteArrayOutputStream;
import java.util.List;

@TargetApi(11)
public class AlertsListFragment extends Fragment {

	ListView listView;
	LazyAdapter adapter;

	private LayoutInflater inflater;

	public AlertsListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		int i = getArguments().getInt("SettingsItem");
		Log.d("LEAGUE CHAT", "SettingsItem:" + i);
		View rootView = null;

		rootView = inflater.inflate(R.layout.alerts_list, container,
				false);
		getActivity().setTitle("Settings");

		ImageButton menuButton = (ImageButton) rootView.findViewById(R.id.menuButton2);
		menuButton.setVisibility(View.GONE);
		ImageView headerLogo = (ImageView) rootView.findViewById(R.id.headerLogo);
		headerLogo.setVisibility(View.GONE);
/*
		GSONParser gsonParser = new GSONParser();

		String configURL = "http://184.95.54.213/schoolapp/configuration.json";
		String defaultAlertsURL = "http://184.95.54.213/schoolapp/alerts.json";
		String alertsURL = "";
		try {
			//Read URL of Alerts List from a Config JSON in a server
			String URL = gsonParser.readConfig("alerts");

			if (null == configURL) {
				//Read Alerts List from Default URL
				alertsURL = defaultAlertsURL;
			}
			else {
				alertsURL = URL;
			}
		}
		catch (Exception ex) {
			Log.e("AlertsListFragment",ex.getMessage());
		}

		//Process the ALERTS GSON
		try {
			gsonParser.processDataFromGSON(alertsURL, rootView.getContext());

			List<String> mStrings = new ArrayList<String>();
			mStrings.add("https://pbs.twimg.com/profile_images/1306095935/androidcoo_normal.png");
			mStrings.add("https://pbs.twimg.com/profile_images/2938108229/399ba333772228bfbb40134018fbe777_normal.jpeg");
			mStrings.add("https://pbs.twimg.com/profile_images/1701796334/TA-New-Logo_normal.jpg");
			mStrings.add("https://pbs.twimg.com/profile_images/1417650153/android-hug_normal.png");
//        mStrings.add("https://pbs.twimg.com/profile_images/1517737798/aam-twitter-right-final_normal.png");
//        mStrings.add("https://pbs.twimg.com/profile_images/3319660679/70e7025a05b674852b9f3cea0998259c_normal.jpeg");
//        mStrings.add("https://pbs.twimg.com/profile_images/487047133392949248/sVTI9rGI_normal.png");
//        mStrings.add("https://pbs.twimg.com/profile_images/2100693240/58534_150210305010136_148613708503129_315282_6481640_n_normal.jpg");

			listView = (ListView) rootView.findViewById(R.id.list);
			adapter = new LazyAdapter(getActivity(), gsonParser.pic_url, gsonParser.name);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
					System.out.println("Item Clicked");
					TextView c = (TextView) v.findViewById(R.id.text);
					String playerChanged = c.getText().toString();
					Toast.makeText(getActivity(), playerChanged, Toast.LENGTH_SHORT).show();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		GSONParser gsonParser = new GSONParser();

		String configURL = "http://184.95.54.213/standrewsmobile/configuration.json";
		String defaultAlertsURL = "http://184.95.54.213/standrewsmobile/newsletters.json";
		String alertsURL = "";
		try {
			//Read URL of Alerts List from a Config JSON in a server
			String URL = gsonParser.readConfig("newsletters");

			if (null == configURL) {
				//Read Alerts List from Default URL
				alertsURL = defaultAlertsURL;
			}
			else {
				alertsURL = URL;
			}
		}
		catch (Exception ex) {
			Log.e("AlertsListActivity",ex.getMessage());
		}

		//Process the ALERTS GSON
		try {
			gsonParser.processDataFromGSON(alertsURL, rootView.getContext());

			listView = (ListView)rootView.findViewById(R.id.list);
			adapter = new LazyAdapter(getActivity(), gsonParser.pic_url, gsonParser.name);
			listView.setAdapter(adapter);
			List<String> details = gsonParser.details;
			final List<String> finalDetails = details;
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
						System.out.println("Item Clicked");
						TextView c = (TextView) v.findViewById(R.id.text);
						String alertName = c.getText().toString();

					FragmentManager fragmentManager = getFragmentManager();
					Fragment newpost = new DetailsFragment();
					Bundle args = new Bundle();

						//Intent i = new Intent(AlertsListFragment.this, DetailsActivity.class);

						ImageView listimage=(ImageView)v.findViewById(R.id.listimage);
						Bitmap bitmap = ((BitmapDrawable)listimage.getDrawable()).getBitmap();

						ByteArrayOutputStream bs = new ByteArrayOutputStream();
						bitmap.compress(Bitmap.CompressFormat.PNG, 100, bs);
						args.putByteArray("byteArray", bs.toByteArray());
						args.putString("NAME", alertName);
						args.putString("DETAILS", finalDetails.get(position));

					newpost.setArguments(args);
					fragmentManager.beginTransaction().replace(R.id.content_frame, newpost).commit();

					//startActivity(i);
					}
				});

		} catch (Exception e) {
			e.printStackTrace();
		}
		//Button b=(Button)findViewById(R.id.button1);
		//b.setOnClickListener(listener);

		return rootView;
	}

	public View.OnClickListener buttonListener=new View.OnClickListener(){
		@Override
		public void onClick(View arg0) {
			adapter.imageLoader.clearCache();
			adapter.notifyDataSetChanged();
			//finish();
		}
	};

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// Do something that differs the Activity's menu here
		inflater.inflate(R.menu.menu_details, menu);
		//menu.findItem(R.id.action_refresh).setVisible(true);
		super.onCreateOptionsMenu(menu, inflater);
	}

}
