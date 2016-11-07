package test.wightview.adapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by RenT on 2016/4/13.
 */
public class ViewHolder {
    private View mConvertView;
    private SparseArray<View> mViews;

    public ViewHolder(View convertView) {
        this.mConvertView = convertView;
        this.mViews = new SparseArray<>();
    }

    public View get(int id) {
        View v = mViews.get(id);
        if (v == null) {
            v = mConvertView.findViewById(id);
            mViews.put(id, v);
        }
        return v;
    }

    public void setText(int id, CharSequence text) {
        ((TextView) get(id)).setText(text);
    }

//    public void setImage(int id, String url) {
//        ((GYImageView) get(id)).setImage(url);
//    }

}
