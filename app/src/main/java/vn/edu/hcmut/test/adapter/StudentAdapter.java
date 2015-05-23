package vn.edu.hcmut.test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.hcmut.test.R;
import vn.edu.hcmut.test.model.Student;

public class StudentAdapter extends BaseAdapter {

    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    public static class Holder {
//        public TextView txtId;
//        public TextView txtName;
//
//        public Holder(View view) {
//            txtId = (TextView) view.findViewById(R.id.txtId);
//            txtName = (TextView) view.findViewById(R.id.txtName);
//        }
//    }

    public static class ViewHolder {
        public TextView txtId, txtName;

        public ViewHolder(View view) {
            txtId = (TextView) view.findViewById(R.id.txtId);
            txtName = (TextView) view.findViewById(R.id.txtName);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if (convertView == null) {

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.student_item, parent, false);
            convertView = View.inflate(context, R.layout.student_item, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();

//        TextView txtId = (TextView) convertView.findViewById(R.id.txtId);
//        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);

        Student student = students.get(position);
        String id = student.getId();
        viewHolder.txtId.setText(id);

        String name = student.getName();
        viewHolder.txtName.setText(name);

        return convertView;
    }
}
