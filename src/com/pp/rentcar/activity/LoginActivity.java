package com.pp.rentcar.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.pp.rentcar.R;
import com.pp.rentcar.util.InjectView;
import com.pp.rentcar.util.Injector;
import com.pp.rentcar.util.SharedPreferencesUtil;

public class LoginActivity extends Activity {
	private ImageView mHead_left;
	private LinearLayout mAbove_toHome;
	private TextView mAbove_tittle;
	private TextView mLogin;
	private TextView mRegister;
	private SharedPreferencesUtil SpUtil;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		mHead_left = (ImageView) findViewById(R.id.iv_head_left);
		mAbove_toHome = (LinearLayout) findViewById(R.id.linear_above_toHome);
		mAbove_tittle = (TextView) findViewById(R.id.tv_common_above_head);
		mLogin = (TextView) findViewById(R.id.login_submit);
		mRegister = (TextView) findViewById(R.id.user_register);
		SpUtil = new SharedPreferencesUtil(this,"SharedPreferences");
		initView();
		setListener();
	}

	private void setListener() {
		// TODO Auto-generated method stub
		mAbove_toHome.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
				
			}
		});
		mLogin.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				SpUtil.setValue("loginFlag", true);
				finish();
				
			}
		});
		mRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
			}
		});
	}

	private void initView() {
		mAbove_tittle.setText("");
		mHead_left.setImageResource(R.drawable.abc_ic_ab_back_holo_dark);

	}

}
