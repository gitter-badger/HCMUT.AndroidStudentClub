//package vn.edu.hcmut.week3;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.ListView;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import vn.edu.hcmut.test.R;
//
//public class MainScreen extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
//
//    private ClubAdapter adapter;
//    private ListView lvStudents;
//    private View txtEmpty;
//    private Button btnAddNew;
//    private ArrayList<Item> data;
//
//    public void showEditScreen(int position, Item item) {
//        Intent intent = new Intent(this, AddNewScreen.class);
//        intent.putExtra(AddNewScreen.EXTRA_POSITION, position);
//        intent.putExtra(AddNewScreen.EXTRA_ID, item.id);
//        intent.putExtra(AddNewScreen.EXTRA_NAME, item.name);
//        intent.putExtra(AddNewScreen.EXTRA_EMAIL, item.email);
//        startActivityForResult(intent, MainScreen.REQUEST_EDIT);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.week3_main_screen);
//
//        lvStudents = (ListView) findViewById(R.id.lvStudents);
//        txtEmpty = findViewById(R.id.txtEmpty);
//        btnAddNew = (Button) findViewById(R.id.btnAddNew);
//
//        if (savedInstanceState != null) {
//            data = savedInstanceState.getParcelableArrayList(DATA_KEY);
//        } else {
//            data = new ArrayList<>();
//            getData();
//        }
//
//        adapter = new ClubAdapter(data, this);
//        lvStudents.setAdapter(adapter);
//        lvStudents.setOnScrollListener(this);
//
//        if (savedInstanceState != null) {
//            firstIndex = savedInstanceState.getInt(POSITION_KEY);
//            lvStudents.smoothScrollToPositionFromTop(firstIndex, 0, 0);
//        }
//
//        lvStudents.setOnItemClickListener(this);
//        btnAddNew.setOnClickListener(this);
//
//        updateView();
//    }
//
//    private String path = "http://api.routine.geekup.vn";
//
//    public void getData() {
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... params) {
//                DefaultHttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpGet = new HttpGet(path);
//                try {
//                    HttpResponse response = httpClient.execute(httpGet);
//                    HttpEntity entity = response.getEntity();
//                    String data = EntityUtils.toString(entity);
//                    return data;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String data) {
//                Log.e("Data", data);
//
//                try {
//                    JSONObject json = new JSONObject(data);
//
//                    Log.e("Json", json.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }.execute();
//    }
//
//    public void updateView() {
//        if (data.isEmpty()) {
//            lvStudents.setVisibility(View.GONE);
//            txtEmpty.setVisibility(View.VISIBLE);
//        } else {
//            lvStudents.setVisibility(View.VISIBLE);
//            txtEmpty.setVisibility(View.GONE);
//
//            adapter.notifyDataSetChanged();
//        }
//    }
//
//    public static final int REQUEST_NEW = 1001;
//    public static final int REQUEST_EDIT = 1002;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        switch (requestCode) {
//            case REQUEST_NEW:
//                if (resultCode == RESULT_OK) {
//                    String id = intent.getStringExtra(AddNewScreen.EXTRA_ID);
//                    String name = intent.getStringExtra(AddNewScreen.EXTRA_NAME);
//                    String email = intent.getStringExtra(AddNewScreen.EXTRA_EMAIL);
//
//                    Item item = new Item(id, name, email);
//                    data.add(item);
//                    updateView();
//                }
//                break;
//            case REQUEST_EDIT:
//                if (resultCode == RESULT_OK) {
//                    int position = intent.getIntExtra(AddNewScreen.EXTRA_POSITION, -1);
//                    if (position != -1) {
//                        Item item = data.get(position);
//                        item.id = intent.getStringExtra(AddNewScreen.EXTRA_ID);
//                        item.name = intent.getStringExtra(AddNewScreen.EXTRA_NAME);
//                        item.email = intent.getStringExtra(AddNewScreen.EXTRA_EMAIL);
//                        updateView();
//                    }
//                }
//                break;
//            default:
//                super.onActivityResult(requestCode, resultCode, intent);
//                break;
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//
//        switch (id) {
//            case R.id.btnAddNew:
//                Intent createIntent = new Intent(this, AddNewScreen.class);
//                startActivityForResult(createIntent, REQUEST_NEW);
//                break;
//        }
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, ViewScreen.class);
//        Item item = data.get(position);
//        intent.putExtra(ViewScreen.EXTRA_ID, item.id);
//        intent.putExtra(ViewScreen.EXTRA_ID, item.name);
//        intent.putExtra(ViewScreen.EXTRA_ID, item.email);
//        startActivity(intent);
//    }
//
//    private static final String DATA_KEY = "data_key";
//    private static final String POSITION_KEY = "position_key";
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        outState.putParcelableArrayList(DATA_KEY, data);
//        outState.putInt(POSITION_KEY, firstIndex);
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//    }
//
//    private int firstIndex;
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        firstIndex = firstVisibleItem;
//    }
//}
