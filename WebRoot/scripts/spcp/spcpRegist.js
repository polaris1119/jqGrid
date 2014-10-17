/**
 * @author xuxinhua
 */

/**
* 验证表单
*/
$(function()
{
	/**
	 * 使用validation框架进行验证
	 */
	$("#spcpRegForm").validate(
	{
		rules:
		{
			spId:
			{
				required: true,
				maxlength: 21,
				digits: true,
				remote:
				{
					url:'ajax/validateSpId.action',//后台处理程序
					type:'post',			 //数据发送方式
					dataType:'json' 		 //接受数据格式
					/*data:					 //要传递的数据（默认已传递本字段值，可在此传其他字段）
					{
						spId:function(){return $("#spId").val();}
					}*/
				}
			},
			spName:
			{
				required: true,
				maxlength: 99
			},
			type: {selectNone: true},		// 即使只有一个也需要用{}括起来
			webAccount:
			{
				required: true,
				maxlength: 20
			},
			webPassword:
			{
				required: true,
				maxlength: 50
			},
			webPasswordConfirm:
			{
				required: true,
				equalTo: '#webPassword'
			},
			vaspId:
			{
				required: true,
				maxlength: 8
			},
			vasId:
			{
				required: true,
				maxlength: 50
			},
			spSrvPhone:
			{
				required: true,
				maxlength: 20,
				isPhone: true
			},
			effDate:{required:true},
			expDate:{required:true}
		},
		
		errorElement: 'span',						// 放置错误信息的元素，可以是其他的。
		errorPlacement: function(error,element)		// 将错误提示信息放在什么地方
		{
			error.appendTo(element.parent("li"));
		},
		success: function(label)
		{
			label.text(" ")						// 将错误内容清空，一定要是" "有空格，否则IE有问题。
				.addClass("success");
			
		}
	});
	
	/**
	 * 自定义验证规则——增加对select的验证
	 */
	$.validator.addMethod( 
		"selectNone", 				// name验证方法名
		function(value, element)    // 验证规则
		{
			if (value == "none") 	// select默认值需要设置为"none"(当然可以自定义其他值）
			{ 
				return false; 
			} 
			else 
			{
				return true; 
			}
		}, 
		"必须选择一项" 	// 默认验证消息（自定义规则信息的国际化是否不起作用？）
	); 
	
	/**
	 * 自定义验证规则——对电话号码进行验证
	 */
	$.validator.addMethod(
		"isPhone", 
		function(value, element) 
		{    
			var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
			return this.optional(element) || (tel.test(value));    
		}
		, "电话号码格式不对."
	);    

	
	/**
	 * 当“投诉电话表”单元素获得焦点时，弹出温馨提示，以指导用户输入正确的电话号码
	 */
	$("#spSrvPhone").focus(function()		// 为表单元素绑定获取焦点事件
	{
		$(this).nextAll("span").remove();
		$(this)
			.parent(".liEff").append("<span class='toolTip'>推荐格式：010-12345678</span>");
	});
	
	/**
	 * 内容改变后，把温馨提示移除
	 */
	$("#spSrvPhone").blur(function()
	{
		$(this).nextAll(".toolTip").remove();		// 移除温馨提示
	});
	
	/**
	 * 当表单元素第一次获得焦点时，弹出温馨提示
	 * $(".inputEff").one("focus",function()		// 为所有表单元素绑定获取焦点事件（只执行一次）
	{
		var $parent = $(this).parent(".liEff");
		var $prev = $(this).prev(".labelEff");	// 获得标签元素
		var tipStr = "请输入" + $prev.attr("title");
		$parent.append("<span class='tip'>" + tipStr + "</span>");
	});
	
	 */
	
	/**
	 * 内容改变后，把温馨提示移除
	 * $(".inputEff").one("blur",function()
	{
		$(this).nextAll(".tip").remove();		// 移除温馨提示
	});
	 */
});
























