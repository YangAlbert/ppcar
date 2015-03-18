package com.pp.rentcar;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.widget.Toast;

import com.pp.rentcar.image.ImageLoaderConfig;
import com.pp.rentcar.util.Constants;


public class BaseApplication extends Application {
	private String jumpType="";
	private static BaseApplication sInstance=null;
	
	/*// 百度MapAPI的管理类
		public BMapManager mBMapManager = null;

		// 授权Key
		// TODO: 请输入您的Key,
		// 申请地址：http://dev.baidu.com/wiki/static/imap/key/
		public static final String strKey = "840FFE132BB1749F265E77000ED4A8E17ECEC190";
		boolean m_bKeyRight = true; // 授权Key正确，验证通过

		public void initEngineManager(Context context) {
	        if (mBMapManager == null) {
	            mBMapManager = new BMapManager(context);
	        }

	        if (!mBMapManager.init(strKey,new MyGeneralListener())) {
	            Toast.makeText(BaseApplication.getInstance().getApplicationContext(), 
	                    "BMapManager  初始化错误!", Toast.LENGTH_LONG).show();
	        }
		}*/
		public static BaseApplication getInstance() {
			return sInstance;
		}
		// 常用事件监听，用来处理通常的网络错误，授权验证错误等
		/*public static class MyGeneralListener implements MKGeneralListener {

			@Override
			public void onGetNetworkState(int iError) {
				if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
	                Toast.makeText(BaseApplication.getInstance().getApplicationContext(), "您的网络出错啦！",
	                    Toast.LENGTH_LONG).show();
	            }
	            else if (iError == MKEvent.ERROR_NETWORK_DATA) {
	                Toast.makeText(BaseApplication.getInstance().getApplicationContext(), "输入正确的检索条件！",
	                        Toast.LENGTH_LONG).show();
	            }
				
			}

			@Override
			public void onGetPermissionState(int iError) {
				// TODO Auto-generated method stub
				if (iError ==  MKEvent.ERROR_PERMISSION_DENIED) {
	                //授权Key错误：
	                Toast.makeText(BaseApplication.getInstance().getApplicationContext(), 
	                        "请在 DemoApplication.java文件输入正确的授权Key！", Toast.LENGTH_LONG).show();
	                BaseApplication.getInstance().m_bKeyRight = false;
	            }
			}
			
		}*/
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		sInstance = this;
		//initEngineManager(this);
		super.onCreate();
		ImageLoaderConfig.initImageLoader(this, Constants.BASE_IMAGE_CACHE);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		/*if (mBMapManager != null) {
            mBMapManager.destroy();
            mBMapManager = null;
        }*/
		super.onTerminate();
	}

	public String getJumpType() {
		return jumpType;
	}

	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}

}
