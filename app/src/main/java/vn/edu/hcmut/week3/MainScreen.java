package vn.edu.hcmut.week3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmut.test.R;

public class MainScreen extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ClubAdapter adapter;
    private ListView lvStudents;
    private View txtEmtpy;
    private Button btnAddNew;
    private List<Item> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.week3_main_screen);

        lvStudents = (ListView) findViewById(R.id.lvStudents);
        txtEmtpy = findViewById(R.id.txtEmpty);
        btnAddNew = (Button) findViewById(R.id.btnAddNew);

        data = new ArrayList<>();
        adapter = new ClubAdapter(data, this);
        lvStudents.setAdapter(adapter);

        lvStudents.setVisibility(View.GONE);
        txtEmtpy.setVisibility(View.VISIBLE);

        lvStudents.setOnItemClickListener(this);
        btnAddNew.setOnClickListener(this);
    }

    public void updateView() {
        if (data.isEmpty()) {
            lvStudents.setVisibility(View.GONE);
            txtEmtpy.setVisibility(View.VISIBLE);
        } else {
            lvStudents.setVisibility(View.VISIBLE);
            txtEmtpy.setVisibility(View.GONE);

            adapter.notifyDataSetChanged();
        }
    }

    public static final int REQUEST_NEW = 1001;
    public static final int REQUEST_EDIT = 1002;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_NEW:
                if (resultCode == RESULT_OK) {
                    String id = intent.getStringExtra(AddNewScreen.EXTRA_ID);
                    String name = intent.getStringExtra(AddNewScreen.EXTRA_NAME);
                    String email = intent.getStringExtra(AddNewScreen.EXTRA_EMAIL);

                    Item item = new Item(id, name, email);
                    data.add(item);
                    updateView();
                }
                break;
            case REQUEST_EDIT:
                if (resultCode == RESULT_OK) {
                    int position = intent.getIntExtra(AddNewScreen.EXTRA_POSITION, -1);
                    if (position != -1) {
                        Item item = data.get(position);
                        item.id = intent.getStringExtra(AddNewScreen.EXTRA_ID);
                        item.name = intent.getStringExtra(AddNewScreen.EXTRA_NAME);
                        item.email = intent.getStringExtra(AddNewScreen.EXTRA_EMAIL);
                        updateView();
                    }
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
            case R.id.btnAddNew:
                Intent createIntent = new Intent(this, AddNewScreen.class);
                startActivityForResult(createIntent, REQUEST_NEW);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewScreen.class);
        Item item = data.get(position);
        intent.putExtra(ViewScreen.EXTRA_ID, item.id);
        intent.putExtra(ViewScreen.EXTRA_ID, item.name);
        intent.putExtra(ViewScreen.EXTRA_ID, item.email);
        startActivity(intent);
    }
}
