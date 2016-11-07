package test.wightview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.wightview.R;

/**
 * 装在Fragment的Activity
 * Created by zhouchao on 2016/9/22.
 */

public class FragmentMainActivity extends AppCompatActivity {

    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button2, R.id.button1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                Toast.makeText(this, button2.getText() ,Toast.LENGTH_SHORT).show();
                break;
            case R.id.button1:
                Toast.makeText(this, button1.getText() ,Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
