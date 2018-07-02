$package('CENYA.login');
CENYA.loginBe=function(){
		return{
		toLogin:function(){
			var form=$("#loginForm");
			if(form.form('validate')){
				//TODO
				CENYA.progress('Please waiting for login','Login...');
				//$.messager.alert("提示","500");
			/*	CENYA.submitForm(form,function(data){
					$.messager.alert('提示',data.success,'error');  
					CENYA.closeProgress();
					//刷新验证码
					if(data.success){
				 		window.location= "/rest/fist/main";
			        }else{
			       	   CENYA.alert('提示',data.msg,'error');  
			        }
				});*/
				
				
				
			}
			
		},
		init:function(){
			//TODO
			if(window.top != window.self){
				window.top.location =  window.self.location;
			}
			var loginForm=$("#loginForm");
			loginForm.submit(CENYA.loginBe.toLogin);
		},
}}(),
$(function(){
	CENYA.loginBe.init();
});	