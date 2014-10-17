/**
 * 使用jQuery UI中的DatePicker插件实现日期选择功能。
 * @author xuxinhua
 */

$(function()
{
	$("#effDate").datepicker(
	{
		showMonthAfterYear: true,	// 月在年之后显示
		changeMonth: true,			// 允许选择月份
		changeYear: true,			// 允许选择年份
		dateFormat:'yy-mm-dd',		// 设置日期格式
		showClearButton: true,
		//clearText: '清除',
		closeText:'关闭',			// 只有showButtonPanel: true才会显示出来
		duration: 'fast',
		showAnim:'fadeIn',
		showOn:'button',
		buttonImage: 'images/commons/calendar.gif',
		buttonImageOnly: true,
		buttonText:'选择日期',
		showButtonPanel: true,
		showOtherMonths: true,
		//appendText: '(yyyy-mm-dd)',
		onSelect: function(dateText, inst) 			// 使结束时间大于开始时间
		{
			/**
			 * 以下写法在IE中出现问题。
			 * $('#expDate').datepicker('option', 'minDate', new Date(dateText.replace(/-/g,',')));
			 * 时，在结束（过期时间）选择时，年会没有，而且控制会失效。通过调试，发现new Date(dateText.replace(/-/g,','))
			 * 返回的结果是NaN。说明Date对象不能这么构造（但是Firefox可以）
			 */ 
			 var arys = new Array();
			 var arys = dateText.split('-');
			 $('#expDate').datepicker('option', 'minDate', new Date(arys[0],arys[1]-1,arys[2]));
		}
	});
	
	$("#expDate").datepicker(
	{
		showMonthAfterYear: true,	// 月在年之后显示
		changeMonth: true,			// 允许选择月份
		changeYear: true,			// 允许选择年份
		dateFormat:'yy-mm-dd',		// 设置日期格式
		showClearButton: true,		// 自定义的方法(1.7.2没有清除按钮）
		//clearText: '清除',			// 自定义的文本，在文档在有定义（js中）
		closeText:'关闭',			// 只有showButtonPanel: true才会显示出来
		duration: 'fast',
		showAnim:'fadeIn',
		showOn:'button',			// 在输入框旁边显示按钮触发，默认为：focus。还可以设置为both
		buttonImage: 'images/commons/calendar.gif',			// 按钮图标
		buttonImageOnly: true,								// 不把图标显示在按钮上，即去掉按钮
		buttonText:'选择日期',
		showButtonPanel: true,
		showOtherMonths: true,
		//appendText: '(yyyy-mm-dd)',
		onSelect: function(dateText, inst)
		{
			var arys = new Array();
			var arys = dateText.split('-');
			$('#effDate').datepicker('option','maxDate',new Date(arys[0],arys[1]-1,arys[2]));
		}
	});
});