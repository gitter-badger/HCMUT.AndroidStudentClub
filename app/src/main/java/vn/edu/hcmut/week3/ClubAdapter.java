//package vn.edu.hcmut.week3;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//import vn.edu.hcmut.test.R;
//
//public class ClubAdapter extends BaseAdapter {
//
//    private List<Item> data;
//    private MainScreen activity;
//
//    public ClubAdapter(List<Item> data, MainScreen activity) {
//        this.data = data;
//        this.activity = activity;
//    }
//
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    public static class ViewHolder {
//        public TextView txtId, txtName, txtEdit, txtDelete;
//
//        public ViewHolder(View view) {
//            txtId = (TextView) view.findViewById(R.id.txtId);
//            txtName = (TextView) view.findViewById(R.id.txtName);
//            txtEdit = (TextView) view.findViewById(R.id.txtEdit);
//            txtDelete = (TextView) view.findViewById(R.id.txtDelete);
//        }
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        if (convertView == null) {
//            convertView = View.inflate(activity, R.layout.week3_studen_item, null);
//            ViewHolder viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//
//        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
//
//        final Item item = data.get(position);
//
//        viewHolder.txtId.setText(item.id);
//        viewHolder.txtName.setText(item.name);
//
//        viewHolder.txtEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.showEditScreen(position, item);
//            }
//        });
//
//        viewHolder.txtDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(activity)
//                        .setTitle("Confirm")
//                        .setMessage("Do you want to remove this member?")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                data.remove(position);
//                                activity.updateView();
//                            }
//                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
//            }
//        });
//
//        return convertView;
//    }
//}
