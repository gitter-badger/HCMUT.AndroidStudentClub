//package vn.edu.hcmut.week3;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import vn.edu.hcmut.test.R;
//
//public class ViewScreen extends Activity {
//
//    public static final String EXTRA_ID = "key_id";
//    public static final String EXTRA_NAME = "key_name";
//    public static final String EXTRA_EMAIL = "key_email";
//
//    private TextView txtId, txtName, txtEmail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.week3_view_screen);
//
//        txtId = (TextView) findViewById(R.id.txtId);
//        txtName = (TextView) findViewById(R.id.txtName);
//        txtEmail = (TextView) findViewById(R.id.txtEmail);
//
//        Intent intent = getIntent();
//
//        String id = intent.getStringExtra(EXTRA_ID);
//        String name = intent.getStringExtra(EXTRA_NAME);
//        String email = intent.getStringExtra(EXTRA_EMAIL);
//
//        txtId.setText(id);
//        txtName.setText(name);
//        txtEmail.setText(email);
//    }
//
//}
