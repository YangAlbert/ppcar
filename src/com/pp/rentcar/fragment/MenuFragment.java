
package com.pp.rentcar.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pp.rentcar.R;
import com.pp.rentcar.activity.HomePageActivity;
import com.pp.rentcar.activity.LoginActivity;
import com.pp.rentcar.util.Tools;

@SuppressLint("NewApi")
public class MenuFragment extends Fragment implements View.OnClickListener {

	private View currentView;
	private ImageView mLoginview;
	private LinearLayout mAccountSet;
	private Button bt_abouts, bt_gift, bt_home, bt_invitation, bt_orders, bt_camera;
	private Drawable head_iocn;

	public View getCurrentView() {
		return currentView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currentView = inflater.inflate(R.layout.slidingpane_menu_layout,
				container, false);
		bt_abouts = (Button) currentView.findViewById(R.id.btn_abouts);
		bt_gift = (Button) currentView.findViewById(R.id.btn_gift);
		bt_home = (Button) currentView.findViewById(R.id.btn_home);
		bt_invitation = (Button) currentView.findViewById(R.id.btn_invitation);
		mAccountSet = (LinearLayout)currentView.findViewById(R.id.iv_login);
		mLoginview = (ImageView) currentView.findViewById(R.id.iv_loginview);
		bt_camera = (Button) currentView.findViewById(R.id.btn_camera);
		initRoundImage();
		bt_orders = (Button) currentView.findViewById(R.id.btn_order);
		bt_abouts.setOnClickListener(this);
		bt_gift.setOnClickListener(this);
		bt_home.setOnClickListener(this);
		bt_invitation.setOnClickListener(this);
		bt_orders.setOnClickListener(this);
		mAccountSet.setOnClickListener(this);
		//mLoginview.setOnClickListener(this);
		bt_camera.setOnClickListener(this);
		return currentView;
	}

	@SuppressWarnings("deprecation")
	private void initRoundImage() {
		Tools tools = new Tools();
		mLoginview.setBackgroundDrawable(new BitmapDrawable(tools.toRoundBitmap(getActivity(), "ali_head.jpg")));  
		mLoginview.getBackground().setAlpha(0);
		mLoginview.setImageBitmap(tools.toRoundBitmap(getActivity(), "ali_head.jpg"));     
	}

	@SuppressLint("CommitTransaction")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();// 开始一个事物
		switch (v.getId()) {
		case R.id.btn_home:
			Fragment homeFragment = new HomeFragment();
			ft.replace(R.id.slidingpane_content, homeFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.btn_order:
			Fragment orderFragment = new OrderFragment();
			ft.replace(R.id.slidingpane_content, orderFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.btn_gift:
			Fragment giftFragment = new GiftsFragment();
			ft.replace(R.id.slidingpane_content, giftFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.btn_invitation:
			Fragment invitationFragment = new InvitationsFragment();
			ft.replace(R.id.slidingpane_content, invitationFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.btn_abouts:
			Fragment aboutsFragment = new AboutsFragment();
			ft.replace(R.id.slidingpane_content, aboutsFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.iv_login:
			Fragment accountSetFragment = new AccountSetFragment();
			ft.replace(R.id.slidingpane_content, accountSetFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.btn_camera:
			Fragment cameraFragment = new CameraFragment();
			ft.replace(R.id.slidingpane_content, cameraFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		}
		((HomePageActivity) getActivity()).getSlidingPaneLayout().closePane();
	}
}
