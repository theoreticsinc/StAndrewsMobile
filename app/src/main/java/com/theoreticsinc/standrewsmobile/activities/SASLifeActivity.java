package com.theoreticsinc.standrewsmobile.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.theoreticsinc.standrewsmobile.R;

public class SASLifeActivity extends AppCompatActivity {

    AdView mAdView;
    PopupWindow popUp;

    LinearLayout layout;
    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sas_life);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE); // the results will be higher than using the activity context object or the getWindowManager() shortcut
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        final int w = displayMetrics.widthPixels;
        final int h = displayMetrics.heightPixels;

        layout = new LinearLayout(this);
        popUp = new PopupWindow(this);

/*        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(displayMetrics.widthPixels/4, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout icon1 = (LinearLayout) findViewById(R.id.linearLayout3);
      icon1.setLayoutParams(params);
*/
        final HorizontalScrollView mainScrollView = (HorizontalScrollView) findViewById(R.id.mainScrollView);
        mainScrollView.smoothScrollTo(0, 0);
        ImageView clubButton = (ImageView) findViewById(R.id.clubButton);
        clubButton.getLayoutParams().width = w - 5;
        clubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Club Clicked" + w);
                mainScrollView.smoothScrollTo(1000, 0);
            }
        });

        ImageView sasClubButton = (ImageView) findViewById(R.id.sasClubButton);
        sasClubButton.getLayoutParams().width = w - 5;
        sasClubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ClubActivity.class);
                startActivity(i);
                //finish();
            }
        });

        ImageView schoolIDButton = (ImageView) findViewById(R.id.schoolIDButton);
        schoolIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SchoolIDActivity.class);
                startActivity(i);
                //finish();
            }
        });

        ImageView handbookButton = (ImageView) findViewById(R.id.handbookButton);
        handbookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HandbookActivity.class);
                startActivity(i);
                //finish();
            }
        });

        ImageView venueRegButton = (ImageView) findViewById(R.id.venueRegButton);
        venueRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), VenueRegActivity.class);
                startActivity(i);
                //finish();
            }
        });



        TextView hymn = (TextView) findViewById(R.id.hymn);
        hymn.setOnClickListener(new View.OnClickListener() {

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
        TextView hymn1 = (TextView) findViewById(R.id.hymn1);
        hymn1.setOnClickListener(new View.OnClickListener() {

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

        ImageView homeButton = (ImageView) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        ImageView aboutButton = (ImageView) findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
                finish();
            }
        });

        ImageView ministryButton = (ImageView) findViewById(R.id.ministryButton);
        ministryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MinistryActivity.class);
                startActivity(i);
                finish();
            }
        });

        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.hymn, null);
        ImageView back = (ImageView) layout.findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });
        popUp.setContentView(layout);

        /*
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
*/


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
