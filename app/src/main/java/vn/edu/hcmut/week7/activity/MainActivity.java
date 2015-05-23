package vn.edu.hcmut.week7.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import vn.edu.hcmut.test.R;
import vn.edu.hcmut.week7.receiver.MainReceiver;
import vn.edu.hcmut.week7.service.MainService;

/**
 * Created by H.Anh on 5/23/2015.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private EditText txtNumber;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            int number = intent.getIntExtra(MainReceiver.EXTRA_NUMBER, -1);
            String text = String.valueOf(number);
            txtNumber.setText(text);
        }
    };

    private IntentFilter filter = new IntentFilter(MainReceiver.ACTION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.week7_main_screen);

        View btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);

        View btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);

        txtNumber = (EditText) findViewById(R.id.txtNumber);

        View btnSend = findViewById(R.id.btnIncrease);
        btnSend.setOnClickListener(this);

        View btnDecrease = findViewById(R.id.btnDecrease);
        btnDecrease.setOnClickListener(this);

        View btnFragment = findViewById(R.id.btnFragment);
        btnFragment.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnStart:
                Intent start = new Intent(this, MainService.class);
                startService(start);
                break;
            case R.id.btnStop:
                Intent stop = new Intent(this, MainService.class);
                stopService(stop);
                break;
            case R.id.btnIncrease:
                Intent send = new Intent(this, MainService.class);
                String text = txtNumber.getText().toString();
                int number = Integer.parseInt(text);
                send.putExtra(MainService.TYPE, MainService.TYPE_INCREMENT);
                send.putExtra(MainService.EXTRA_NUMBER, number);
                startService(send);
                break;
            case R.id.btnDecrease:
                Intent send2 = new Intent(this, MainService.class);
                String text2 = txtNumber.getText().toString();
                int number2 = Integer.parseInt(text2);
                send2.putExtra(MainService.TYPE, MainService.TYPE_DECREMENT);
                send2.putExtra(MainService.EXTRA_NUMBER, number2);
                startService(send2);
                break;
            case R.id.btnFragment:
                Intent frag = new Intent(this, MainFragmentActivity.class);
                startActivity(frag);
                break;
        }
    }
}
