package com.pp.rentcar.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import com.pp.rentcar.BaseApplication;
import com.pp.rentcar.R;
import com.pp.rentcar.activity.DianPingWebActivity;
import com.pp.rentcar.activity.RestaurantDetailActivity;
import com.pp.rentcar.adapter.HomePageRestaurantAdapter;
import com.pp.rentcar.entity.RestaurantEntity;
import com.pp.rentcar.util.RefreshableListView;
import com.pp.rentcar.util.RefreshableListView.OnRefreshListener;
import com.pp.rentcar.widget.SpinnerPopWindow;
import com.pp.rentcar.widget.SpinnerPopWindow.IOnItemSelectListener;

@SuppressLint("NewApi")
public class HomeFragment extends Fragment implements OnClickListener {
	
	//static MapView mMapView = null;
    //private MapController mMapController = null;
	LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    //MyLocationOverlay myLocationOverlay = null;
    //LocationData locData = null;
	
    final int getAddSuccess = 0;
	final int getAddFail = 1;
	
	private TextView mLbs_location;
	private View currentView;
	private LinearLayout openMenu;
	private RefreshableListView mListView;
	private HomePageRestaurantAdapter adapter;
	private List<RestaurantEntity> mlist;
	private int total = 21;
	private int step = 7;
	private int add = 7;
	private View listHeaderView;
	private RelativeLayout mHeadView;
	private SpinnerPopWindow mSpinerPopWindow;

	public void setCurrentViewPararms(FrameLayout.LayoutParams layoutParams) {
		currentView.setLayoutParams(layoutParams);
	}

	public FrameLayout.LayoutParams getCurrentViewParams() {
		return (LayoutParams) currentView.getLayoutParams();
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		BaseApplication app = (BaseApplication) getActivity().getApplication();

        // LocationClient just init once;
        // start() is moved
		Log.d("lbc", "onCreate enter");
        mLocClient = new LocationClient(getActivity());

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型       
        option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
        option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式Hight_Accuracy高精度、Battery_Saving低功耗、Device_Sensors仅设备(GPS)
        option.setAddrType("all");  //设置定位优先级
        option.setIsNeedAddress(true);//返回的定位结果包含地址信息
        mLocClient.setLocOption(option);

        
		super.onCreate(savedInstanceState);
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			nameList.add("内容" + i);
		}

		mSpinerPopWindow = new SpinnerPopWindow(getActivity(), nameList);
		// mSpinerPopWindow.refreshData(nameList, 0);
		mSpinerPopWindow.setItemListener(new IOnItemSelectListener(){

			@Override
			public void onItemClick(int pos) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "" + pos, Toast.LENGTH_SHORT).show();
			}
			
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currentView = inflater.inflate(R.layout.slidingpane_home_layout,
				container, false);
		Log.d("lbc", "onCreateView enter");
		/*mMapView = (MapView) v.findViewById(R.id.bmapView);
        mMapController = mMapView.getController();
        mMapView.getController().setZoom(14);
        mMapView.getController().enableClick(true);
        mMapView.setBuiltInZoomControls(true);

        myLocationOverlay = new MyLocationOverlay(mMapView);
        locData = new LocationData();
        myLocationOverlay.setData(locData);
        mMapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.enableCompass();
        mMapView.refresh();*/
        
		mLbs_location = (TextView) currentView
				.findViewById(R.id.tv_common_above_head);
		mListView = (RefreshableListView) currentView
				.findViewById(R.id.mineList);
		openMenu = (LinearLayout) currentView
				.findViewById(R.id.linear_above_toHome);
		listHeaderView = getActivity().getLayoutInflater().inflate(
				R.layout.home_head_view, null);
		mHeadView = (RelativeLayout) listHeaderView
				.findViewById(R.id.iv_home_head);
		openMenu.setOnClickListener(this);
		getDate();
		setListener();
		startRequestLocation();
		return currentView;
	}

	/**
     * warning: this method is callback by activity manager; Be careful it's
     * lifecycle ： It is called after oncreate , before oncreateview; see
     * detail:
     * http://developer.android.com/reference/android/support/v4/app/Fragment
     * .html#setUserVisibleHint(boolean)
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("lbc", "setUserVisibleHint enter");
        if (isVisibleToUser == true) {
            // if this view is visible to user, start to request user location
            startRequestLocation();
        } else if (isVisibleToUser == false) {
            // if this view is not visible to user, stop to request user
            // location
            stopRequestLocation();
        }
    }

    private void stopRequestLocation() {
        if (mLocClient != null) {
            mLocClient.unRegisterLocationListener(myListener);
            mLocClient.stop();
        }
    }

    long startTime;
    long costTime;

    private void startRequestLocation() {
        // this nullpoint check is necessary
        if (mLocClient != null) {
        	Log.d("lbc", "startRequestLocation enter");
            mLocClient.registerLocationListener(myListener);
            mLocClient.start();
            mLocClient.requestLocation();
            startTime = System.currentTimeMillis();
        }
    }
	public void setListener() {

		mListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh(RefreshableListView listView) {
				new NewDataTask().execute();
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent = new Intent(getActivity(),
						RestaurantDetailActivity.class);
				intent.putExtra("name", mlist.get(position+1).getName());
				startActivity(intent);
			}
		});
		//全部区域列表
		mHeadView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showSpinWindow();
			}
		});
	}

	private void showSpinWindow() {
		mSpinerPopWindow.setWidth(mHeadView.getWidth());
		mSpinerPopWindow.showAsDropDown(mHeadView, 0, 10);
		mSpinerPopWindow.setFocusable(true);
		mSpinerPopWindow.setAnimationStyle(R.style.AnimSpinerInSearchProducts);
		mSpinerPopWindow.update();
	}
	 /**
     * 监听函数，有新位置的时候，格式化成字符串，输出到屏幕中
     */
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null)
                return;
            costTime = System.currentTimeMillis() - startTime;
            Log.d("lbc", "" + costTime);
            final double latitude = location.getLatitude();
            Log.d("lbc", "latitude=" + latitude);
            final double longitude = location.getLongitude();
            Log.d("lbc", "longitude=" + longitude);
            final String addr = location.getAddrStr();
            Log.d("lbc", "addr=" + addr);
            new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						String add = com.pp.rentcar.util.getJson.getAddress(latitude, longitude,
								getActivity());
						if (addr != "") {
							Message message = new Message();
							message.what = getAddSuccess;
							message.obj = addr;
							handler.sendMessage(message);
						} else {
							Message message = new Message();
							message.what = getAddFail;
							handler.sendMessage(message);
						}
					} catch (Exception e) {
						Message message = new Message();
						message.what = getAddFail;
						handler.sendMessage(message);
					}
				}
			}).start();
            /*locData.accuracy = location.getRadius();
            locData.direction = location.getDerect();
            myLocationOverlay.setData(locData);
            mMapView.refresh();
            mMapController.animateTo(new GeoPoint((int) (locData.latitude * 1e6), (int) (locData.longitude * 1e6)));*/
            // if request location success , stop it
            stopRequestLocation();
        }

        public void onReceivePoi(BDLocation poiLocation) {
            if (poiLocation == null) {
                return;
            }
        }
    }
    private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case getAddFail:
				mLbs_location.setText("定位失败，请稍后重试！");
				break;
			case getAddSuccess:
				//mMapView.setVisibility(View.VISIBLE);
				mLbs_location.setText((String) msg.obj);
				break;
			}
		}
	};
	private class NewDataTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			int current = mListView.getAdapter().getCount();
			if (current < total) {
				add += step;
				mListView.removeHeaderView(listHeaderView);
				getDate();
			}

			mListView.completeRefreshing();

			super.onPostExecute(result);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.linear_above_toHome):
			openMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					final SlidingPaneLayout slidingPaneLayout = (SlidingPaneLayout) getActivity()
							.findViewById(R.id.slidingpanellayout);
					if (slidingPaneLayout.isOpen()) {
						slidingPaneLayout.closePane();
					} else {
						slidingPaneLayout.openPane();
					}
				}
			});

			break;
		}
	}

	private void getDate() {

		mlist = new ArrayList<RestaurantEntity>();
		RestaurantEntity restaurant1 = new RestaurantEntity();
		restaurant1.setLogo("drawable://" + R.drawable.pic_jigongbao);
		restaurant1.setName("丁丁洗车行");
		restaurant1.setItem_msg("月售208单 / 20元起送 / 30分钟");
		restaurant1.setRate_numbers(1);
		restaurant1.setIs_rest(true);
		//restaurant1.setPromotion("指定食品，每份减4元");
		restaurant1.setIs_half(true);
		restaurant1.setIs_mins(true);
		mlist.add(restaurant1);
		restaurant1 = null;

		RestaurantEntity restaurant2 = new RestaurantEntity();
		restaurant2.setLogo("drawable://" + R.drawable.pic_jixiang);
		restaurant2.setName("大冰的小屋");
		restaurant2.setItem_msg("月售128单 / 14元起送 / 20分钟");
		//restaurant2.setPromotion("【新】下单立减3元，份份减3，加赠500ml康师傅果汁！");
		restaurant2.setIs_mins(true);
		restaurant2.setRate_numbers(2);
		mlist.add(restaurant2);
		restaurant2 = null;

		RestaurantEntity restaurant3 = new RestaurantEntity();
		restaurant3.setLogo("drawable://" + R.drawable.pic_milishi);
		restaurant3.setName("浮游吧");
		restaurant3.setItem_msg("月售221单 / 12元起送 / 30分钟");
		restaurant3.setIs_favor(true);
		restaurant3.setRate_numbers(3);
		//restaurant3.setPromotion("【新】赠500ml康师傅果汁！");
		restaurant3.setIs_half(true);
		mlist.add(restaurant3);
		restaurant3 = null;

		RestaurantEntity restaurant4 = new RestaurantEntity();
		restaurant4.setLogo("drawable://" + R.drawable.pic_shaxian);
		restaurant4.setName("点点");
		restaurant4.setItem_msg("月售218单 / 11元起送 / 10分钟");
		restaurant4.setIs_rest(true);
		restaurant4.setRate_numbers(4);
		//restaurant4.setPromotion("帅哥给你送餐！");
		restaurant4.setIs_mins(true);
		mlist.add(restaurant4);
		restaurant4 = null;

		RestaurantEntity restaurant5 = new RestaurantEntity();
		restaurant5.setLogo("drawable://" + R.drawable.pic_shiguifan);
		restaurant5.setName("小苹果");
		restaurant5.setItem_msg("月售82单 / 14元起送 / 22分钟");
		restaurant5.setIs_favor(true);
		restaurant5.setRate_numbers(5);
		restaurant5.setIs_mins(true);
		mlist.add(restaurant5);
		restaurant5 = null;

		RestaurantEntity restaurant6 = new RestaurantEntity();
		restaurant6.setLogo("drawable://" + R.drawable.pic_tengqi);
		restaurant6.setName("藤崎寿司");
		restaurant6.setItem_msg("月售34单 / 11元起送 / 10分钟");
		restaurant6.setRate_numbers(2);
		restaurant6.setIs_mins(true);
		mlist.add(restaurant6);
		restaurant6 = null;

		RestaurantEntity restaurant7 = new RestaurantEntity();
		restaurant7.setLogo("drawable://" + R.drawable.pic_xiaohongmao);
		restaurant7.setName("小红帽快餐厅");
		restaurant7.setItem_msg("月售233单 / 14元起送 / 20分钟");
		restaurant7.setRate_numbers(3);
		restaurant7.setIs_mins(true);
		mlist.add(restaurant7);
		restaurant7 = null;

		adapter = new HomePageRestaurantAdapter(getActivity(), mlist);
		mListView.setAdapter(adapter);
		mListView.addHeaderView(listHeaderView);
	}

}
