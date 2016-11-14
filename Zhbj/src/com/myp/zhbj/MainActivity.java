package com.myp.zhbj;
/*
 * 主页面
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import com.myp.zhbj.fragment.ContentFragment;
import com.myp.zhbj.fragment.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity{
	private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	private static final String TAG_CONTENT = "TAG_CONTENT";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去标题栏，在setContent之前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		
		setBehindContentView(R.layout.left_menu);
		SlidingMenu slidingMenu = getSlidingMenu();
		//全屏触摸
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(200);//屏幕预留200像素宽度
		
		initFragment();
	}
	//初始化fragment
	private void initFragment(){
		FragmentManager fm = 
				getSupportFragmentManager();//拿到Fragment管理器
		//开启事务
		FragmentTransaction transaction = fm.beginTransaction();
		
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),TAG_LEFT_MENU);//用fragment替换帧布局；参一：帧布局容器的 id,参2：要替换的Fragment
		transaction.replace(R.id.fl_main, new ContentFragment(),TAG_CONTENT);//用fragment替换帧布局；参一：帧布局容器的 id,参2：要替换的Fragment,3标记
		//提交事务
		transaction.commit();
		
	}
	//获取主页ContentFragment对象
	public ContentFragment getContentFragment(){
		FragmentManager fm = getSupportFragmentManager();
		ContentFragment fragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);
		return fragment;
	}
	
	//获取侧边栏fragment对象
	public LeftMenuFragment getLeftMenuFragment(){
		FragmentManager fm = getSupportFragmentManager();
		LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);
		return fragment;
	}
}
