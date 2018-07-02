$package('CENYA');
var CENYA={
		//弹出框
		alert:function(title,msg,icon,callback){
			$.messager.alert(title,msg,icon,callback);
		},
		//等待框
		progress:function(title,msg){
		var win=	$.messager.progress({
				title:title ||'OH OH',
			    msg:  msg   ||'AO AO' 
			});
		} ,
		//关闭等待框
       closeProgress:function(){
	   $.messager.progress('close');
      },
      //ajax提条表单 最后一步
      ajaxSubmit:function(form,option){
  		form.ajaxSubmit(option);
  	  },
      
      //from表单 callback函数 
      submitForm:function(form,callback,dataType){
    	  var options={
    			  //url : 默认传入表单
    			 //  url:'/rest/page/toLogin',
    			  type:'post', //默认传入表单 覆盖
  			 	  dataType: dataType||'json', //默认NULL
    			  success:function(data){
    				  $.messager.alert("提示","500");
    				  if($.isFunction(callback)){
    					  callback(data);
    				  }
    			  },
    			  error:function(response, textStatus, errorThrown){
    				  CENYA.closeProgress();
    				  var data = $.parseJSON(response.responseText);
    				  CENYA.alert('提示', data.msg || "请求出现异常,请联系管理员",'error');
    				  
    			  },
    			  
    	  }
    	  
    	  CENYA.ajaxSubmit(form ,options);
      },
		
		
}