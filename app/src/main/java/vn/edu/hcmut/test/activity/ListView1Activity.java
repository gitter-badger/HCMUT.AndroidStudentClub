package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import vn.edu.hcmut.test.R;

public class ListView1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.club_screen);

        ListView lvStudents = (ListView) findViewById(R.id.lvStudents);

        String[] data = {
                "A", "B", "C", "D"
        };

        List<String> students = Arrays.asList(data);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, students);

        lvStudents.setAdapter(adapter);

    }
}
