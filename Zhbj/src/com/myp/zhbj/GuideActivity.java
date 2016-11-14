package com.myp.zhbj;

import java.util.ArrayList;

import com.myp.zhbj.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/*
 * 新手引导页面
 */
public class GuideActivity extends Activity {
	
	private ViewPager mViewPager;
	
	private ArrayList<ImageView> mImageViewList;//imageView的集合
	
	private ImageView ivRedPoint;//小红点
	
	private Button btnStart;

	
	
	//引导页图片id数组
	private int[] mImageIds = new int[]{ R.drawable.guide_1,
			R.drawable.guide_2,
			R.drawable.guide_3};

	private LinearLayout llContainer;//小圆点布局

	private int mPointDis;	//小红点移动距离

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//去标题栏，在setContent之前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_guide);
		
		 mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		 llContainer = (LinearLayout) findViewById(R.id.ll_container);
		 ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
		 btnStart = (Button) findViewById(R.id.btn_start);
		
		 
		 initData();
		 mViewPager.setAdapter(new GuideAdapter());//设置数据
		 
		//设置ViewPager的监听
		 mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				//当页面滑动过程中回调  不断跟新点的位置
				//position 当前位置  positionOffset 偏移量百分比，参数里最后一个为偏移像素
				
				//更新小红点距离
				//计算小红点当前的左边距
				int leftMargin = (int)(mPointDis * positionOffset) + position * mPointDis;
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
				params.leftMargin = leftMargin;
				//重新设置布局参数
				ivRedPoint.setLayoutParams(params);
			}

			@Override
			public void onPageSelected(int position) {
				//某个页面被选中
				if(position == mImageViewList.size()-1){
					btnStart.setVisibility(View.VISIBLE);
				}else{
					btnStart.setVisibility(View.INVISIBLE);
				}
				
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				//页面状态发生变化 如 不划到划
			}
		 });
		//mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();
		 //这里是 0 ，因为自定义view流程：measure->layout->draw(在activity的oncreate方法调用后执行)，所以这里是0
		 //监听 layout（确定位置）结束事件，再获取两点间距离
		 ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
				//layout方法执行结束回调
				mPointDis = llContainer.getChildAt(1).getLeft() - llContainer.getChildAt(0).getLeft();

			}
		});
		 btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//更新sp,表示已经不是第一次进入了
				PrefUtils.setBoolean(getApplicationContext(),"is_first_enter",false);
				//跳到主页面
				startActivity(new Intent(getApplicationContext(),MainActivity.class));
				finish();
			}
		});
		 
	}		//onCreate结束
	
	
	//初始化mImageView对象
	private void initData(){
		mImageViewList = new ArrayList<ImageView>();
		for(int i=0;i<mImageIds.length;i++){
			ImageView view = new ImageView(this); 
			view.setBackgroundResource(mImageIds[i]);//通过设置背景可以让宽高填充
			mImageViewList.add(view); 
			
			//初始化小圆点
			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.shape_point_gray);//设置图片为形状
			//布局参数初始化,用父控件的布局参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			
			if(i>0){
				//设置左边距 从第二个点开始
				params.leftMargin = 10;//像素
			} 
			
			point.setLayoutParams(params);//设置布局参数
			
			llContainer.addView(point);//给linearLayout添加圆点
		}
	}
	
	class GuideAdapter extends PagerAdapter{
		
		//item的个数
		@Override
		public int getCount() {
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
		}
		
		//初始化item布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}
		//销毁item
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

}
