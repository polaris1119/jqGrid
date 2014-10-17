/**
 * @author xuxinhua
 */
$(function()
{
	$("#spcpInfo").jqGrid(
	{
		url:'json/spcpList.action',			// 请求的服务端地址
		datatype:'json',
		colNames:['编号','姓名','密码','年龄','地址','出生日期'],
		colModel:
		[
			{name:'id',index:'id',width:90,sorttype:'int'},
			{name:'username',index:'name',width:110},
			{name:'password',index:'password',width:80},
			{name:'age',index:'age',width:80},
			{name:'address',index:'address',width:80},
			{name:'time',index:'time',width:80}
		],
		rowNum:10,
		rowList:[10,20,30],
		pager:$("#spcpPager"),
		
		sortname:'id',
		viewrecords:true,
		sortorder:'desc',
		caption:'SP/CP待审信息管理',
		height:368,
		width:800
	}).navGrid('#spcpPager',{edit:false,add:false,del:false});
});