package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.hcmut.test.R;

public class ResultActivity extends Activity implements View.OnClickListener {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        int number = intent.getIntExtra("integer", -1);

        text = (EditText) findViewById(R.id.text);
        text.setText(message);

        Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnOk:
                String result = text.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("result", result);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
