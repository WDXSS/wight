package test.wightview.validedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import test.wightview.R;
import test.wightview.utik.StringUtil;

/**
 * desc: 常见EditText字数限制(默认一个汉字的长度 == 一个字母的长度 == 1)
 * Created by jiarh on 16/10/14 17:32.
 */

public class ValidLimitEditText extends ValidEditText {
    /**
     * 设置最大输入长度
     */
    private int mMaxLengthNum = 10;
    /**
     * 文本显示格式
     */
    private static final String DEFAULT_FORMATTER = "%s/%s";
    /**
     * 提示文本
     */
    private String mLimitTextShow;
    /**
     * 提示文字默认颜色
     */
    private int mNormalLimitTextColor = Color.parseColor("#787878");
    /**
     * 超过限制后提示字体的颜色
     */
    private int mWarnLimitTextColor = Color.RED;
    /**
     * 文本大小
     */
    private int mLimitTextSize = 20;
    /**
     * 是否显示提示文字 默认显示
     */
    private boolean mShowLimitText=true;
    /**
     * 是否设置清除图标 默认会显示
     */
    private boolean mShowClear;

    private int limtW, limitH;
    private Paint mLimitPaint;
    private InputFilter[] filters = new InputFilter[1];
    private Rect rect = new Rect();
    private Bitmap mClear;
    private Drawable mClearDrawable;
    private int PADING=15;




    public ValidLimitEditText(Context context) {
        this(context,null);
    }


    public ValidLimitEditText(Context context, AttributeSet attrs) {
        super(context,attrs);
        init(attrs);

    }

    public ValidLimitEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    public void setMaxLengthNum(int mMaxLengthNum) {
        this.mMaxLengthNum = mMaxLengthNum;
        if (mMaxLengthNum <= 0) {
            throw new IllegalArgumentException("必须大于0");
        }

        filters[0] = new InputFilter.LengthFilter(mMaxLengthNum);
        setFilters(filters);
    }

    private void init(AttributeSet attrs) {

        initAttr(attrs);
        setMaxLengthNum(mMaxLengthNum);
        addTextChangedListener(textWatcher);
        initPaint();
        initClearIcon();
    }

    private void initClearIcon() {
        mClear = BitmapFactory.decodeResource(getResources(),R.mipmap.btn_close_normal);
        mClearDrawable= new BitmapDrawable(getResources(),mClear);
        mClearDrawable.setBounds(0, 0, mClearDrawable.getMinimumWidth()/2, mClearDrawable.getMinimumHeight()/2);
        setCompoundDrawablePadding(PADING);


    }


    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ValidLimitEditText);
        mShowLimitText = typedArray.getBoolean(R.styleable.ValidLimitEditText_showlimitText, true);
        mMaxLengthNum = typedArray.getInteger(R.styleable.ValidLimitEditText_maxLimitTextNum, mMaxLengthNum);
        mNormalLimitTextColor = typedArray.getInt(R.styleable.ValidLimitEditText_normalTextColor, mNormalLimitTextColor);
        mWarnLimitTextColor = typedArray.getInt(R.styleable.ValidLimitEditText_warnTextColor, mWarnLimitTextColor);
        mLimitTextSize = typedArray.getInt(R.styleable.ValidLimitEditText_limitTextSize, mLimitTextSize);
        mShowClear = typedArray.getBoolean(R.styleable.ValidLimitEditText_showClear, true);
        typedArray.recycle();
    }

    private void initPaint() {
        mLimitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLimitPaint.setColor(mNormalLimitTextColor);
        mLimitPaint.setTextSize(mLimitTextSize);
        mLimitPaint.setStrokeWidth(5);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!TextUtils.isEmpty(mLimitTextShow) && mShowLimitText) {

            mLimitPaint.getTextBounds(mLimitTextShow, 0, mLimitTextShow.length(), rect);
            limtW = rect.width();
            limitH = rect.height();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(mLimitTextShow) && mShowLimitText) {

            canvas.save();
            int ww = (getMeasuredWidth()) - limtW * 3 / 2;
            int hh = (getMeasuredHeight())- limitH * 3 / 2;
            canvas.translate(ww, hh);
            canvas.drawText(mLimitTextShow, -rect.left, -rect.top, mLimitPaint);
            canvas.restore();
        }

    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (mShowLimitText) {
                mLimitTextShow = String.format(DEFAULT_FORMATTER, getText().toString().length(),
                        mMaxLengthNum + "");
                if (s.length() >= mMaxLengthNum) {
                    mLimitPaint.setColor(mWarnLimitTextColor);
                } else {
                    mLimitPaint.setColor(mNormalLimitTextColor);
                }
                invalidate();
            }

            setSelection(getText().toString().length());
            if (StringUtil.isNotBlank(s.toString())){
                showClearIcon();
            }else {
                hideClearIcon();
            }
        }


    };


    private void showClearIcon() {
        if (mShowClear)
        setCompoundDrawables(null,null,mClearDrawable,null);
    }
    private void hideClearIcon() {
        setCompoundDrawables(null,null,null,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                    Drawable drawableRight = getCompoundDrawables()[2] ;
                    if (drawableRight != null && event.getRawX() >= (getRight() - 2*drawableRight.getBounds().width())) {
                        setText("");
                        return true ;
                    }

                break;
        }

        return super.onTouchEvent(event);
    }

    public void setmShowLimitText(boolean mShowLimitText) {
        this.mShowLimitText = mShowLimitText;
    }

    public void setmLimitTextSize(int mLimitTextSize) {
        this.mLimitTextSize = mLimitTextSize;
    }

    public void setmNormalLimitTextColor(int mNormalLimitTextColor) {
        this.mNormalLimitTextColor = mNormalLimitTextColor;
    }

    public void setmWarnLimitTextColor(int mWarnLimitTextColor) {
        this.mWarnLimitTextColor = mWarnLimitTextColor;
    }

    public void setmShowClear(boolean mShowClear) {
        this.mShowClear = mShowClear;
    }


}
