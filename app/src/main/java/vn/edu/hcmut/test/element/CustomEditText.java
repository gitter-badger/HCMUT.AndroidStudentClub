package vn.edu.hcmut.test.element;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {

	public CustomEditText(Context context) {
		super(context);
		setFont();
	}

	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setFont();
	}

	public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFont();
	}

	private void setFont() {
        if (isInEditMode()) {
            return;
        }

		Typeface font = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/segoe_ui_light.ttf");
		setTypeface(font, Typeface.BOLD);
	}

}