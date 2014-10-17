/**
 * @author xuxinhua
 */
$(function()
{
	jQuery("#list2").jqGrid({
   	url:'ajax.txt',
	datatype: "json",
   	colNames:['编号','姓名','密码','年龄','地址','出生日期','备注'],
   	colModel:[
   		{name:'id',index:'id', width:55},
   		{name:'username',index:'username', width:90},
   		{name:'password',index:'password', width:100},
   		{name:'age',index:'age', width:80, align:"right"},
   		{name:'address',index:'address', width:80, align:"right"},		
   		{name:'time',index:'time', width:80,align:"right"},		
   		{name:'note',index:'note', width:150, sortable:false}		
   	],
   	rowNum:10,
   	rowList:[10,20,30],
    // imgpath: gridimgpath,
   	pager: $('#pager2'),
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
    caption:"SP/CP待审信息管理"
	}).navGrid('#pager2',{edit:true,add:true,del:true,view:true});
});