package test.wightview.numeditext;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by zhouchao on 2016/7/29.
 */
public class NumTextWatcher implements TextWatcher {
    private EditText editText;
    /**
     * 设小数点后最多可输入2位数字
     *（大于7位时讲转成科学计数法，建议在7以内)小数点前，数值个数入：maxNumber = 3 ；123.00
     */
    private int maxNumber;
    public NumTextWatcher(EditText editText, int maxNum ) {
        this.editText = editText;
        this.maxNumber = maxNum;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
                if (s.toString().indexOf(".") > maxNumber) {
                    s = s.subSequence(0, start).toString() + s.subSequence(start + 1, s.length()).toString();
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
            } else {
                if (s.length() > maxNumber) {
                    s = s.subSequence(0, start).toString() + s.subSequence(start + 1, s.length()).toString();
                    editText.setText(s);
                    editText.setSelection(s.length());
                }
            }
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                editText.setText(s);
                editText.setSelection(2);
            }

            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    editText.setText(s.subSequence(0, 1));
                    editText.setSelection(1);
                    return;
                }
            }

    }
    @Override
    public void afterTextChanged(Editable s) {
    }
}
