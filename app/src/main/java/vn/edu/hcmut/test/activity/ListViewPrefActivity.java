package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import vn.edu.hcmut.test.R;
import vn.edu.hcmut.test.adapter.StudentAdapter;
import vn.edu.hcmut.test.model.Student;

public class ListViewPrefActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static final String DATA_KEY = "students";
    private static final String PREF_KEY = "json_data";

    private ListView lvStudents;
    private ArrayList<Student> st;
    private BaseAdapter adapter;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.club_screen);

        lvStudents = (ListView) findViewById(R.id.lvStudents);

        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String prefData = preferences.getString("json_data", null);

        if (savedInstanceState != null) {
            st = savedInstanceState.getParcelableArrayList(DATA_KEY);
        } else if (prefData != null) {
            try {
                JSONArray array = new JSONArray(prefData);
                st = Student.fromJson(array);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            st = new ArrayList<>();
            st.add(new Student("1", "A"));
            st.add(new Student("2", "B"));
            st.add(new Student("3", "C"));
            st.add(new Student("4", "D"));
            st.add(new Student("5", "E"));
        }

        adapter = new StudentAdapter(st);
        lvStudents.setAdapter(adapter);

        lvStudents.setOnItemClickListener(this);
        lvStudents.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = st.get(position);
        String message = "Choice: " + student.toString();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = st.get(position);
        String message = "Remove: " + student.toString();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        st.remove(position);
        adapter.notifyDataSetChanged();
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.actionClear:
//                preferences.edit().clear().commit();
//
//                Intent intent = new Intent(this, ClubActivity.class);
//                startActivity(intent);
//
//                finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(DATA_KEY, st);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String data = Student.toJson(st).toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_KEY, data).commit();
    }
}
