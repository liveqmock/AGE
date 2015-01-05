/**
 * Copyright (c) 2005-2011 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Fixtures.java 1593 2011-05-11 10:37:12Z calvinxiu $
 */
package com.age.core.orm;

import java.io.Serializable;

/**
 * 分页参数封装类.
 */
public class PageRequest implements Serializable {
	protected int pageNo = 1;
	protected int pageSize = 10;

	protected long totalItems = -1;

	//protected String orderBy = null;
	//protected String orderDir = null;

	//是否计算总行数
	protected boolean countTotal = true;
	//缓存总行数，即第一次加载页面计算总行数，分页不计算
	protected boolean cacheCount = false;
	//计算总行数的查询策略
	protected String countQueryGoal = null;

	//是否使用自定义计算总数
	protected boolean useOwnerCountTotal = false;
	// 自定义计算总数sql
	protected String ownerCountTotalSql = null;

	public PageRequest() {
	}

	public PageRequest(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 获得当前页的页号, 序号从1开始, 默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号, 序号从1开始, 低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数量, 默认为10.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量, 低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public long getTotalItems() {
		return totalItems;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalItems(final long totalItems) {
		this.totalItems = totalItems;
	}

	public boolean isUseOwnerCountTotal() {
		return useOwnerCountTotal;
	}

	public void setUseOwnerCountTotal(boolean useOwnerCountTotal) {
		this.useOwnerCountTotal = useOwnerCountTotal;
	}

	public String getOwnerCountTotalSql() {
		return ownerCountTotalSql;
	}

	public void setOwnerCountTotalSql(String ownerCountTotalSql) {
		this.ownerCountTotalSql = ownerCountTotalSql;
	}

	/**
	 * 是否默认计算总记录数.
	 */
	public boolean isCountTotal() {
		return countTotal;
	}

	/**
	 * 设置是否默认计算总记录数.
	 */
	public void setCountTotal(boolean countTotal) {
		this.countTotal = countTotal;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始.
	 */
	public int getOffset() {
		return ((pageNo - 1) * pageSize);
	}

	//用于jquery easyui的grid
	protected int rows = 1;
	protected int page = 10;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		setPageSize(rows);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		setPageNo(page);
	}

	public String getCountQueryGoal() {
		return countQueryGoal;
	}

	public void setCountQueryGoal(String countQueryGoal) {
		this.countQueryGoal = countQueryGoal;
	}

	public boolean isCacheCount() {
		return cacheCount;
	}

	public void setCacheCount(boolean cacheCount) {
		this.cacheCount = cacheCount;
	}

}
