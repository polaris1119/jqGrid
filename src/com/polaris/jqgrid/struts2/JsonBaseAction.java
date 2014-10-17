package com.polaris.jqgrid.struts2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.polaris.jqgrid.util.Page;

/**
 * <p>
 * 该类是所有需要返回json数据的Action的<b>抽象基类</b>。在展示数据时，使用了jQuery的插件jqGrid，
 * 它对返回的json数据格式有一定的要求。该基类就处理了这些统一的格式要求。
 * 需要返回json格式数据被jqGrid使用的，应该继承该类。<br/>
 * <b><font color='red'>注意：所有子类应该实现以下get方法，并只简单的返回相应的属性值。</font></b>
 * <ul>
 * 	<li>{@link JsoBaseAction#getTotalPages()}</li>
 * 	<li>{@link JsoBaseAction#getCurPage()}</li>
 * 	<li>{@link JsoBaseAction#getTotalRecords()}</li>
 * 	<li>{@link JsoBaseAction#getDataRows()}</li>
 * </ul>
 * 之所以将这些get方法定义为抽象的，是因为struts2的json插件只会序列化Action类，而不会序列化其父类。
 * </p>
 * 
 * <p><font color='red'>
 * 继承该类的Action方法，除了搜索（它需要返回JSON数据，因此可以返回SUCCESS)，其他操作都应该返回null而不是SUCCESS之类的。
 * 这意味着struts配置中，result什么也不用配，即没有相应的视图资源——这是AJAX请求。
 * </font></p>
 * 
 * <p>
 * 该类定义了以下几个字段：totalPages、curPage、totalRecords和dataRows。
 * 这几个字段是jqGrid格式的要求，而此处是自定义的。因此，在配置jqGrid接收服务器
 * 返回的数据格式时，应该配置成这几个名字。(因为默认名字不是这样的，默认名字为：page,total,records,rows）
 * 当然，也可以通过@JSON注解来指定与默认一样的名字
 * </p>
 * 
 * <p>
 * 另外一个字段page，则是一个分页类。因为jqGrid会向服务器端传递分页参数，
 * 用一个分页类接收这些参数。同样，为使Struts能够为page赋值，需要修改jqGrid默认的分页参数名。
 * jqGrid默认分页名为：（默认定义在options参数中的prmNames数组中）
 * <ul>
 * 	<li>page->显示第几页</li>
 * 	<li>rows->每页显示几条记录</li>
 * 	<li>sidx->排序字段</li>
 * 	<li>sord->排序方式（asc/desc)</li>
 * </ul>
 * 应用中应该根据{@link com.polaris.jqgrid.util.Page}类中的定义设置。设置为：
 * prmNames:{rows:"page.pageSize",page:"page.curPageNo",sort:"page.orderBy",order:"page.order"}
 * 
 * prmNames数组的默认值为：
 * prmNames: {page:"page",rows:"rows", sort: "sidx",order: "sord", search:"_search", nd:"nd", npage:null}
 * </p>
 * 
 * @author xuxinhua
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class JsonBaseAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 该属性专门用于接收删除的数据的ID（主键）。注意，当支持一次删除多记录时，id的值是通过','号分隔的多个
	 */
	protected String id;
	
	/**
	 * 分页类
	 */
	protected Page page = new Page();
	/**
	 * 以下属性用于序列化成json格式的数据。名称不能改。如果要改，客户端页面对应的地方也要改；
	 * 或通过@JSON来指定序列化的名字
	 */
	/**
	 * 总页数
	 */
	protected int totalPages;
	/**
	 * 显示第几页
	 */
	protected int curPage;
	/**
	 * 总记录数
	 */
	protected int totalRecords;
	/**
	 * 保存实际的数据
	 */
	protected List<Map<String,Object>> dataRows = new ArrayList<Map<String,Object>>();

	public JsonBaseAction() {
		super();
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public abstract int getTotalPages();

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public abstract int getCurPage();

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	public abstract int getTotalRecords();

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	/**
	 * 注意该方法的返回值：List。实际上包含了实际的数据，
	 * 而这些数据是放在Map<String, Object>中的。
	 * 因而，子类在action方法如：execute中，应该构建一个
	 * Map<String, Object>对象，将数据放入其中，并把该对象放入
	 * List中。
	 * @return
	 */
	public abstract List<Map<String, Object>> getDataRows();

	public void setDataRows(List<Map<String, Object>> dataRows) {
		this.dataRows = dataRows;
	}

}