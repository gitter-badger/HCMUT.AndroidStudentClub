package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

import vn.edu.hcmut.test.R;

public class ActivityLifeCycle extends Activity implements View.OnClickListener {

    private EditText text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.life_cycle_screen);

        // The activity is being created.
        Log.e("Activity", "onCreate");

        Button btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(this);

        Button btnShowImageImplicit = (Button) findViewById(R.id.btnShowImageImplicit);
        btnShowImageImplicit.setOnClickListener(this);

        Button btnShowImageExplicit = (Button) findViewById(R.id.btnShowImageExplicit);
        btnShowImageExplicit.setOnClickListener(this);

        Button btnStartForResult = (Button) findViewById(R.id.btnStartForResult);
        btnStartForResult.setOnClickListener(this);

        text = (EditText) findViewById(R.id.text);
    }

    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_2 = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("Activity", "onActivityResult " + requestCode);

        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String text = data.getStringExtra("result");
                    showAlert("Alert", "OK - " + text);
                    break;
                case RESULT_CANCELED:
                    showAlert("Result", "Cancelled");
                    break;

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.e("Activity", "onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        Log.e("Activity", "Resume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        Log.e("Activity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Log.e("Activity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.e("Activity", "onDestroy");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String path = "/storage/emulated/0/ASC/3.jpg";
        Uri uri = Uri.fromFile(new File(path));

        switch (id) {
            case R.id.btnShowDialog:
                showAlert("Alert", "Test alert message");
                break;
            case R.id.btnShowImageImplicit:
                Intent intentImplicit = new Intent(this, DetailActivity.class);
                intentImplicit.setData(uri);
                startActivity(intentImplicit);
                break;
            case R.id.btnShowImageExplicit:
                Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
                mediaIntent.setDataAndType(uri, "image/*");
                Intent chooser = Intent.createChooser(mediaIntent, "Open with");
                startActivity(chooser);
                break;
            case R.id.btnStartForResult:
                String message = text.getText().toString();
                Intent intentForResult = new Intent(this, ResultActivity.class);
                intentForResult.putExtra("message", message);
                intentForResult.putExtra("integer", 1);
                startActivityForResult(intentForResult, REQUEST_CODE);
                break;
        }


    }

    public static class A implements Parcelable {
        public String a;
        public int b;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }
    }

    public void doSomething() {

    }
}
