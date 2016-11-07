package test.wightview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import test.wightview.R;

/**
 *  定制属性的TextView
 * Created by zhouchao on 2016/8/2.
 * (熟悉自定义属性，及构建
 *  没使用价值)
 */
public class CustomTextView extends TextView{
    private static final String TAG = "CustomTextView";
    private int textSize ;
    private int textColor;
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(attrs);
    }

    private void getAttrs(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        textSize = ta.getDimensionPixelSize(R.styleable.CustomTextView_text_size,16);
        textColor = ta.getColor(R.styleable.CustomTextView_text_color, 0xfffff000);
        for (int i = 0; i < attrs.getAttributeCount() ; i++) {
            String value = attrs.getAttributeValue(i);
            String name = attrs.getAttributeName(i);
            Log.d(TAG, "getAttrs: name = " + name +  ", value = " + value );
        }
        setColor(textColor);
        setSize(textSize);
        ta.recycle();
    }

    private void setColor(int color){
        setTextColor(color);
    }
    private void setSize(int size){
        setTextSize(size);
    }
}
