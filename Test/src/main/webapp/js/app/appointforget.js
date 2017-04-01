(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		this.finish();
	 		
	 		return this;
	 	},
	 	
	 	//忘记密码
	 	finish:function(){
	 		
	 		$("#finish").click(function(){
	 	        var username = $("#username").val();
	 			var pwd = $("#pwd").val();
 		    	$.ajax({
 				type:"get",
 				url:"/findpassword?username="+username+"&password="+pwd,
 				dataType:"json",
 				async:false,
 				success:function(data){
 					if(data.code == 'success')
 					{
 						setCookie("userid",data.id);
 						setCookie("username",data.userName);
			 			setCookie("password",data.password);
			 			alert("密码修改成功！");	
			 			window.location.href="appointcenter.html";
			 			//跳转
 					}else
 					{
 						alert("密码修改失败！");	
 					}
 				}});
     		    //读取用户名和密码
	 		    console.log(getCookie("username"))
	 		    console.log(getCookie("password"))
	 		})
	 	}
	 }
	 main.init();
})(Zepto)
