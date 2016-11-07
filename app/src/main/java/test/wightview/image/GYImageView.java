package test.wightview.image;

import android.content.Context;
import android.util.AttributeSet;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by zhouchao on 2016/11/3.
 */

public class GYImageView extends SimpleDraweeView {

    public GYImageView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }
    public GYImageView(Context context) {
        super(context);
    }

    public GYImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GYImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GYImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



}
