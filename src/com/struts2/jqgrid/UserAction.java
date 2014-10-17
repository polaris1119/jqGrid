package com.struts2.jqgrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction {
	
	private String page;
	private String total;
	private String sidx;
	
	/**
	 * 显示用户列表，封装为JsonView类型，前端使用jqGrid插件。设置的变量名都不得更改
	 * 集合类均使用泛型
	 * @author Sarin
	 */
	/*
	public String userlist() {
		// 准备jqGrid分页参数
		int total_pages = 0;
		int limit = Integer.parseInt(rows);
		int t_page = Integer.parseInt(page);
		if (sidx == null) {
			sidx = "userId";
		}
		//查询需要分页数据的总记录数
		int count = Integer.parseInt(getServMgr().getUserService().getUsersCount());
		//计算分页参数
		if (count > 0) {
			total_pages = (int) Math.ceil(count / limit) + 1;
		}
		if (t_page > total_pages) {
			t_page = total_pages;
		}
		int start = limit * t_page - limit;
		//获取记录列表
		List users = getServMgr().getUserService().getAllUsers(sidx + " " + sord, start, limit);
		List<Map> rows = new ArrayList<Map>();
		for (int i = 0; i < users.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			//封装jqGrid可识别的数据格式
			Object[] cell = new Object[] { ((Map) users.get(i)).get("USERID"), ((Map) users.get(i)).get("USERNAME"),
					((Map) users.get(i)).get("REALNAME"), ((Map) users.get(i)).get("SEX"),
					((Map) users.get(i)).get("BIRTHDAY") };
			map.put("id", ((Map) users.get(i)).get("USERID"));//设置更新时的更新依据，一般为主键
			map.put("cell", cell);
			rows.add(map);
		}
		//构建Json类型的数据，参数名不可为其他的
		JSONObject result = new JSONObject();
		result.put("total", total_pages);
		result.put("records", count);
		result.put("page", t_page);
		result.put("rows", rows);
		//封装成jsonView向客户端输出
		jsonView = new JsonView(result);
		return "userlist";
	}*/

}
