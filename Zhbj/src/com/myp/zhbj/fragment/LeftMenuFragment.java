package com.myp.zhbj.fragment;

import java.util.ArrayList;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.myp.zhbj.MainActivity;
import com.myp.zhbj.R;
import com.myp.zhbj.base.Impl.NewsCenterPager;
import com.myp.zhbj.domain.NewsMenu.NewsMenuData;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/*
 * 侧边栏
 */

public class LeftMenuFragment extends BaseFragment {

	//XUtils注解，代替findviewbyid
	@ViewInject(R.id.lv_list)
	private ListView lvList;
	
	//当前被选中的item的位置
	private int mCurrentPos;
	
	//侧边栏网络对象数据对象
	private ArrayList<NewsMenuData> mNewsMenuData;

	private LeftMenuAdapter mAdapter;
	
	@Override
	public View initView() {
		View view = View.inflate(mActivity,R.layout.fragment_left_menu,null);
		//lvList = (ListView) view.findViewById(R.id.lv_list);
		
		ViewUtils.inject(this,view);//注入View和事件,viewutils
		return view;
	} 

	@Override
	public void initData() {
		
	}
	//设置侧边栏数据
	public void setMenuData(ArrayList<NewsMenuData> data){
		mCurrentPos = 0;//当前选中的位置归0
		
		//更新页面
		mNewsMenuData = data;
		
		mAdapter = new LeftMenuAdapter();
		lvList.setAdapter(mAdapter);
		
		lvList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCurrentPos = position;//更新当前被选中的位置
				
				mAdapter.notifyDataSetChanged();//☆☆☆☆☆刷新listview
				
				//收起侧边栏 ： 先拿到侧边栏对象，在写个方法 管理 打开收起 侧边栏
				toggle();
				
				//侧边栏被点击后，修改新闻中心FragmentLayout中的内容
				setCurrentDetailPager(position);
			}

			
		});
	}
	/*
	 * 设置当前的菜单详情页
	 */
	protected void setCurrentDetailPager(int position) {
		//获取新闻中心的对象
		MainActivity mainUI = (MainActivity) mActivity;
		// 通过mainactivity 获取 contentFragment
		ContentFragment fragment = mainUI.getContentFragment();
		//用ContentFragment获取新闻中心
		NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();
		//修改新闻中心的fragmentLayout
		newsCenterPager.setCurrentDetailPager(position);
	}
	/*
	 * 打开或者关闭侧边栏
	 */
	protected void toggle(){
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();//状态是开，调用关，反之开
	}
	
	class LeftMenuAdapter extends BaseAdapter{
		

	@Override
	public int getCount() {
		return mNewsMenuData.size();
	}

	@Override
	public NewsMenuData getItem(int position) {
		return mNewsMenuData.get(position);
	}

	@Override 
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mActivity, R.layout.list_item_left_menu, null);
		TextView tvMenu = (TextView) view.findViewById(R.id.tv_menu);
		NewsMenuData item = getItem(position);
		tvMenu.setText(item.title);
		
		if(position == mCurrentPos){
			tvMenu.setEnabled(true);//文字变为红色
		}else{
			tvMenu.setEnabled(false);
		}
		
		return view;
	}

}
	}
