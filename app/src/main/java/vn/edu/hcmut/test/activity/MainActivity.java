package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vn.edu.hcmut.test.R;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_screen);

        Button btnDemo1 = (Button) findViewById(R.id.btnDemo1);
        btnDemo1.setOnClickListener(this);

        Button btnDemo2 = (Button) findViewById(R.id.btnDemo2);
        btnDemo2.setOnClickListener(this);

        Button btnDemo3 = (Button) findViewById(R.id.btnDemo3);
        btnDemo3.setOnClickListener(this);

        Button btnDemo4 = (Button) findViewById(R.id.btnDemo4);
        btnDemo4.setOnClickListener(this);

        Button btnDemo5 = (Button) findViewById(R.id.btnDemo5);
        btnDemo5.setOnClickListener(this);

        Button btnDemo6 = (Button) findViewById(R.id.btnDemo6);
        btnDemo6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        Class clazz = null;

        switch (id) {
            case R.id.btnDemo1:
                clazz = ListView1Activity.class;
                break;
            case R.id.btnDemo2:
                clazz = ListView2Activity.class;
                break;
            case R.id.btnDemo3:
                clazz = ListViewActivity.class;
                break;
            case R.id.btnDemo4:
                clazz = ListViewSaveActivity.class;
                break;
            case R.id.btnDemo5:
                clazz = ListViewPrefActivity.class;
                break;
            case R.id.btnDemo6:
                clazz = ClubActivity.class;
                break;
        }

        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
