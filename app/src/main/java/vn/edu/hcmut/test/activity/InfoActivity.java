package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.hcmut.test.R;

public class InfoActivity extends Activity implements View.OnClickListener {

    private static final int REQUEST_CODE = 1001;

    public static final String EXTRA_FIRST_NAME = "extra_first_name";
    public static final String EXTRA_LAST_NAME = "extra_last_name";
    public static final String EXTRA_EMAIL_ADDRESS = "extra_email_address";
    public static final String EXTRA_PASSWORD = "extra_password";

    private String firstName, lastName, emailAddress;
    private TextView txtFirstName, txtLastName, txtEmailAddress, txtPassword;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLayout();

        Intent intent = getIntent();
        fillData(intent);
    }

    private void initLayout() {
        setContentView(R.layout.info_main);

        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtLastName = (TextView) findViewById(R.id.txtLastName);
        txtEmailAddress = (TextView) findViewById(R.id.txtEmailAddress);
        txtPassword = (TextView) findViewById(R.id.txtPassword);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);
    }

    private void fillData(Intent intent) {
        firstName = intent.getStringExtra(EXTRA_FIRST_NAME);
        txtFirstName.setText(firstName);

        lastName = intent.getStringExtra(EXTRA_LAST_NAME);
        txtLastName.setText(lastName);

        emailAddress = intent.getStringExtra(EXTRA_EMAIL_ADDRESS);
        txtEmailAddress.setText(emailAddress);

        String password = intent.getStringExtra(EXTRA_PASSWORD);
        if (password != null) {
            txtPassword.setText(password);
        }
    }

    public void createActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("key", "hoang ");
        startActivity(intent);

        startActivityForResult(intent, 10000);
        startActivityForResult(intent, 10001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    fillData(intent);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, intent);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnEdit:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(MainActivity.EXTRA_EDIT_MODE, true);
                intent.putExtra(InfoActivity.EXTRA_FIRST_NAME, firstName);
                intent.putExtra(InfoActivity.EXTRA_LAST_NAME, lastName);
                intent.putExtra(InfoActivity.EXTRA_EMAIL_ADDRESS, emailAddress);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }
}
