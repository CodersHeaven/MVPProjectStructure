package projects.in.projectstructure.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import projects.in.projectstructure.R;

/**
 * Created by prime on 16-12-2017.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    String customFont;

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        style(context, attrs);

    }

    private void style(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomFontTextView);
        int cf = a.getInteger(R.styleable.CustomFontTextView_fontName, 0);
        int fontName = 0;
        switch (cf) {
            case 1:
                fontName = R.string.Gotham_Book;
                break;
            case 2:
                fontName = R.string.Gotham_Medium;
                break;
            default:
                fontName = R.string.Gotham_Book;
                break;
        }

        customFont = getResources().getString(fontName);

        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "fonts/" + customFont + ".ttf");
        setTypeface(tf);
        a.recycle();
    }
}
