package com.theoreticsinc.standrewsmobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;
import com.theoreticsinc.standrewsmobile.R;

public class SplashScreenActivity extends Activity {

    private static final int REQUEST_CODE = 0;
    private static String TAG =".SplashScreenActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
/*
        SharedPreferences preferences = getSharedPreferences("SchoolApp", Context.MODE_PRIVATE);
        boolean init = preferences.getBoolean("initialized", false);

        if (init == false) {
            // Enable Local Datastore.
            Parse.enableLocalDatastore(this);
        }

        try {
            // Add your initialization code here
            Parse.initialize(this, "@string/parse_app_id", "@string/parse_client_key");
        }
        catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }

        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("initialized", true);
        edit.apply();
*/




    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentapiVersion = android.os.Build.VERSION.SDK_INT;

                if (currentapiVersion < 14) {
                    //Intent i = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else if (currentapiVersion >= 14) {
                    //Intent i = new Intent(SplashScreenActivity.this, DrawerActivity.class);
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, 3000);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        new AlertDialog.Builder(this)
                .setTitle("Resume Main?")
                .setMessage("Press No to Reset")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        //DashboardActivity.super.onBackPressed();
                        finish();
                    }
                }).create().show();
                */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
