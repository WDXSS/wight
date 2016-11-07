package test.wightview.item;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/7/29.
 */
public class ItemViewActivity extends AppCompatActivity implements View.OnClickListener{

    private ItemView mItemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        mItemView = (ItemView) findViewById(R.id.item);
        initItemView();
    }

    private void initItemView() {
        List<ItemBundle> list = new ArrayList<>();
        ItemBundle item1 = new ItemBundle();
        item1.key = 1001;
        item1.imgId = R.mipmap.icon_discovery_boss;
        item1.text = "周一";
        item1.listener = this;
        list.add(item1);

        ItemBundle item2 = new ItemBundle();
        item2.key = 1002;
        item2.imgId = R.mipmap.icon_discovery_boss;
        item2.text = "周二";
        item2.listener = this;
        list.add(item2);

        ItemBundle item3 = new ItemBundle();
        item3.key = 1003;
        item3.imgId = R.mipmap.icon_discovery_boss;
        item3.text = "周三";
        item3.listener = this;
        list.add(item3);

        ItemBundle item4 = new ItemBundle();
        item4.key = 1004;
        item4.imgId = R.mipmap.icon_discovery_boss;
        item4.text = "周四";
        item4.listener = this;
        list.add(item4);

        ItemBundle item5 = new ItemBundle();
        item5.key = 1005;
        item5.imgId = R.mipmap.icon_discovery_boss;
        item5.text = "周五";
        item5.listener = this;
        list.add(item5);

        ItemBundle item6 = new ItemBundle();
        item6.key = 1006;
        item6.imgId = R.mipmap.icon_discovery_boss;
        item6.text = "周末";
        item6.listener = this;
        list.add(item6);

        mItemView.addItems(list);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1001:
                Toast.makeText(ItemViewActivity.this, "周一", Toast.LENGTH_SHORT).show();
                mItemView.setItemHide(1001);
                break;
            case 1002:
                Toast.makeText(ItemViewActivity.this, "周二", Toast.LENGTH_SHORT).show();
                mItemView.setItemHide(1002);
                break;
            case 1003:
                Toast.makeText(ItemViewActivity.this, "周三", Toast.LENGTH_SHORT).show();
                mItemView.setItemHide(1003);
                break;
            case 1004:
                Toast.makeText(ItemViewActivity.this, "周四", Toast.LENGTH_SHORT).show();
                mItemView.setItemHide(1004);
                break;
            case 1005:
                Toast.makeText(ItemViewActivity.this, "周五", Toast.LENGTH_SHORT).show();
                mItemView.setItemHide(1005);
                break;
            case 1006:
                Toast.makeText(ItemViewActivity.this, "周末", Toast.LENGTH_SHORT).show();
                mItemView.setItemHide(1006);
                break;
        }
    }
}
