package test.wightview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/8/10.
 */
public class CustomView extends RelativeLayout {
    private static final String TAG = "CustomView";
    private Context con;
    private TextView customName;
    private TextView customTextContent;
    private EditText customEditContent;
    private ImageView customInto;

    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.con = context;
        initView();
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs) {

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView);
        CharSequence strName = ta.getText(R.styleable.CustomView_custom_name);
        CharSequence strTextContent = ta.getString(R.styleable.CustomView_custom_text_content);
        CharSequence strEditContent = ta.getText(R.styleable.CustomView_custom_edit_content);
        CharSequence strEidtHint = ta.getText(R.styleable.CustomView_custom_edit_hint);
        int editVisible = ta.getInt(R.styleable.CustomView_custom_edit_visible, GONE);
        int textVisible = ta.getInt(R.styleable.CustomView_custom_text_visible, VISIBLE);

        Log.d(TAG, "getAttrs: strShow = " + editVisible + ", textVisible = " + textVisible);
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String value = attrs.getAttributeValue(i);
            String name = attrs.getAttributeName(i);
            Log.d(TAG, "getAttrs: name = " + name + ", value = " + value);
        }

        ta.recycle();
        setNameText(strName);
        setTextContent(strTextContent);
        setEditHint(strEidtHint);
        setEditVisible(editVisible == VISIBLE);
        setTextVisible(textVisible == VISIBLE);
    }

    private void initView() {
        View view = LayoutInflater.from(con).inflate(R.layout.custom_view, null);
        customName = (TextView) view.findViewById(R.id.custom_name);
        customTextContent = (TextView) view.findViewById(R.id.custom_text_content);
        customEditContent = (EditText) view.findViewById(R.id.custom_edit_content);
        customInto = (ImageView) view.findViewById(R.id.custom_into_img);
        addView(view);
    }

    private void setNameText(CharSequence text) {
        customName.setText(text);
    }

    public void setTextContent(CharSequence text) {
        customTextContent.setText(text);
    }

    public void setEditHint(CharSequence text) {
        customEditContent.setHint(text);
    }

    private void setEditVisible(boolean visible){
        customEditContent.setVisibility(visible? VISIBLE : GONE);
    }private void setTextVisible(boolean visible){
        customTextContent.setVisibility(visible? VISIBLE : GONE);
    }
}
