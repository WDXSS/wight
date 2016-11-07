package test.wightview.numeditext;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * Created by zhouchao on 2016/7/29.
 * 输入数字
 */
public class NumEditText extends EditText {
    private int maxNumber = 3;

    public NumEditText(Context context) {
        super(context);
        initView();
    }

    public NumEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NumEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setInputType(EditorInfo.TYPE_CLASS_PHONE);
        addTextWatcher();
    }
    private void addTextWatcher() {

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        setText(s);
                        setSelection(s.length());
                    }
                    if (s.toString().indexOf(".") > maxNumber) {
                        s = s.subSequence(0, start).toString() + s.subSequence(start + 1, s.length()).toString();
                        setText(s);
                        setSelection(s.length());
                    }
                } else {
                    if (s.length() > maxNumber) {
                        s = s.subSequence(0, start).toString() + s.subSequence(start + 1, s.length()).toString();
                        setText(s);
                        setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    setText(s);
                    setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        setText(s.subSequence(0, 1));
                        setSelection(1);
                        return;
                    }
                }
            }
        });
    }

    /**
     * 设小数点后最多可输入2位数字
     *
     * @param maxNumber （大于7位时讲转成科学计数法，建议在7以内)小数点前，数值个数入：maxNumber = 3 ；123.00
     */
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

}
