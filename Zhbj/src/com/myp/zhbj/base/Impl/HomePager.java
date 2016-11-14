package com.myp.zhbj.base.Impl;

import com.myp.zhbj.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;


/*
 * 首页
 */

public class HomePager extends BasePager {

	public HomePager(Activity activity) {
		super(activity);
	}
	@Override
	public void initData() {
		//给帧布局填充对象
		TextView view = new TextView(mActivity);
		view.setText("首页");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);
		
		//修改页面标题
		tvTitle.setText("智慧北京");
		
		//隐藏菜单按钮
		btnMenu.setVisibility(View.GONE);
	}

}
