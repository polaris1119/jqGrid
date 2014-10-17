package com.polaris.jqgrid.util;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * @param <T> Page中记录的类型.
 * @author xuxinhua
 */
public final class Page<T> {

	// 公共变量
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	public static final int MIN_PAGESIZE = 1;			// 每页显示的最少记录数
	public static final int MAX_PAGESIZE = 200;			// 每页显示的最大记录数

	// 分页参数
	protected int curPageNo = 1;						// 当前所在页码（在第几页）
	protected int pageSize = MIN_PAGESIZE;		 		// 当前每页显示的记录数
	protected String orderBy = null;					// 排序字段
	protected String order = null;						// 排序方式（升序或降序）
	protected boolean autoCount = false;				// 查询对象时是否自动另外执行count查询获取总记录数

	// 返回结果
	protected List<T> result = Collections.emptyList();	// 当前页的实际记录数
	protected int totalCount = -1;						// 记录总数

	/**
	 * 默认构造函数
	 */
	public Page() {
		super();
	}
	/**
	 * 通过设置每页显示多少条记录来构造对象
	 * @param pageSize
	 */
	public Page(final int pageSize) {
		setPageSize(pageSize);
	}
	/**
	 * 
	 * @param pageSize
	 * @param autoCount
	 */
	public Page(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		this.autoCount = autoCount;
	}

	// 查询参数函数

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getCurPageNo() {
		return curPageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 * @param curPageNo
	 */
	public void setCurPageNo(final int curPageNo) {
		this.curPageNo = curPageNo;

		if (curPageNo < 1) {
			this.curPageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数量,默认为10.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,超出MIN_PAGESIZE与MAX_PAGESIZE范围时会自动调整.
	 * @param pageSize
	 */
	public void setPageSize(final int pageSize) {

		this.pageSize = pageSize;
		
		if (pageSize < MIN_PAGESIZE) {
			this.pageSize = MIN_PAGESIZE;
		}
		else if (pageSize > MAX_PAGESIZE) {
			this.pageSize = MAX_PAGESIZE;
		}
	}

	/**
	 * 根据curPageNo和pageSize（每页显示记录数）计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	public int getFirst() {
		return ((curPageNo - 1) * pageSize);
	}

	/**
	 * 获得排序字段,无默认值。多个排序字段时用','分隔,仅在Criterion查询时有效.
	 */
	public String getOrderBy() {
		return this.orderBy;
	}

	/**
	 * 设置排序字段。多个排序字段时用','分隔.仅在Criterion查询时有效.
	 * @param orderBy 要排序的字段
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 是否已设置排序字段,仅在Criterion查询时有效.
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(orderBy);	// isNotBlank判断：当orderBy不等于null、""或" "时返回true
	}

	/**
	 * 获得排序方向,默认为asc,仅在Criterion查询时有效.
	 */
	public String getOrder() {
		return this.order;
	}

	/**
	 * 设置排序方式向,仅在Criterion查询时有效.
	 * @param order 可选值为desc或asc，多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		// 检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr)
					&& !StringUtils.equals(ASC, orderStr))
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
		}

		this.order = StringUtils.lowerCase(order);
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数,默认为false,仅在Criterion查询时有效.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数,仅在Criterion查询时有效.
	 * @param autoCount
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	// 查询结果函数

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}
	
	/**
	 * 设置页内的记录列表.
	 * @param result
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数,默认值为-1.
	 */
	public int getTotalCount() {
		return totalCount;
	}
	
	/**
	 * 设置总记录数
	 * @param totalCount
	 */
	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数,默认值为-1.
	 */
	public int getTotalPages() {
		if (totalCount < 0)
			return -1;

		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (curPageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号,序号从1开始.
	 */
	public int getNextPage() {
		if (isHasNext())
			return curPageNo + 1;
		else
			return curPageNo;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (curPageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号,序号从1开始.
	 */
	public int getPrePage() {
		if (isHasPre())
			return curPageNo - 1;
		else
			return curPageNo;
	}
}
