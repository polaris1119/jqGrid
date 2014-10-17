/**
 * 弹出一个窗口，列出一类信息，用于选择。
 * @author xuxinhua
 */

(function($){   
    $.extend({
        //显示窗体
        showDialog:function(dataUrl,loadingText){
            $("body").append("<div id='bgDiv'></div><div id='databgDiv' style='position:fixed !important; position:absolute; z-index:1001; text-align:center;top:50%; left:50%;'></div>");
            $("#bgDiv").css({'position':'absolute','left':'0','top':'0px','z-index':'1000','background':'lightgrey','filter':'alpha(opacity=40)','opacity':'0.4','width':'100%','height':'100%'});
            $.ajax({
                url:dataUrl,
                beforeSend:function(xhr){
                    $("#databgDiv").append("<span>"+loadingText+"</span>");
                },
                success:function(data,status){
                    $("#databgDiv").load(dataUrl);
                },
                error:function(xhr,error){
                    $("#databgDiv").html(error);
                }
            });
        },
        //隐藏窗体
        hideDialog:function()
        {
            $("#bgDiv").remove();
            $("#loginDiv").fadeOut();
        }
    });
})(jQuery);