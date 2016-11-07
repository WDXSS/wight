package test.wightview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/7/28.
 * <p/>
 * 两个list，一个contentview ，一line
 * 1。addItems 传入List
 * 2。setItemHide 隐藏item
 * <p/>
 * 原理：
 * items 存放item
 * lines 分割线，（顶部和底部分割线，不包含在lines）
 * line和item分组方式：item与其顶部的line，为一组 (第一个分割线默认状态:GONE)
 * <p/>
 * item 中View.setTag(boolean); true为第一条，用于判断是不是第一条
 */
public class ItemView extends LinearLayout {
    private static final String TAG = "ItemView";
    private SparseArray<View> items = new SparseArray<>();
    private SparseArray<View> lines = new SparseArray<>();

    private Context con;
    private LinearLayout itemContainer;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.con = context;
        initView();
    }


    private void initView() {
        View view = LayoutInflater.from(con).inflate(R.layout.view_item, null);
        itemContainer = (LinearLayout) view.findViewById(R.id.item_container);
        addView(view);
    }

    public void addItems(List<ItemBundle> array) {
        for (int i = 0; i < array.size(); i++) {
            ItemBundle item = array.get(i);
            addItem(item,i);
        }
    }

    private void addItem(ItemBundle item , int index){

        View view = LayoutInflater.from(con).inflate(R.layout.view_content, null);
        RelativeLayout layou = (RelativeLayout) view.findViewById(R.id.layout);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView txt = (TextView) view.findViewById(R.id.text);

        layou.setId(item.key);
        layou.setOnClickListener(item.listener);
        img.setImageResource(item.imgId);
        txt.setText(item.text);

        View line = new View(con);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        layoutParams.setMargins(50, 0, 0, 0);
        line.setBackgroundColor(getResources().getColor(R.color.colorLine));
        line.setLayoutParams(layoutParams);

        lines.append(item.key, line);
        items.append(item.key, view);

        boolean isTopView = false;
        if(index == 0){
            line.setVisibility(View.GONE);
            isTopView = true;
        }
        view.setTag(isTopView);

        itemContainer.addView(line);
        itemContainer.addView(view);
    }

    public void setItemHide(int key) {
        View view = items.get(key);
        View line = lines.get(key);
        int index = items.indexOfKey(key);
        Log.d(TAG, "removeItme: index = " + index);
        if (view != null) {
            view.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            if (hasNextView(key) && (boolean) view.getTag()) {
                setNextViewToTop(key);
            }
            if ((boolean) view.getTag()) {
                view.setTag(false);
            }
        }
    }

    /**
     * 判断是否有下一个item
     * items.size() && view.getVisibility()
     *
     * @param key
     * @return
     */
    private boolean hasNextView(int key) {
        boolean hasNext = false;
        int index = items.indexOfKey(key);
        index++;
        if (index >= items.size()) {
            hasNext = false;
        } else {
            for (int i = index; i < items.size(); i++) {
                View view = items.get(items.keyAt(i));
                if (view.getVisibility() == VISIBLE) {
                    hasNext = true;
                    break;
                }
            }
        }
        return hasNext;
    }

    /**
     * view.setTag（true）
     *
     * @param key
     */
    private void setNextViewToTop(int key) {
        View view = items.get(key);
        int index = items.indexOfKey(key);
        if (view != null) {
            boolean b = true;
            while (index < items.size() && b) {
                index++;
                View nextView = items.get(items.keyAt(index));
                if (nextView != null && nextView.getVisibility() != GONE) {
                    b = false;
                    nextView.setTag(true);
                    View nextLine = lines.get(lines.keyAt(index));
                    nextLine.setVisibility(View.GONE);
                }
            }
        }
    }
}
