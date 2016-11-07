package test.wightview.overtextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.List;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/9/27.
 */

public class MyAdapter extends BaseAdapter {

    private static final String TAG = "MyAdapter";

    private List<String> data;
    private Context con;
    public MyAdapter(Context con ,List<String> data) {
        this.data = data;
        this.con = con;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HolderView holderView ;
        if(convertView == null ){
            holderView = new HolderView();
            convertView = LayoutInflater.from(con).inflate(R.layout.over_text_item,null);
            holderView.textview = (OverTextView) convertView.findViewById(R.id.tv_over);
            convertView.setTag(holderView);
        }else{
            holderView = (HolderView) convertView.getTag();
        }
        holderView.textview.setText(data.get(position));
        Log.d(TAG, "starOverFlow:  行数 = " +holderView.textview.getLineCount()) ;
        holderView.textview.setBackColor("#5ED7F2");
        holderView.textview.replaceTips();


//        holderView.textview.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener() {
//
//                    @SuppressLint("NewApi")
//                    @Override
//                    public void onGlobalLayout() {
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                            holderView.textview.getViewTreeObserver()
//                                    .removeOnGlobalLayoutListener(this);
//                        } else {
//                            holderView.textview.getViewTreeObserver()
//                                    .removeGlobalOnLayoutListener(this);
//                        }
//                        Log.d(TAG, "onGlobalLayout: 获取的高度 == " +holderView.textview.getHeight());
//                        holderView.textview.replaceTips();
//                    }
//                });
        holderView.textview.setOnCustomLinkClickListener(new OverTextView.OnCustomLinkClickListener() {
            @Override
            public void onCustomLinkClick() {
                Toast.makeText(con, "点我干嘛", Toast.LENGTH_LONG)
                        .show();
            }
        });

        return convertView;
    }

    private final class  HolderView{
        OverTextView textview;
    }
}
