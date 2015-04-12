package vn.edu.hcmut.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.hcmut.test.R;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    public static final String EXTRA_EDIT_MODE = "extra_edit_mode";
    private boolean isEditMode;

    private TextView txtError, txtTitle;
    private TextView txtFirstName, txtLastName;
    private TextView txtEmailAddress;
    private TextView txtPassword, txtConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtError = (TextView) findViewById(R.id.txtError);

        txtFirstName = (TextView) findViewById(R.id.txtFirstName);
        txtLastName = (TextView) findViewById(R.id.txtLastName);

        txtEmailAddress = (TextView) findViewById(R.id.txtEmailAddress);

        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtConfirmPassword = (TextView) findViewById(R.id.txtConfirmPassword);

        txtConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    register();
                    return true;
                }

                return false;
            }
        });

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        isEditMode = intent.hasExtra(EXTRA_EDIT_MODE);
        if (isEditMode) {
            String title = getString(R.string.title_edit_mode);
            txtTitle.setText(title);

            String firstName = intent.getStringExtra(InfoActivity.EXTRA_FIRST_NAME);
            String lastName = intent.getStringExtra(InfoActivity.EXTRA_LAST_NAME);
            String emailAddress = intent.getStringExtra(InfoActivity.EXTRA_EMAIL_ADDRESS);
            txtFirstName.setText(firstName);

            txtLastName.setText(lastName);
            txtEmailAddress.setText(emailAddress);

            String text = getString(R.string.btn_register_edit_mode);
            btnRegister.setText(text);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void register() {
        String firstName = txtFirstName.getText().toString().trim();
        if (firstName.isEmpty()) {
            showError(R.string.msg_enter_first_name);
            txtFirstName.requestFocus();
            return;
        }

        String lastName = txtLastName.getText().toString().trim();
        if (lastName.isEmpty()) {
            showError(R.string.msg_enter_last_name);
            txtLastName.requestFocus();
            return;
        }

        String emailAddress = txtEmailAddress.getText().toString().trim();
        if (emailAddress.isEmpty()) {
            showError(R.string.msg_enter_email_address);
            txtEmailAddress.requestFocus();
            return;
        }

        boolean emailValid = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
        if (!emailValid) {
            showError(R.string.msg_enter_email_address_invalid);
            txtEmailAddress.requestFocus();
            return;
        }

        String confirmPassword = txtConfirmPassword.getText().toString();
        String password = txtPassword.getText().toString();
        if (password.isEmpty() && (!isEditMode || !confirmPassword.isEmpty())) {
            showError(R.string.msg_enter_password);
            txtPassword.requestFocus();
            return;
        }

        if ((!isEditMode || !password.isEmpty()) && !confirmPassword.equals(password)) {
            showError(R.string.msg_password_not_match);
            txtConfirmPassword.requestFocus();
            return;
        }

        hideError();
        btnRegister.requestFocus();

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra(InfoActivity.EXTRA_FIRST_NAME, firstName);
        intent.putExtra(InfoActivity.EXTRA_LAST_NAME, lastName);
        intent.putExtra(InfoActivity.EXTRA_EMAIL_ADDRESS, emailAddress);

        if (!isEditMode || !password.isEmpty()) {
            intent.putExtra(InfoActivity.EXTRA_PASSWORD, password);
        }

        if (isEditMode) {
            setResult(RESULT_CANCELED, intent);
        } else {
            startActivity(intent);
        }

        finish();
    }

    private void showError(int stringId) {
        String text = getString(stringId);
        txtError.setText(text);
        txtError.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        txtError.setText("");
        txtError.setVisibility(View.GONE);
    }
}
