(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		$('#addressUrl').attr("value",getQueryString("addressUrl"));
	 		var username=getCookie("username");
	 		var userid=getCookie("userid");
    		var password=getCookie("password");
    		if((username != null) && (password !=null))
	    	{
	    		window.location.href="orderlist.html";
	    	}else
	    	{
				this.login();	    		
	    	}
	 		
	 		return this;
	 	},
	 	
	 	//登录
	 	login:function(){
	 		
	 		$("#login").click(function(){
	 			//var phone =/^0?1[34578]\d{9}$/;//手机 +86 
	 			var username = $("#username").val();
	 		    var password = $("#pwd").val();
	 		    var jzmm=$("input[type='checkbox']").is(':checked');
	 		    $.ajax({
	 		    	type:"get",
	 		    	url:"/login?username="+username+"&password="+password,
	 		    	async:true,
	 		    	success:function(data){
	 		    		if(data.code== 'success'){
	 		    			 //将输入内容写入cookie
				 		    setCookie("userid",data.id);
				 		    setCookie("username",data.userName);
				 		    setCookie("password",data.password);
				 		    if($('#addressUrl').val()==1)
				 		    	{
									window.history.go(-1);				 		    	
				 		    	}else
				 		    	{
				 		    		window.location.href="orderlist.html";
				 		    	}
	 		    		}else{
	 		    			alert("登录失败！");
	 		    		}
	 		    	}
	 		    });
	 		})
	 	
	 	}
	 }
	 
	 main.init();
})(Zepto)
