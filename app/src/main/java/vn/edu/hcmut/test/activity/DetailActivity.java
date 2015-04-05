package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import vn.edu.hcmut.test.R;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_screen);

        ImageView image = (ImageView) findViewById(R.id.image);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            image.setImageURI(uri);
        }
    }
}
