package com.myp.zhbj.base.Impl;

import com.myp.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/*
 * 服务
 */

public class SmartServicePager extends BasePager {

	public SmartServicePager(Activity activity) {
		super(activity);
		// TODO Auto-generated constructor stub
	}



	@Override
	public void initData() {
		//给帧布局填充对象
		TextView view = new TextView(mActivity);
		view.setText("服务");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		tvTitle.setText("生活");
		//菜单按钮
		btnMenu.setVisibility(View.VISIBLE);

	}

}
