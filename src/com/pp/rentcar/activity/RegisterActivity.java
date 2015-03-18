package com.pp.rentcar.activity;

import com.pp.rentcar.R;
import com.pp.rentcar.widget.ClearEditTextView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class RegisterActivity extends Activity{

	private TextView mRegister;
	private ClearEditTextView mPasswordAgain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.regiser);
		mRegister = (TextView) findViewById(R.id.login_submit);
		mRegister.setText("注册");
		mPasswordAgain = (ClearEditTextView) findViewById(R.id.login_passwordagain);
		mPasswordAgain.setVisibility(View.VISIBLE);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
