package vn.edu.hcmut.week3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.hcmut.test.R;

public class AddNewScreen extends Activity implements View.OnClickListener {

    public static final String EXTRA_POSITION = "key_pos";
    public static final String EXTRA_ID = "key_id";
    public static final String EXTRA_NAME = "key_name";
    public static final String EXTRA_EMAIL = "key_email";

    private int position;
    private Button btnSave, btnCancel;
    private EditText txtId, txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.week3_add_new_screen);

        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        Intent intent = getIntent();
        position = intent.getIntExtra(EXTRA_POSITION, -1);
        if (position != -1) {
            String id = intent.getStringExtra(EXTRA_ID);
            String name = intent.getStringExtra(EXTRA_NAME);
            String email = intent.getStringExtra(EXTRA_EMAIL);

            txtId.setText(id);
            txtName.setText(name);
            txtEmail.setText(email);
        }

        btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId) {
            case R.id.btnSave:
                Intent intent = new Intent();
                String id = txtId.getText().toString();
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();

                intent.putExtra(EXTRA_POSITION, position);
                intent.putExtra(EXTRA_ID, id);
                intent.putExtra(EXTRA_NAME, name);
                intent.putExtra(EXTRA_EMAIL, email);

                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }
}
