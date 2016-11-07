package test.wightview.image;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.wightview.BaseActivity;
import test.wightview.R;

/**
 * Created by zhouchao on 2016/11/3.
 */

public class ImageActivity extends BaseActivity {


    @BindView(R.id.img)
    GYImageView mImg;
    @BindView(R.id.img2)
    ImageView img2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_main);
        ButterKnife.bind(this);
        Uri uri = Uri.parse("http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg");
        mImg.setImageURI(uri);
    }


    @OnClick({R.id.img, R.id.img2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                break;
            case R.id.img2:
                break;
        }
    }
}
