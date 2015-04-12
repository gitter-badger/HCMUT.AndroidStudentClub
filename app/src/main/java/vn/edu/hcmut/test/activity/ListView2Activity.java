package vn.edu.hcmut.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.edu.hcmut.test.R;

public class ListView2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.club_screen);

        ListView lvStudents = (ListView) findViewById(R.id.lvStudents);

        String[] from = {"id", "name"};

        List<Map<String, String>> dt = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put(from[0], "51100011");
        item.put(from[1], "Tran Van A");
        dt.add(item);

        item = new HashMap<>();
        item.put(from[0], "2");
        item.put(from[1], "Nguyen Van B");
        dt.add(item);

        item = new HashMap<>();
        item.put(from[0], "2");
        item.put(from[1], "C");
        dt.add(item);

        item = new HashMap<>();
        item.put(from[0], "51000");
        item.put(from[1], "Hoang ANh");
        dt.add(item);

        int[] to = {android.R.id.text1, android.R.id.text2 };

        BaseAdapter adapter = new SimpleAdapter(this, dt, android.R.layout.simple_list_item_2, from, to);
        lvStudents.setAdapter(adapter);

    }
}
