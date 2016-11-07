package test.wightview.overtextview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/9/27.
 */

public class OverTextViewActivity extends AppCompatActivity {

    private String testStr = "飞啊飞阿公二阿哥半个熬过二阿哥哦啊个啊个奥格阿黑哥阿黑哥" +
            "画个阿文哥二阿哥啊我为给和阿给哦娃和个案歌华我刚和哇哦广告  " +
            "个哦啊还个哦啊哈共轭阿和哦啊个个哦阿哥和爱国哦啊恶化个哦为个爱好噶红歌哈鬼  " +
            "好歌哦啊还个哦年宏观  个哦阿黑哥奥格奥委会 共和哦啊还搞个后会噶个红花各哦啊";

    private ListView mListView;
    private List<String> data = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtextview);
        initView();
        mListView = (ListView) findViewById(R.id.listview);
        myAdapter = new MyAdapter(this, data);
        mListView.setAdapter(myAdapter);

    }

    private void initView(){

        for (int i = 0; i < 20; i++) {
            data.add("第" + i + "条 ： " +testStr);
        }

        final OverTextView tv_over = (OverTextView) findViewById(R.id.tv_over);
        tv_over.setText(testStr);
        tv_over.setBackColor("#5ED7F2");
        tv_over.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onGlobalLayout() {
                        tv_over.replaceTips();
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                            tv_over.getViewTreeObserver()
//                                    .removeOnGlobalLayoutListener(this);
//                        } else {
//                            tv_over.getViewTreeObserver()
//                                    .removeGlobalOnLayoutListener(this);
//                        }
                    }
                });
        tv_over.setOnCustomLinkClickListener(new OverTextView.OnCustomLinkClickListener() {
            @Override
            public void onCustomLinkClick() {
                Toast.makeText(OverTextViewActivity.this, "点我干嘛", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
