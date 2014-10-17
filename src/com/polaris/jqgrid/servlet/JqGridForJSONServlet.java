package com.polaris.jqgrid.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 该Servlet向客户端返回一个json对象。为了简便，数据不是从数据库获得的。
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
public class JqGridForJSONServlet extends HttpServlet 
{
	private static final long serialVersionUID = 132383828833L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 定义返回的数据类型：json，使用了json-lib
		JSONObject jsonObj = new JSONObject();
		
		// 根据jqGrid对JSON的数据格式要求给jsonObj赋值
		jsonObj.put("page", 1);		// 当前页
		jsonObj.put("total", 1);	// 总页数
		jsonObj.put("records", 4);	// 总记录数
		
		// 定义rows，存放数据
		JSONArray rows = new JSONArray();
		// 放入4条数据
		for(int i=0;i<4;i++)
		{
			// 存放一条记录的对象
			JSONObject cell = new JSONObject();
			cell.put("id", i);
			if(i%2==0)
			{
				cell.put("userName", "polaris");
				cell.put("gender", "女");
			}
			else
			{
				cell.put("userName", "徐新华");
				cell.put("gender", "男");
			}
			cell.put("email", "polaris@gmail.com");
			cell.put("QQ", "772"+i+"1837"+i);
			cell.put("mobilePhone", "132"+i+"1837"+i+"3"+i);
			cell.put("birthday", "198"+i+"-10-"+"1"+i);
			
			// 将该记录放入rows中
			rows.add(cell);
		}
		
		// 将rows放入json对象中
		jsonObj.put("rows", rows);
		
		// 自控制台打印输出，以检验json对象生成是否正确
		System.out.println("要返回的json对象：\n" + jsonObj.toString());
		
		// 设置字符编码
		resp.setCharacterEncoding("UTF-8");
		// 返回json对象（通过PrintWriter输出）
		resp.getWriter().print(jsonObj);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
}
