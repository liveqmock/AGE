package com.age.back.sysmng.common;

import java.util.LinkedHashMap;
import java.util.Map;

public class SysMngContstant {

	public static final Integer SYS_MNG_LEAF_YES = 1;
	public static final Integer SYS_MNG_LEAF_NO = 0;

	public static final Integer SYS_MNG_STATE_DISABLED = 0;//禁用
	public static final Integer SYS_MNG_STATE_ENABLED = 1; //启用 
	private static Map<Integer, String> SYS_MNG_STATE = new LinkedHashMap<Integer, String>();
	static {
		SYS_MNG_STATE.put(SYS_MNG_STATE_ENABLED, "启用");
		SYS_MNG_STATE.put(SYS_MNG_STATE_DISABLED, "禁用");
	}

	public static String getSysMngStateCN(Integer key) {
		return SYS_MNG_STATE.get(key);
	}

	public static final String SYS_MNG_RESOURCE_TYPE_MODULE_ = "0"; //模块
	public static final String SYS_MNG_RESOURCE_TYPE_MEMU_ = "1"; //菜单
	public static final String SYS_MNG_RESOURCE_TYPE_ACTION_ = "2"; //操作
	private static Map<String, String> SYS_MNG_RESOURCE_TYPE = new LinkedHashMap<String, String>();
	static {
		SYS_MNG_RESOURCE_TYPE.put(SYS_MNG_RESOURCE_TYPE_MODULE_, "模块");
		SYS_MNG_RESOURCE_TYPE.put(SYS_MNG_RESOURCE_TYPE_MEMU_, "菜单");
		SYS_MNG_RESOURCE_TYPE.put(SYS_MNG_RESOURCE_TYPE_ACTION_, "操作");
	}

	public static String getSysMngResourceTypeCN(String key) {
		return SYS_MNG_RESOURCE_TYPE.get(key);
	}
	// 公告状态：0=初始；1=发布；2=撤销；
	public static final String STATUS_INIT = "0";//初始
	public static final String STATUS_RELESE = "1"; //发布 
	public static final String STATUS_CANCEL = "2"; //撤销
	
	private static Map<String, String> STATUS_STATE = new LinkedHashMap<String, String>();
	static {
		STATUS_STATE.put(STATUS_INIT, "初始");
		STATUS_STATE.put(STATUS_RELESE, "发布");
		STATUS_STATE.put(STATUS_CANCEL, "撤销");
	}

	public static String getStatusCN(String key) {
		return STATUS_STATE.get(key);
	}
}
