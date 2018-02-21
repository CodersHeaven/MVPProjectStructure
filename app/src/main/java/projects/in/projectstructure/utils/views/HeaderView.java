package projects.in.projectstructure.utils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import projects.in.projectstructure.R;

/**
 * Created by prime on 17-12-2017.
 */

public class HeaderView extends LinearLayout {

    private Context context;
    private String heading;

    @BindView(R.id.txtHeader)
    CustomTextView txtHeader;

    @BindView(R.id.viewUnderline)
    View viewUnderline;

    public HeaderView(Context context) {
        super(context);
        this.context = context;

        createView();
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.attrs_header, 0, 0);

        try {
            heading = a.getString(R.styleable.attrs_header_heading);
        } finally {
            a.recycle();
        }

        createView();
    }

    private void createView() {
        inflate(context, R.layout.view_header, this);
        ButterKnife.bind(this);

        setHeading(heading);
    }

    public void setHeading(String heading) {
        if (TextUtils.isEmpty(heading))
            viewUnderline.setVisibility(View.GONE);
        else
            this.txtHeader.setText(heading);
    }
}
