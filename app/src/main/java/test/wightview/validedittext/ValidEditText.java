package test.wightview.validedittext;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import test.wightview.R;


/**
 * 带校验功能的EditText，调用{@link #validate()}即可对数据进行校验，并会弹出toast进行提示，当然，您也可以自定义提示信息<br/>
 * 自定义属性：<br/>
 * 1.nullable 是否允许为空，默认是true<br/>
 * 2.range 长度范围，用逗号分隔。如：6,20，表示[6, 20]<br/>
 * 3.maxLength 最大长度（如果设置了range，则不对该属性进行判断）<br/>
 * 4.minLength 最小长度（如果设置了range，则不对该属性进行判断）<br/>
 * 5.toast 为true时，数据校验不通过则会弹toast；反之不处理。默认为true<br/>
 * Created by RenTao on 2016/7/13.
 */
public class ValidEditText extends EditText implements Validation {
    private boolean mNullable;
    private int mMinLength, mMaxLength;
    private String mRange;
    private String mNullableMsg, mMaxLengthMsg, mMinLengthMsg, mRangeMsg;
    private boolean mToast;


    public ValidEditText(Context context) {
        this(context, null);
    }

    public ValidEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ValidEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ValidEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ValidEditText);
        mNullable = typedArray.getBoolean(R.styleable.ValidEditText_nullable, true);
        mNullableMsg = getString(typedArray, R.styleable.ValidEditText_nullableMsg);
        mMaxLength = typedArray.getInt(R.styleable.ValidEditText_maxLength, -1);
        mMaxLengthMsg = getString(typedArray, R.styleable.ValidEditText_maxLengthMsg);
        mMinLength = typedArray.getInt(R.styleable.ValidEditText_minLength, -1);
        mMinLengthMsg = getString(typedArray, R.styleable.ValidEditText_minLengthMsg);
        mRange = typedArray.getString(R.styleable.ValidEditText_range);
        mRangeMsg = getString(typedArray, R.styleable.ValidEditText_rangeMsg);
        mToast = typedArray.getBoolean(R.styleable.ValidEditText_toast, true);

        typedArray.recycle();
    }

    private String getString(TypedArray typedArray, int id) {
        int resId = typedArray.getResourceId(id, 0);
        return resId > 0 ? getResources().getString(resId) : typedArray.getString(id);
    }

    public ValidEditText setNullable(boolean nullable) {
        this.mNullable = nullable;
        return this;
    }

    public ValidEditText setMinLength(int minLength) {
        this.mMinLength = minLength;
        return this;
    }

    public ValidEditText setMaxLength(int maxLength) {
        this.mMaxLength = maxLength;
        return this;
    }

    public ValidEditText setRange(String range) {
        this.mRange = range;
        return this;
    }

    public ValidEditText setNullableMsg(String nullableMsg) {
        this.mNullableMsg = nullableMsg;
        return this;
    }

    public ValidEditText setMaxLengthMsg(String maxLengthMsg) {
        this.mMaxLengthMsg = maxLengthMsg;
        return this;
    }

    public ValidEditText setMinLengthMsg(String minLengthMsg) {
        this.mMinLengthMsg = minLengthMsg;
        return this;
    }

    public ValidEditText setRangeMsg(String rangeMsg) {
        this.mRangeMsg = rangeMsg;
        return this;
    }

    public ValidEditText setToastEnable(boolean toast) {
        this.mToast = toast;
        return this;
    }

    @Override
    public boolean validate() {
        if (!mNullable && TextUtils.isEmpty(getText().toString().trim())) {
            toast(mNullableMsg, "内容不能为空");
            return false;
        }
        int textLength = getText().toString().trim().length();
        String[] len;
        if (mRange != null && (len = mRange.split(",")).length == 2) {
            int min = Integer.parseInt(len[0].trim());
            int max = Integer.parseInt(len[1].trim());
            if (max > min && (textLength < min || textLength > max)) {
                toast(mRangeMsg, "长度需要在" + min + "到" + max + "之间");
                return false;
            }
        } else {
            if (textLength < mMinLength) {
                toast(mMinLengthMsg, "长度不能少于" + mMinLength);
                return false;
            }
            if (mMaxLength > 0 && mMaxLength > mMinLength && textLength > mMaxLength) {
                toast(mMaxLengthMsg, "长度不能超过" + mMaxLength);
                return false;
            }
        }
        return true;
    }

    private void toast(String msg, String defaultMsg) {
        if (mToast) {
            Toast.makeText(getContext(), msg == null ? defaultMsg : msg, Toast.LENGTH_SHORT).show();
        }
    }
}
