package vn.edu.hcmut.week7.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.hcmut.test.R;

/**
 * Created by H.Anh on 5/23/2015.
 */
public class MainFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.week7_fragment_screen);

        View viewById = findViewById(R.id.rightFragment);
        if (viewById != null) {
            MainFragment fragment = new MainFragment();
            Bundle args = new Bundle();
            args.putString(MainFragment.ARG_TEXT, "Other fragment");
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().add(R.id.rightFragment, fragment).commit();
        }
    }

    public static class MainFragment extends Fragment {

        public static final String ARG_TEXT = "text";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            Bundle arguments = getArguments();

            View inflate = inflater.inflate(R.layout.weeek7_fragment, container, false);

            if (arguments != null) {
                String text = arguments.getString(ARG_TEXT);
                TextView txtText = (TextView) inflate.findViewById(R.id.txtText);
                txtText.setText(text);
            }

            return inflate;
        }
    }
}
