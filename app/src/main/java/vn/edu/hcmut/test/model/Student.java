package vn.edu.hcmut.test.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Student implements Parcelable {

    public static final String JSON_KEY_ID = "id";
    public static final String JSON_KEY_NAME = "name";

    private String id;
    private String name;

    public Student(JSONObject data) throws JSONException {
        id = data.getString(JSON_KEY_ID);
        name = data.getString(JSON_KEY_NAME);
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public JSONObject toJson() {
        try {
            JSONObject object = new JSONObject();
            object.put(JSON_KEY_ID, id);
            object.put(JSON_KEY_NAME, name);

            return object;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Student> fromJson(JSONArray array) {
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject item = array.getJSONObject(i);
                Student student = new Student(item);
                students.add(student);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return students;
    }

    public static JSONArray toJson(List<Student> students) {
        JSONArray array = new JSONArray();
        for (Student student : students) {
            JSONObject item = student.toJson();
            array.put(item);
        }
        return array;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
