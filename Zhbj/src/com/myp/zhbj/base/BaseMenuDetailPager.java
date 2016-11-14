package com.myp.zhbj.base;

import android.app.Activity;
import android.view.View;

/*
 *  菜单详情页基类
 */
public abstract class BaseMenuDetailPager {
	public Activity mActivity;
	public View mRootView;//菜单详情页的根布局
	
	public BaseMenuDetailPager(Activity activity){
		mActivity = activity;
		mRootView = initView();
	}

	public abstract View initView();
	
	public void initData(){
		
	}
}
