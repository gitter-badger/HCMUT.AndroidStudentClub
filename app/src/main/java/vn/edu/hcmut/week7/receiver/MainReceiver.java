package vn.edu.hcmut.week7.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import vn.edu.hcmut.test.R;
import vn.edu.hcmut.test.activity.MainActivity;

/**
 * Created by H.Anh on 5/23/2015.
 */
public class MainReceiver extends BroadcastReceiver {

    public static final String ACTION = "asc.broadcast.receiver";
    public static final String EXTRA_NUMBER = "extra_number";

    @Override
    public void onReceive(Context context, Intent data) {
        int number = data.getIntExtra(EXTRA_NUMBER, 0);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Notification n = new Notification.Builder(context)
                .setContentTitle("Receiver")
                .setContentText("Number: " + number)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);

        Log.e("Receiver", String.valueOf(number));
    }

}
