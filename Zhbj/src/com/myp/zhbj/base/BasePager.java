package com.myp.zhbj.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myp.zhbj.MainActivity;
import com.myp.zhbj.R;
/*
 * 5个标签类的基类
 */

public class BasePager {
	public Activity mActivity;
	
	public TextView tvTitle;
	public ImageButton btnMenu;
	public FrameLayout flContent;//空的帧布局对象,动态添加布局

	public View mRootView;//当前页面的布局文件对象
	
	
	public BasePager(Activity activity){
		mActivity = activity;
		mRootView = initView();
	}
	
	//初始化布局
	public View initView(){
		View view = View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
		flContent = (FrameLayout) view.findViewById(R.id.fl_content);
		 
		btnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggle();
				
			//点击侧边栏后 修改fragmentLayout
			}
		});
		
		return view;
	}
	
	/*
	 * 打开或者关闭侧边栏
	 */
	protected void toggle(){
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();//状态是开，调用关，反之开
	}
	
	public void initData(){
		
	}

}
