package com.theoreticsinc.schoolapp.utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import com.parse.ParsePushBroadcastReceiver;
import com.theoreticsinc.schoolapp.R;
import com.theoreticsinc.schoolapp.activities.NotificationReceiverActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by angelo on 11/14/15.
 */
public class MyReceiver extends ParsePushBroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;
    public static int numMessages = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Parse Custom Receiver", ">>onReceive()<< RECEIVED PUSH NOTIFICATION!");

        try {
            Bundle extras = intent.getExtras();
            String message = extras != null ? extras.getString("com.parse.Data") : "";
            JSONObject jObject = new JSONObject(message);
            Log.d("Parse Custom Receiver", ">>onReceive()<< SHOW TOAST!" + jObject);

            Toast toast = Toast.makeText(context, jObject.getString("alert"), Toast.LENGTH_LONG);
            toast.show();

            String action = intent.getAction();
            Log.d("Custom", action);

            if (action.equalsIgnoreCase("com.parse.push.intent.UPDATE_STATUS")) {
                String title = "appname";

                if (jObject.has("header"))
                    title = jObject.getString("header");

                generateNotification(context, title, jObject,"");
            }
            else if (action.equalsIgnoreCase("com.parse.push.intent.RECEIVE")) {
                String title = "appname";

                if (jObject.has("header"))
                    title = jObject.getString("header");

                generateNotification(context, title, jObject,"");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("Parse Custom Receiver", ">>onReceive()<< Vibrate Phone!");

    }

    private void generateNotification(Context context, String title, JSONObject json, String contenttext) {

        Log.d("Parse Custom Receiver", "generateNotification");

        Intent intent = new Intent(context.getApplicationContext(), NotificationReceiverActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(context.getApplicationContext(), (int) System.currentTimeMillis(), intent, 0);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setContentText("Subject")
                .setNumber(++numMessages);

        mBuilder.setContentIntent(contentIntent);
        Notification n = mBuilder.build();
        notificationManager.notify((int) System.currentTimeMillis(), n);
/*
        Notification n  = new Notification.Builder(context.getApplicationContext())
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "Call", pIntent)
                .addAction(R.mipmap.ic_launcher, "More", pIntent)
                .addAction(R.mipmap.ic_launcher, "And more", pIntent).build();
*/
        //n.flags |= Notification.FLAG_AUTO_CANCEL;

        //notificationManager.notify(0, n);

        Log.d("Parse Custom Receiver", "Done Notifying");

    }
}