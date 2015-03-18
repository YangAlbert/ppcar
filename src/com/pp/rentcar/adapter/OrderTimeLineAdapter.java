package com.pp.rentcar.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.pp.rentcar.R;
import com.pp.rentcar.entity.OrderTimeLineEntity;
import com.pp.rentcar.entity.RestaurantEntity;

public class OrderTimeLineAdapter extends BaseAdapter{

	private Activity mactivity;
	private List<OrderTimeLineEntity> mlist;
	private LayoutInflater inflater;
	public OrderTimeLineAdapter(Activity activity,
			List<OrderTimeLineEntity> list) {
		this.mactivity=activity;
		this.mlist=list;
		this.inflater=LayoutInflater.from(mactivity);
	}

	@Override
	public int getCount() {
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.order_time_point_item, null);
            holder = new ViewHolder();
            holder.msg = (TextView) convertView
                    .findViewById(R.id.time_line_item_msg);
            holder.time = (TextView) convertView
            		.findViewById(R.id.time_line_item_time);
            holder.logo = (ImageView) convertView
            		.findViewById(R.id.time_line_item_icon);
            convertView.setTag(holder);
            
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.msg.setText(mlist.get(position).getMsg());
        holder.time.setText(mlist.get(position).getTime());
        
        ImageLoader.getInstance().displayImage(mlist.get(position).getLogo(), holder.logo);
		
		return convertView;
	}
	public class ViewHolder {
		public ImageView logo;
		public TextView msg;
		public TextView time;
		
	}

}
