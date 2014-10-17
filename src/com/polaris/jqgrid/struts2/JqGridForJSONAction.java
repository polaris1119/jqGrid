package com.polaris.jqgrid.struts2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 该Struts2向客户端返回一个json对象。为了简便，数据不是从数据库获得的。
 * jqGrid默认期望返回的json对象格式要求如下：
 * {"page":"1","total":"2","records":"13",
 * "rows":[
 * 		{id:"1",cell:["1","polaris","男","polaris@gmail.com","772618379","18329382732","1985-10-2"]},
 * 		{id:"2",cell:["2","张三","女","zhangsan@163.com","272618382","15329382732","1986-10-12"]},
 * 		{id:"3",cell:["3","王五","女","wangwu@yahoo.com","172635372","13329389832","1987-12-21"]},
 * 		{id:"4",cell:["4","赵六","男","zhaoliu@sina.com","372618332","18929343731","1988-09-22"]}
 * 	]
 * }
 * 当然，在js中，可以通过jqGrid的jsonReader属性来修改默认格式
 * 因为默认的格式，rows的数据要求顺序不能变，且每个字段都得有值（空也得有"")。因而，
 * 在jsonReader中定义repeatitems : false。这样，rows就变成了：
 * "rows":[
 * 		{id:"1",userName:"polaris",gender:"男",email:"polaris@gmail.com",QQ:"772618379",mobilePhone:"18329382732",birthday:"1985-10-2"]},
 * 		{id:"2",userName:"徐新华",gender:"男",email:"xh.xu@163.com",QQ:"272618382",mobilePhone:"15329382732",birthday:"1986-10-12"]},
 * 		{id:"3",userName:"王五",gender:"女",email:"wangwu@yahoo.com",QQ:"172635372",mobilePhone:"13329389832",birthday:"1987-12-21"]},
 * 		{id:"4",userName:"赵六",gender:"女",email:"zhaoliu@sina.com",QQ:"372618332",mobilePhone:"18929343731",birthday:"1988-09-22"]}
 * 	]
 * @author xuxinhua
 *
 */
public class JqGridForJSONAction extends JsonBaseAction
{
	private static final long serialVersionUID = 132383828833L;
	
	/**
	 * 该方法得到数据并构造json对象以便返回给客户端
	 * @return 
	 * @throws Exception
	 */
	public String execute() throws Exception
	{
		// 构建几条数据
		int i = 0;
		for(i=0;i<4;++i)
		{
			// 定义一个Map<String,Object>存放一行行数据。（跟从Servlet获得数据类似，只不过此处不需要用json-lib）
			Map<String, Object> row = new HashMap<String, Object>();
			
			row.put("id", i);
			if(i%2==0)
			{
				row.put("userName", "polaris");
				row.put("gender", "女");
			}
			else
			{
				row.put("userName", "徐新华");
				row.put("gender", "男");
			}
			row.put("email", "polaris@gmail.com");
			row.put("QQ", "772"+i+"1837"+i);
			row.put("mobilePhone", "132"+i+"1837"+i+"3"+i);
			row.put("birthday", "198"+i+"-10-"+"1"+i);
			
			dataRows.add(row);
		}
		
		// 给另外三个返回参数设值
		setTotalPages(1);		// 总页数
		setCurPage(1);			// 当前页
		setTotalRecords(i);		// 总记录数
		
		return SUCCESS;
	}
	
	/*
	 * 以下getter方法必须实现，struts2-json插件会将这些getter方法序列化，以便输出json对象。
	 * 这些getter方法只需返回相应的属性即可。如getCurPage应该返回curPage
	 * （curPage在JsonBaseAction中有定义）
	 */
	@Override
	public int getCurPage() 
	{
		return this.curPage;
	}

	@Override
	public List<Map<String, Object>> getDataRows() 
	{
		return this.dataRows;
	}

	@Override
	public int getTotalPages() 
	{
		return this.totalPages;
	}

	@Override
	public int getTotalRecords() 
	{
		return this.totalRecords;
	}
}
