package com.myp.zhbj.utils;

import android.content.Context;

/*
 * 网络缓存工具类
 */
public class CacheUtils {
	/*
	 * 以url为key,以json为value,保存在本地
	 */
	public static void setCache(String url,String json,Context ctx){
		PrefUtils.setString(ctx, url, json);
	}
/*	
	 * 以MD5(url为文件名,查找有没有有没有文件，以json为value,保存在本地
	 
	public static void setCache(String url,String json,Context ctx){
		PrefUtils.setString(ctx, url, json);
	}*/
	//获取缓存
	public static String getCache(String url,Context ctx){
		//查找有没有一个文件叫MD5(url)的，有的话缓存
		return PrefUtils.getString(ctx, url, null);
	}
	
}
