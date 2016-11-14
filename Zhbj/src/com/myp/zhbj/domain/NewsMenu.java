package com.myp.zhbj.domain;

import java.util.ArrayList;
/*
 * 分类信息的封装
 * 使用Gson解析时注意以下技巧：大括号创建对象，中括号创建arraylist
 * 名称要高度一致(字段),类名可以改
 */
public class NewsMenu {
	public int retcode;
	 
	public ArrayList<Integer> extend;
	
	public ArrayList<NewsMenuData> data;
	//侧边栏菜单对象
	public class NewsMenuData{

		public int id;
		public String  title;
		public int type;
		
		public ArrayList<NewsTabData> children;
		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", children=" + children
					+ "]";
		}
	}
	//页签对象
	public class NewsTabData{
		public int id;
		public String  title;
		public int type;
		public String url;
		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}
		
	}
	@Override
	public String toString() {
		return "NewsMenu [data=" + data + "]";
	}
	
	
	
}

