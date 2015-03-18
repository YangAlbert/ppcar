package com.pp.rentcar.widget;

import java.util.List;

import com.pp.rentcar.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author: wangjishan
 * @类 说 明: 下拉的 Popwindow
 * @version 1.0
 * @创建时间：2014-12-23 下午2:13:47
 * 
 */
public class SpinnerPopWindow extends PopupWindow implements
		OnItemClickListener {

	private Context mContext;
	private ListView mListView;
	private IOnItemSelectListener mItemSelectListener;

	public static interface IOnItemSelectListener{
		public void onItemClick(int pos);
	};
	
	public SpinnerPopWindow(Context context, List<String> values) {
		super();
		mContext = context;
		init(values);
	}

	private void init(List<String> values) {
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.spiner_window_layout, null);
		setContentView(view);
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);

		setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x00);
		setBackgroundDrawable(dw);

		mListView = (ListView) view.findViewById(R.id.lv);

		AbstractSpinerAdapter mAdapter = new AbstractSpinerAdapter(values);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	public void setItemListener(IOnItemSelectListener listener){
		mItemSelectListener = listener;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		dismiss();
		if (mItemSelectListener != null){
			mItemSelectListener.onItemClick(position);
		}
	}

	private class AbstractSpinerAdapter extends BaseAdapter {

		private List mStringList;
		private LayoutInflater inflater;

		public AbstractSpinerAdapter(List<String> StringList) {
			super();
			inflater = LayoutInflater.from(mContext);
			mStringList = StringList;
		}

		@Override
		public int getCount() {
			return mStringList.size();
		}

		@Override
		public Object getItem(int position) {
			return mStringList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			viewHolder holder;
			if (convertView == null) {
				holder = new viewHolder();
				convertView = inflater.inflate(R.layout.list_item_layout, null);
				holder.tv_content = (TextView) convertView
						.findViewById(R.id.tv_content);
				convertView.setTag(holder);
			} else {
				holder = (viewHolder) convertView.getTag();
			}
			holder.tv_content.setText(mStringList.get(position).toString());
			return convertView;
		}

		private class viewHolder {
			private TextView tv_content;
		}
	}

}
