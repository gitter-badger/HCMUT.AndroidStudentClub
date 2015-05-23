//package vn.edu.hcmut.week4;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.androidquery.AQuery;
//import com.androidquery.callback.AjaxCallback;
//import com.androidquery.callback.AjaxStatus;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//import vn.edu.hcmut.test.R;
//
//public class ConnectScreen extends Activity {
//
//    private EditText text;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.connect_activity);
//
//        text = (EditText) findViewById(R.id.text);
//
//        AQuery aQuery = new AQuery(this);
//
//        aQuery.ajax("http://api.routine.geekup.vn/", JSONObject.class, new AjaxCallback<JSONObject>() {
//            @Override
//            public void callback(String url, JSONObject object, AjaxStatus status) {
//                try {
//                    String name = object.getString("name");
//                    text.setText(name);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("id", "51000083");
//        params.put("name", "Hoang Anh");
//
//        aQuery.ajax("url", params, JSONObject.class, new AjaxCallback<JSONObject>() {
//            @Override
//            public void callback(String url, JSONObject object, AjaxStatus status) {
//
//            }
//        });
//
//        new AsyncTask<Integer, Void, String>() {
//            @Override
//            protected String doInBackground(Integer... params) {
//                return getData();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                JSONObject json = null;
//                try {
//                    json = new JSONObject(s);
//                    String message = json.getString("message");
//                    text.setText(message);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }.execute(1, 2);
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                text.setText("abc");
//            }
//        }).start();
//
//
//
////        new AsyncTask<Void, Void, String>() {
////            @Override
////            protected String doInBackground(Void... params) {
////                return getData();
////            }
////
////            @Override
////            protected void onPostExecute(String data) {
////                text.setText(data);
////            }
////        }.execute();
//    }
//
//    private String path = "http://10.0.3.2";
//
//    public String getData() {
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(path);
//        try {
//            HttpResponse response = httpClient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            String data = EntityUtils.toString(entity);
//            return data;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    private AsyncTask<Void, Void, String> simpleRequest = new AsyncTask<Void, Void, String>() {
//        @Override
//        protected String doInBackground(Void... params) {
//
//            try {
//                URL url = new URL(path);
//
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//                InputStream inputStream = connection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader reader = new BufferedReader(inputStreamReader);
//
//                StringBuilder builder = new StringBuilder();
//
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                }
//
//                return builder.toString();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            if (result == null) {
//                Toast.makeText(ConnectScreen.this, "Error", Toast.LENGTH_LONG).show();
//            } else {
//                text.setText(result);
//            }
//        }
//    };
//}
