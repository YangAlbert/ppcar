package com.pp.rentcar.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pp.rentcar.R;
import com.pp.rentcar.adapter.RestaurantDetailAdapter;
import com.pp.rentcar.util.InjectView;
import com.pp.rentcar.util.Injector;
import com.pp.rentcar.widget.SpinnerPopWindow;
import com.pp.rentcar.widget.SpinnerPopWindow.IOnItemSelectListener;
import com.pp.rentcar.widget.stickylistheaders.StickyListHeadersListView;


public class RestaurantDetailActivity extends Activity implements
		AdapterView.OnItemClickListener,
		StickyListHeadersListView.OnHeaderClickListener,
		StickyListHeadersListView.OnStickyHeaderOffsetChangedListener,
		StickyListHeadersListView.OnStickyHeaderChangedListener {

	private RestaurantDetailAdapter mAdapter;
	private boolean fadeHeader = true;
	@InjectView(R.id.linear_above_toHome)
	private LinearLayout above_toHome;
	@InjectView(R.id.tv_common_above_head)
	private TextView above_tittle;
	@InjectView(R.id.iv_head_left)
	private ImageView head_left;
	@InjectView(R.id.tv_common_above_head)
	private TextView head_tittle;
	@InjectView(R.id.food_list_shipping_fee)
	private TextView order_cart;
	@InjectView(R.id.food_list_take_order_button)
	private TextView mPayMoney;
	private String restaurant_name;
	

	
	private SpinnerPopWindow mSpinerPopWindow;
	private StickyListHeadersListView stickyList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.restaurant_detail_main);
		Injector.get(this).inject();//init views
		Intent intent = getIntent();
		restaurant_name = intent.getStringExtra("name");
		initView();
		setListener();
		
	}

	private void initView() {
		above_tittle.setText(restaurant_name);
		head_left.setImageResource(R.drawable.abc_ic_ab_back_holo_dark);
		
	}
	
	private void setListener() {
		// TODO Auto-generated method stub
		above_toHome.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
				
			}
		});
		
		mAdapter = new RestaurantDetailAdapter(this,order_cart);

		stickyList = (StickyListHeadersListView) findViewById(R.id.list_restaurant_detail);
		stickyList.setOnItemClickListener(this);
		stickyList.setOnHeaderClickListener(this);
		stickyList.setOnStickyHeaderChangedListener(this);
		stickyList.setOnStickyHeaderOffsetChangedListener(this);
		stickyList.addHeaderView(getLayoutInflater().inflate(
				R.layout.restaurant_list_header, null));
//		stickyList.addFooterView(getLayoutInflater().inflate(
//				R.layout.restaurant_list_footer, null));
		stickyList.setDrawingListUnderStickyHeader(true);
		stickyList.setAreHeadersSticky(true);
		stickyList.setAdapter(mAdapter);

//		stickyList.setStickyHeaderTopOffset(-20);
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			nameList.add("内容" + i);
		}

		mSpinerPopWindow = new SpinnerPopWindow(RestaurantDetailActivity.this, nameList);
		// mSpinerPopWindow.refreshData(nameList, 0);
		mSpinerPopWindow.setItemListener(new IOnItemSelectListener(){

			@Override
			public void onItemClick(int pos) {
				// TODO Auto-generated method stub
				Toast.makeText(RestaurantDetailActivity.this, "" + pos, Toast.LENGTH_SHORT).show();
			}
			
		});
		mPayMoney.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showSpinWindow();
				
			}
		});
	}

	private void showSpinWindow() {
		mSpinerPopWindow.setWidth(mPayMoney.getWidth());
		mSpinerPopWindow.showAsDropDown(mPayMoney, 0, 5);
		mSpinerPopWindow.setFocusable(true);
		mSpinerPopWindow.setAnimationStyle(R.style.AnimSpinerInSearchProducts);
		mSpinerPopWindow.update();
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "Item " + position + " clicked!",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onHeaderClick(StickyListHeadersListView l, View header,
			int itemPosition, long headerId, boolean currentlySticky) {
		Toast.makeText(this,
				"Header " + headerId + " currentlySticky ? " + currentlySticky,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onStickyHeaderOffsetChanged(StickyListHeadersListView l,
			View header, int offset) {
		if (fadeHeader
				&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			header.setAlpha(1 - (offset / (float) header.getMeasuredHeight()));
		}
	}

	@Override
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void onStickyHeaderChanged(StickyListHeadersListView l, View header,
			int itemPosition, long headerId) {
		header.setAlpha(1);
	}

}