package test.wightview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.wightview.R;

/**
 * 显示的第一Fragment
 * Created by zhouchao on 2016/9/22.
 */

public class FragmentFrist extends Fragment {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, true);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.textView, R.id.button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView:

                break;
            case R.id.button:
                break;
        }
    }
}
