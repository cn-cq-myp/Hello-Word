package com.myp.zhbj.base.Impl.menu;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.myp.zhbj.base.BaseMenuDetailPager;

/*
 * 菜单详情页：组图
 */
public class PhotosMenuDetailPager extends BaseMenuDetailPager{

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("菜单详情页-组图");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		
			return view;
	}

	public PhotosMenuDetailPager(Activity activity) {
		super(activity);
	}
	

}
