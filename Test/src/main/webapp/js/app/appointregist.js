(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		this.regist();
	 		
	 		return this;
	 	},
	 	//注册
	 	regist:function(){
	 		
	 		$("#regist").click(function(){
	 	        var username = $("#username").val();
	 			var pwd = $("#pwd").val();
	 			var result=$('#result').val();
	 			if(result == 'success')
	 			{
	 				$.ajax({
		 				type:"get",
		 				url:"/regist?username="+username+"&password="+pwd,
		 				dataType:"json",
		 				async:false,
		 				success:function(data){
		 					if(data.code == 'success')
		 					{
		 						setCookie("userid",data.id);
		 						setCookie("username",data.userName);
					 			setCookie("password",data.password);
					 			alert("注册成功！");
					 			window.location.href="appointcenter.html";
					 			//跳转
		 					}else
		 					{
		 						alert("注册失败！");	
		 					}
		 				}
	 		    	});
	 			}else
	 			{
	 				alert("用户名重复，请先修改用户名！");	
	 			}
	 		})
	 	
	 	}
	 }
	 $('#username').blur(function(){
		 var username=$('#username').val();
		 $.ajax({
				type:"get",
 				url:"/checkusername?username="+username,
 				dataType:"json",
 				async:false,
 				success:function(data){
 					if(data.code == 'success')
 					{
 						$('#msg').html("");
 						$('#result').attr('value','success');
 					}else
 					{
 						$('#result').attr('value','false');
 						$('#msg').html(data.message);
 					}
 				}
		});
	 });
	 main.init();
})(Zepto)
