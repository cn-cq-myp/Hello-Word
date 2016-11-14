package com.myp.zhbj.base.Impl;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.myp.zhbj.MainActivity;
import com.myp.zhbj.base.BaseMenuDetailPager;
import com.myp.zhbj.base.BasePager;
import com.myp.zhbj.base.Impl.menu.InteractMenuDetailPager;
import com.myp.zhbj.base.Impl.menu.NewsMenuDetailPager;
import com.myp.zhbj.base.Impl.menu.PhotosMenuDetailPager;
import com.myp.zhbj.base.Impl.menu.TopicMenuDetailPager;
import com.myp.zhbj.domain.NewsMenu;
import com.myp.zhbj.fragment.LeftMenuFragment;
import com.myp.zhbj.global.GlobalConstants;
import com.myp.zhbj.utils.CacheUtils;


/*
 * 新闻
 */

public class NewsCenterPager extends BasePager {
	//维护菜单详情页 的 集合
	private ArrayList<BaseMenuDetailPager> mMenuDetailPager;
	private NewsMenu mNewsData;//分类信息网络数据

	public NewsCenterPager(Activity activity) {
		super(activity);
	}
	
	@Override
	public void initData() {
	/*	//给帧布局填充对象
		TextView view = new TextView(mActivity);
		view.setText("新闻中心");
		view.setTextColor(Color.RED);
		view.setTextSize(22);
		view.setGravity(Gravity.CENTER);
		
		flContent.addView(view);*/
		
		tvTitle.setText("新闻中心");
		//显示菜单
		btnMenu.setVisibility(View.VISIBLE);
		
		//判断有没有缓存，有的话就加载缓存
		String cache = CacheUtils.getCache(GlobalConstants.CATEGORY_URL, mActivity);
		if(!TextUtils.isEmpty(cache)){
			//发现缓存
			processData(cache);
		}else{
		//请求服务器,获取数据,用第三方开源框架：XUtils
		
			getDataFromServer();
		}
	}
	/*
	 * 从服务器获取数据
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET,GlobalConstants.CATEGORY_URL,new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//请求成功
				String result = responseInfo.result;//获取服务器访问结果
				System.out.println("服务器返回结果" + result);
				//JsonObject ,Gson
				processData(result);
				
				//写缓存
				CacheUtils.setCache(GlobalConstants.CATEGORY_URL, result, mActivity);
			}
			
 
			@Override
			public void onFailure(HttpException error, String msg) {
				//请求失败
				error.printStackTrace();
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	/*
	 * 解析数据
	 */
	protected void processData(String json) { 
		Gson gson = new Gson();
		mNewsData = gson.fromJson(json,NewsMenu.class);
		System.out.println("解析结果"+mNewsData);
		//获得侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		LeftMenuFragment fragment = mainUI.getLeftMenuFragment();
		
		//给侧边栏设置数据
		fragment.setMenuData(mNewsData.data);
		
		//初始化4个菜单详情页
		mMenuDetailPager = new ArrayList<BaseMenuDetailPager>();
		
		mMenuDetailPager.add(new NewsMenuDetailPager(mActivity,mNewsData.data.get(0).children));
		mMenuDetailPager.add(new TopicMenuDetailPager(mActivity));
		mMenuDetailPager.add(new PhotosMenuDetailPager(mActivity));
		mMenuDetailPager.add(new InteractMenuDetailPager(mActivity));
		
		//一进来就是新闻菜单详情页
		setCurrentDetailPager(0);
		
	}
	
	//设置新闻中心菜单详情页布局
	public void setCurrentDetailPager(int position){
		//从新给fragmeLayout添加内容
		//获取当前应该修改的页面
		BaseMenuDetailPager pager = mMenuDetailPager.get(position);
		View view = pager.mRootView;//当前页面的布局
		
		//因为是FrameLayout,你添加他会直接压上，所以要清除之前旧的布局
		flContent.removeAllViews();
		
		flContent.addView(view);//给帧布局添加新的布局
		
		//初始化页面数据
		pager.initData();
		
		//更新标题
		tvTitle.setText(mNewsData.data.get(position).title);
		
		
	}

}

