package test.wightview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.wightview.adapter.CommonCheckActivity;
import test.wightview.customview.CustomActivity;
import test.wightview.fragment.FragmentMainActivity;
import test.wightview.image.ImageActivity;
import test.wightview.item.ItemViewActivity;
import test.wightview.numeditext.EditTextActivity;
import test.wightview.overtextview.OverTextViewActivity;
import test.wightview.validedittext.TextViewActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.item)
    Button item;
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.customView)
    Button customView;
    @BindView(R.id.check_listview)
    Button checkListview;
    @BindView(R.id.btn_fragment)
    Button btnFragment;
    @BindView(R.id.over_textview)
    Button overTextview;
    @BindView(R.id.textview)
    Button textview;
    @BindView(R.id.imgview)
    Button imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv, R.id.item, R.id.edit, R.id.customView,
            R.id.check_listview, R.id.btn_fragment,
            R.id.over_textview, R.id.textview,R.id.imgview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item:
                startActivity(new Intent(MainActivity.this, ItemViewActivity.class));
                break;
            case R.id.edit:
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
                break;
            case R.id.customView:
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
                break;
            case R.id.check_listview:
                startActivity(new Intent(MainActivity.this, CommonCheckActivity.class));
                break;
            case R.id.btn_fragment:
                startActivity(new Intent(MainActivity.this, FragmentMainActivity.class));
                break;
            case R.id.over_textview:
                startActivity(new Intent(MainActivity.this, OverTextViewActivity.class));
                break;
            case R.id.textview:
                startActivity(new Intent(MainActivity.this, TextViewActivity.class));
                break;
            case R.id.imgview:
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
                break;
        }
    }


}
