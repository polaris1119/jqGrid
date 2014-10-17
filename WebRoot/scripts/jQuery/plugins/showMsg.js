jQuery.fn.showMsg=function(json)
{	
	if(json==null)
		json={};
	var bodyScrollSize=this.getBodyScrollSize();
	var bodySize=this.getBodySize();
	var left=bodyScrollSize.left;
	var top=bodyScrollSize.top;
	if(json.autoMiddle!=true)
	{
		if(json.left)left=left+json.left;
		if(json.top)top=top+json.top;
	}
	else if(json.autoMiddle==true)
	{
		var _w=this.width();
		var _h=this.height();
		top=(bodySize.height-_h)/2 + top;
		left=(bodySize.width-_w)/2;
	}
	if(top+this.height()>bodyScrollSize.height)
		bodyScrollSize.height=top+this.height();
	if(left+this.width()>bodyScrollSize.width)
		bodyScrollSize.width=left+this.width();
	
	if(json.coverBody==true)
	{
		$("<iframe id='jq_effect_backGroundIframe' style='z-index:1;filter:mask();overflow:hidden;border:0px;margin:0; padding:0;position:absolute;width:"+bodyScrollSize.width+"px;height:"+bodyScrollSize.height+"px;top:0px;left:0px' scrolling='auto' frameborder='0' ></iframe>").appendTo("body"); 
		var jqCover=$("<div id='jq_effect_backGroundDiv'></div>");
		var _css = { "background-color":json.color==undefined?"#000":json.color, "position":"absolute","overflow":"hidden", "-moz-opacity": "0.75" , "filter":"alpha(opacity=75)","z-index":"2" ,"opacity":"0.75", "top" : "0px" , "left":"0px","width":bodyScrollSize.width,"height":bodyScrollSize.height };         
		jqCover.css(_css); 
		jqCover.appendTo("body"); 
	}
	if(typeof(json.msg)!="undefined")
		this.html(json.msg);
	this.css("height","auto");
	this.css("position","absolute").css("z-index","100");
	this.css("left",left+"px").css("top",top+"px").fadeIn("slow");
	if(jQuery.msgShowObj!=undefined)
	{
		try{jQuery.msgShowObj.fadeOut('slow');}catch(e){};
	}
	jQuery.msgShowObj=this;
	if(typeof(json.callback)!="undefined")
		jQuery.msgCallBack=json.callback;
	else
		jQuery.msgCallBack=null;
	jQuery.cc=undefined;
	if(json.autoClose!=false)
	{
		jQuery.cc=window.setTimeout("try{jQuery.msgShowObj.closeMsg();}catch(e){}",json.time==undefined?2000:json.time);
	}
	return this;
}
jQuery.fn.closeMsg=function()
{
	this.fadeOut('slow',jQuery.msgCallBack);
	jQuery.msgShowObj=undefined;
	$('#jq_effect_backGroundIframe').remove();
	$('#jq_effect_backGroundDiv').remove();
	
	return this;
}
jQuery.fn.getBodyScrollSize=function()
{
	var bodySL, bodyST, bodySW, bodySH; 
	if(window.pageXOffset) 
	{
		bodySL=window.pageXOffset; 
		bodySW=window.innerWidth; 
		bodyST=window.pageYOffset; 
		bodySH=window.innerHeight; 
	}
	else if(document.documentElement) 
	{	
		bodySL=document.documentElement.scrollLeft; 
		bodySW=document.documentElement.scrollWidth; //-10
		bodyST=document.documentElement.scrollTop; 
		bodySH=document.documentElement.clientHeight>document.documentElement.scrollHeight?document.documentElement.clientHeight:document.documentElement.scrollHeight;
	}
	else if(document.body) 
	{
		bodySL=document.body.scrollLeft; 
		bodySW=document.body.scrollWidth; 
		bodyST=document.body.scrollTop; 
		bodySH=document.body.clientHeight>document.body.scrollHeight?document.body.clientHeight:document.body.scrollHeight;
	}
	return {"top":bodyST,"left":bodySL,"width":bodySW,"height":bodySH};
}
jQuery.fn.getBodySize=function()
{
	var bodyCW, bodyCH; 
	if(window.innerWidth) 
		bodyCW=window.innerWidth; 
	else if(document.documentElement&&document.documentElement.clientWidth) 
		bodyCW=document.documentElement.clientWidth; 
	else if(document.body) 
		bodyCW=document.body.clientWidth; //author: meizz 
	if(window.innerHeight) 
		bodyCH=window.innerHeight; 
	else if(document.documentElement&&document.documentElement.clientHeight) 
		bodyCH=document.documentElement.clientHeight; 
	else if(document.body) 
		bodyCH=document.body.clientHeight; 
	
	return {"width":bodyCW,"height":bodyCH};
}
function _alert()
{
	alert("OK");
}