package test.wightview.numeditext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.wightview.R;

public class EditTextActivity extends AppCompatActivity {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.numTXT)
    NumEditText numTXT;
    @BindView(R.id.num)
    EditText num;
    @BindView(R.id.fuhao)
    TextView fuhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        ButterKnife.bind(this);

        NumEditText text = (NumEditText) findViewById(R.id.numTXT);
        text.setMaxNumber(5);

        //重写TextWatcher
        EditText editText = (EditText) findViewById(R.id.num);
        //  或者 editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.addTextChangedListener(new NumTextWatcher(editText, 5));
        String test = String.format(getString(R.string.old), 23);
        fuhao.setText(test);
    }


    @OnClick({R.id.tv, R.id.fuhao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                break;
            case R.id.fuhao:
                break;
        }
    }
}
