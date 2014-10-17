<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>
		<title>jqGrid与Servlet集成</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<link rel="stylesheet" type="text/css" media="screen" href="styles/themes/redmond/jquery-ui-1.7.2.custom.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="styles/themes/ui.jqgrid.css" />
		
		<!-- 引入jQuery -->
		<script type="text/javascript" src="scripts/jQuery/jquery-1.3.2.js"></script>
		<script src="scripts/jQuery/plugins/jquery-ui-1.7.2.custom.min.js" type="text/javascript"></script>
		<script src="scripts/jQuery/plugins/grid.locale-zh_CN.js" type="text/javascript"></script>
		<script src="scripts/jQuery/plugins/jquery.jqGrid.min.js" type="text/javascript"></script>
		
		<script type="text/javascript">   
			$(function()
			{
				$("#gridTable").jqGrid({
					url:'',
					datatype: "json",
					height: 250,
					colNames:['编号','用户名', '性别', '邮箱', 'QQ','手机号','出生日期'],
					colModel:[
						{name:'id',index:'id', width:60, sorttype:"int"},
						{name:'userName',index:'userName', width:90},
						{name:'gender',index:'gender', width:90},
						{name:'email',index:'email', width:125,sorttype:"string"},
						{name:'QQ',index:'QQ', width:100},		
						{name:'mobilePhone',index:'mobilePhone', width:120},		
						{name:'birthday',index:'birthday', width:100, sorttype:"date"}
					],
					sortname:'id',
					sortorder:'asc',
					viewrecords:true,
					rowNum:10,
					rowList:[10,20,30],
					pager:"#gridPager",
					caption: "jqGrid与Servlet集成"
			}).navGrid('#gridPager',{edit:false,add:false,del:false});
	</script>  
		
	</head>
	<body>
		<table id="gridTable"></table>
		<div id="gridPager"></div>
	</body>
</html>
