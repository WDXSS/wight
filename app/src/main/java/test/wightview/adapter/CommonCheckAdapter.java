package test.wightview.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import test.wightview.R;

/**
 * Created by zhouchao on 2016/8/11.
 * 带有选择器的adapter
 * 逻辑：CommonAdapter + FrameLayout.addview(传入的listview布局)
 *
 * 1.默认checkBox显示；
 *   {@link #showCheckBox() 显示checkBox}
 *   {@link #hideCheckBox() 隐藏checkBox}
 * 2.存放选中的T checkedList，存放格式：key=position，value = T;
 *   {@link #refreshCheckList(boolean, int)}
 * 3.返回选中的集合List<T>
 *     {@link #getCheckedList()}
 * 4.出入选中的集合 ,集合格式：SparseArray key=position，value = T
 *   {@link #setCheckedList(SparseArray)}
 *   **注意：集合保存key==position
 *      所以传入SparseArray.put<position,T>
 */
public abstract class CommonCheckAdapter<T> extends CommonAdapter<T> implements View.OnClickListener {

    private OnCheckedListener listener;
    private boolean isShowCheckBox = true;
    private SparseArray<T> checkedList = new SparseArray<T>();

    public CommonCheckAdapter(Context context, List<T> data, int layoutId, OnCheckedListener listener) {
        super(context, data, layoutId);
        this.listener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.comment_check_adapter, parent, false);
            View childView = mInflater.inflate(mLayoutId, null);
            FrameLayout content = (FrameLayout) convertView.findViewById(R.id.content);
            content.addView(childView);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setChecked(position,(ImageView) holder.get(R.id.checkbox));
        convert(position, holder, mData.get(position));
        if (isShowCheckBox) {
            holder.get(R.id.checkbox).setVisibility(View.VISIBLE);
        } else {
            holder.get(R.id.checkbox).setVisibility(View.GONE);
        }
        holder.get(R.id.checkbox).setTag(position);
        holder.get(R.id.checkbox).setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        boolean isChecked = v.isSelected();
        switch (v.getId()) {
            case R.id.checkbox:
                v.setSelected(!isChecked);
                listener.onChangeChecked(position, !isChecked);
                refreshCheckList(!isChecked, position);
                break;
        }
    }

    public void showCheckBox() {
        isShowCheckBox = true;
        notifyDataSetChanged();
    }

    public void hideCheckBox() {
        isShowCheckBox = false;
        notifyDataSetChanged();
    }
    //更新存放选中的SparseArray
    private void refreshCheckList(boolean isChecked, int position) {
        if (isChecked) {
            checkedList.put(position, mData.get(position));
        } else {
            checkedList.remove(position);
        }
    }
    public void setCheckedList(SparseArray<T>  list){
        if(list != null){
            checkedList = list.clone();
            notifyDataSetChanged();
        }
    }
    
    public List<T> getCheckedList(){
        List<T> checkedData = new ArrayList<>();
        for (int i = 0; i < checkedList.size(); i++) {
            checkedData.add(checkedList.valueAt(i));
        }
        return checkedData;
    }
    /**
     * 设置选中状态
     */
    private void setChecked(int position,ImageView img){
       T t = checkedList.get(position);
        if(t != null){
            img.setSelected(true);
        }else{
            img.setSelected(false);
        }
    }
    public interface OnCheckedListener {
        /**
         * 修改model里的boolean值
         */
        void onChangeChecked(int position, boolean isChecked);
    }
}


