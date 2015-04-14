package vn.edu.hcmut.week3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.edu.hcmut.test.R;
import vn.edu.hcmut.test.activity.MainActivity;

public class ClubAdapter extends BaseAdapter {

    private List<Item> data;
    private MainScreen activity;

    public ClubAdapter(List<Item> data, MainScreen activity) {
        this.data = data;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class Holder {
        public TextView txtId, txtName, txtEdit, txtDelete;

        public Holder(View view) {
            txtId = (TextView) view.findViewById(R.id.txtId);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtEdit = (TextView) view.findViewById(R.id.txtEdit);
            txtDelete = (TextView) view.findViewById(R.id.txtDelete);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.week3_studen_item, null);
            Holder holder = new Holder(convertView);
            convertView.setTag(holder);
        }

        Holder holder = (Holder) convertView.getTag();

        final Item item = data.get(position);

        holder.txtId.setText(item.id);
        holder.txtName.setText(item.name);

        holder.txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddNewScreen.class);
                intent.putExtra(AddNewScreen.EXTRA_POSITION, position);
                intent.putExtra(AddNewScreen.EXTRA_ID, item.id);
                intent.putExtra(AddNewScreen.EXTRA_NAME, item.name);
                intent.putExtra(AddNewScreen.EXTRA_EMAIL, item.email);
                activity.startActivityForResult(intent, MainScreen.REQUEST_EDIT);
            }
        });

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(activity)
                        .setTitle("Confirm")
                        .setMessage("Do you want to remove this member?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.remove(position);
                                activity.updateView();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        return convertView;
    }
}
