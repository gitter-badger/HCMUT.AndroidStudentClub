package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vn.edu.hcmut.test.R;
import vn.edu.hcmut.test.adapter.StudentAdapter;
import vn.edu.hcmut.test.model.Student;

public class ListViewActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView lvStudents;
    private ArrayList<Student> st;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.club_screen);

        lvStudents = (ListView) findViewById(R.id.lvStudents);

        st = new ArrayList<>();
        st.add(new Student("1", "A"));
        st.add(new Student("2", "B"));
        st.add(new Student("3", "C"));
        st.add(new Student("4", "D"));
        st.add(new Student("5", "E"));

        adapter = new StudentAdapter(st);
        lvStudents.setAdapter(adapter);

        lvStudents.setOnItemClickListener(this);
        lvStudents.setOnItemLongClickListener(this);
    }

    public void adb() {

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
}
