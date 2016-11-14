package com.myp.zhbj.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.myp.zhbj.MainActivity;
import com.myp.zhbj.R;
import com.myp.zhbj.base.BasePager;
import com.myp.zhbj.base.Impl.GovAffairsPager;
import com.myp.zhbj.base.Impl.HomePager;
import com.myp.zhbj.base.Impl.NewsCenterPager;
import com.myp.zhbj.base.Impl.SettingPager;
import com.myp.zhbj.base.Impl.SmartServicePager;
import com.myp.zhbj.view.NoScrollViewPager;

public class ContentFragment extends BaseFragment {
	
	
	private NoScrollViewPager mViewPager;

	private ArrayList<BasePager> mPagers;//5个标签页集合
	
	private RadioGroup rgGroup;



	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}





	@Override
	public void initData() {
		mPagers = new ArrayList<BasePager>();
		//添加5个标签页
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new NewsCenterPager(mActivity));
		mPagers.add(new SmartServicePager(mActivity));
		mPagers.add(new GovAffairsPager(mActivity));
		mPagers.add(new SettingPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
		//低栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
					switch (checkedId) {
					case R.id.rb_home:
						//首页
						mViewPager.setCurrentItem(0,false);
						break;
					case R.id.rb_news:
						//新闻
						mViewPager.setCurrentItem(1,false);
						break;
					case R.id.rb_smart:
						//服务
						mViewPager.setCurrentItem(2,false);
						break;
					case R.id.rb_gov:
						//政务
						mViewPager.setCurrentItem(3,false);
						break;
					case R.id.rb_setting:
						//设置
						mViewPager.setCurrentItem(4,false);
						break;
					default:
						break;
					}
			}
		});
		//监听viewPager
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//拿到被选中的页面
				BasePager pager = mPagers.get(position);
				pager.initData();
				//首页设置禁用侧边栏
				if(position == 0 || position == 4){
					setSlidingMenuEnable(false);
				}else{
					//其他页面开启侧边栏
					setSlidingMenuEnable(true);
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		//手动加载第一个
		 mPagers.get(0).initData();
		 //首页禁用侧边栏
		 setSlidingMenuEnable(false);
	}
	//开启或禁用侧边栏
	protected void setSlidingMenuEnable(boolean enable) {
			//获取侧边栏对象
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		if(enable){
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

		}
	}

	class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;//获取当前页面对象的布局
			
			//pager.initData();//初始化数据
			//Viewpager会自动加载下一个页面，为了节省流量个性能，不在此处调用初始化数据
			container.addView(view);//把布局给容器
			
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
		
	}
	
	//获取新闻中心页面的方法
	public NewsCenterPager getNewsCenterPager(){
		NewsCenterPager pager = (NewsCenterPager) mPagers.get(1);
		return pager;
	}
	
}
