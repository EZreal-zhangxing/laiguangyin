(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		var id= getQueryString("id",url);
	 		
	 		this.article(id);
	 		
	 		this.sub1();
	 		
	 	    this.sub2();
	 
	 		return this;
	 	},
	 	
	 	article:function(id){
	 		
	 		//console.log("asdfjh")
	 		$('#articelid').attr("value",id);
	 		//请求数据
	 		$.ajax({
			type:"get",
			url:"/getArticelContent/"+id,
			async:true,
			dataType:"json",
			success:function(data){
				list(data);
			  }
			
			}); 
			
			//遍历数据动态拼接
			function list(data){
				var Data;
				$(data).each(function(index,element){
					Data = element;
				});
				var time2 = new Date(Data.date).Format("yyyy-MM-dd"); 
				$(".wrap").append("<h1>"+Data.title+"</h1>"
					+"<time>"+time2+"</time><br/><br/>"
					+"<div style='margin:auto 30px;'><p>"+Data.content+"</p></div>");
				$('#modelid').attr("value",Data.modelId);
			}
		 
		   return this;
	 	},
	 	
	 	
	 	//预约1
	 	sub1:function(){
	 		
	 		$(".sub1").click(function(){
	 			 var userid=getCookie("userid");
	 			 if(userid == null || userid=='')
	 			{
	 				alert("请先登录！");
	 				window.location.href="appointcenter.html?addressUrl=1";
	 			}else
	 			{
	 				var name = $(".input #name").val();
		 		    var userTel = $(".input #userTel").val();
		 		    var userAddress = $(".input #userAddress").val();
		 		    var comAddress = $(".input #comAddress").val();
		 		    var articelid=$('#articelid').val();
		 		    var modelid=$('#modelid').val();
		 		   	var allright=true;
		 		     if(name == '')
		 		    {
		 		    	alert("请填写姓名!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(userTel == '')
		 		    {
		 		    	alert("请填写电话!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(userAddress == '')
		 		    {
		 		    	alert("请填写所属社区!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(comAddress == '')
		 		    {
		 		    	alert("请填写所在公司!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(allright)
		 		    {
		 		    	 $.ajax({
			 		    	type:"get",
			 		    	url:"/order?name="+name+"&userTel="+userTel+"&userAddress="+userAddress+"&comAddress="+comAddress+"&timeArea=&articelId="+articelid+"&modelId="+modelid+"&userId="+userid,
			 		    	async:true,
			 		    	success:function(data){
			 		    		if(data.code== 'success'){
						 			alert("预订成功！");
						 		}
						 		if(data.code== 'exist'){
						 			alert("已被预订！");
						 		}
						 		if(data.code== 'false'){
						 			alert("您已经预定！请勿重复预订！");
						 		}
						 		if(data.code== 'fail'){
						 			alert("人数已达到上限，无法预订！");
						 		}
			 		    	}
			 		    });
		 		    }
	 			}
	 		   
	 		})
	 	
	 	},
	 	
	 	//预约2
	 	sub2:function(){
			var loctime = (new Date()).Format("hh");
	 		$(".input1 span").each(function(i,n){
	 			
				var spanTime=$(n).data("time");
				if(spanTime>loctime){
					$(n).addClass("active");
				}
	
	 		});
	 		var time
	 		$(".input1").on("click","span",function(){
	 			
// 				time = $(this).html();
 				time = $(this).attr("value");
	 	    })
	 		
	 		$(".sub2").click(function(){
	 			//获取用户id
	 			 var userid=getCookie("userid");
	 			 if(userid == null || userid=='')
	 			{
	 				alert("请先登录！");
	 				window.location.href="appointcenter.html?addressUrl=1";
	 			}else
	 			{
	 				var name = $(".input1 #name").val();
		 		    var userTel = $(".input1 #userTel").val();
		 		    var userAddress = $(".input1 #userAddress").val();
		 		    var comAddress = $(".input1 #comAddress").val();
		 		    var timeArea = time;
		 		    var articelid=$('#articelid').val();
		 		    var modelid=$('#modelid').val();
		 		    var allright=true;
		 		     if(name == '')
		 		    {
		 		    	alert("请填写姓名!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(userTel == '')
		 		    {
		 		    	alert("请填写电话!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(userAddress == '')
		 		    {
		 		    	alert("请填写所属社区!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(comAddress == '')
		 		    {
		 		    	alert("请填写所在公司!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(timeArea == '' || timeArea == null)
		 		    {
		 		    	alert("请选择时间!");
		 		    	allright=false;
		 		    	return ;
		 		    }
		 		    if(allright)
		 		    {
		 		    	$.ajax({
			 		    	type:"get",
			 		    	url:"/order?name="+name+"&userTel="+userTel+"&userAddress="+userAddress+"&comAddress="+comAddress+"&timeArea="+timeArea+"&articelId="+articelid+"&modelId="+modelid+"&userId="+userid,
			 		    	async:true,
			 		    	success:function(data){
						 		if(data.code== 'success'){
						 			alert("预订成功！");
						 		}
						 		if(data.code== 'exist'){
						 			alert("已被预订！");
						 		}
						 		if(data.code== 'false'){
						 			alert("您已经预定！请勿重复预订！");
						 		}
						 		if(data.code== 'fail'){
						 			alert("人数已达到上限，无法预订！");
						 		}
			 		    	}
			 		    });
		 		    }
	 			}
	 		   
	 		})
	 	
	 	}
	 	
	 	
	 }
	 
	 main.init();
})(Zepto)
