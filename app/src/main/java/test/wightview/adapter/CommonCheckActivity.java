package test.wightview.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/8/11.
 */
public class CommonCheckActivity extends FragmentActivity {
    private static final String TAG = "CommonCheckActivity";
    private CommonCheckAdapter<CheckTest> adapter;
    private List<CheckTest> data = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_check_main);
        initCommonAdapter();
        initData();

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        final TextView text = (TextView) findViewById(R.id.testname);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CheckTest> list = adapter.getCheckedList();
                text.setText("选中的个数： "+ list.size() + "");
                for (int i = 0; i < list.size(); i++) {
                    Log.d(TAG, "onClick: position = " + list.get(i).name + "   ,");
                }
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               adapter.hideCheckBox();
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.showCheckBox();
            }
        });
//        initFragment();
    }

    private void initData() {

        for (int i = 0; i < 20; i++) {
            CheckTest test = new CheckTest();
            if (i % 3 == 0) {
                test.isChecked = true;
            } else {
                test.isChecked = false;
            }
            test.name = "name : " + i;
            data.add(test);
        }
        SparseArray<CheckTest> ar = new SparseArray<>();
        for (int j = 0; j < data.size() ; j++) {
            CheckTest ct = data.get(j);
            if(ct.isChecked){
                ar.put(j,ct);
            }
        }
        adapter.setCheckedList(ar);
    }

    private void initCommonAdapter() {

        CommonCheckAdapter.OnCheckedListener listener = new CommonCheckAdapter.OnCheckedListener() {
            @Override
            public void onChangeChecked(int position, boolean isChecked) {
                data.get(position).isChecked = isChecked;
                Log.d(TAG, "setCheccked: position == " + position + ", isChecked = " + isChecked);
            }
        };

        adapter = new CommonCheckAdapter<CheckTest>(CommonCheckActivity.this, data, R.layout.comment_test_item, listener) {
            @Override
            protected void convert(int position, ViewHolder holder, CheckTest model) {
                holder.setText(R.id.text, model.name);
            }
        };
       adapter.showCheckBox();

    }
}
