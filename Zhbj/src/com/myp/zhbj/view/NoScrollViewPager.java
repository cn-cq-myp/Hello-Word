package com.myp.zhbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
/*
 * 不允许滑动打ViewPager
 */
public class NoScrollViewPager extends ViewPager {

	
	
	public NoScrollViewPager(Context context) {
		super(context);
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	//拦截去子控件的事件
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		return false;//不拦截子控件的事件
	}
	
	public boolean onTouchEvent(MotionEvent ev){
		//重写触摸时什么都不做，从而实现对滑动事件的禁用
		return false;
	}

}
