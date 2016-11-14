package com.myp.zhbj.base.Impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.myp.zhbj.base.BaseMenuDetailPager;
import com.myp.zhbj.domain.NewsMenu.NewsTabData;
/*
 * 页签的页面对象
 */
public class TabDetailPager extends BaseMenuDetailPager {

	private NewsTabData mTabData;//单个页签的网络数据
	private TextView view;
	
	public TabDetailPager(Activity activity, NewsTabData newsTabData) {
		super(activity);
		mTabData = newsTabData;
	}

	@Override
	public View initView() {
		view = new TextView(mActivity);
		//view.setText(mTabData.title); 此处空指针异常
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		
		return view;
	}
	@Override
	public void initData() {
		super.initData();
		view.setText(mTabData.title);
	}

}
