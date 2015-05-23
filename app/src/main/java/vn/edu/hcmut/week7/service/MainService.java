package vn.edu.hcmut.week7.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import vn.edu.hcmut.week7.receiver.MainReceiver;

/**
 * Created by H.Anh on 5/23/2015.
 */
public class MainService extends Service {

    public static final String TYPE = "type";
    public static final int TYPE_INCREMENT = 0;
    public static final int TYPE_DECREMENT = 1;

    public static final String EXTRA_NUMBER = "number";

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "Service onCreate", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Service onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service onStartCommand", Toast.LENGTH_LONG).show();

        int type = intent.getIntExtra(TYPE, -1);
        switch (type) {
            case TYPE_INCREMENT:
                int number1 = intent.getIntExtra(EXTRA_NUMBER, 0);
                number1++;

                Intent in = new Intent(MainReceiver.ACTION);
                in.putExtra(MainReceiver.EXTRA_NUMBER, number1);
                sendBroadcast(in);

                Toast.makeText(this, "Number: " + number1, Toast.LENGTH_LONG).show();
                break;
            case TYPE_DECREMENT:
                int number2 = intent.getIntExtra(EXTRA_NUMBER, 0);
                number2--;

                Intent in2 = new Intent(MainReceiver.ACTION);
                in2.putExtra(MainReceiver.EXTRA_NUMBER, number2);
                sendBroadcast(in2);

                Toast.makeText(this, "Number: " + number2, Toast.LENGTH_LONG).show();
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
