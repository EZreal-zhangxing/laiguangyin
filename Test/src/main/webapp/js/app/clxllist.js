(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		
	 		var id= getQueryString("id");
	 		if (id==9) {
			   $("title").text("教育就读");
			}else if(id==10){
			   $("title").text("计划生育");
			}else if(id==11){
			   $("title").text("卫生医疗");
			}else if(id==12){
			   $("title").text("文化服务");
			}else if(id==13){
			   $("title").text("法律咨询");
			}else if(id==14){
			   $("title").text("民政保险");
			}else if(id==15){
			   $("title").text("人口管理");
			}else if(id==16){
			   $("title").text("社区居委会");
			}else if(id==17){
			   $("title").text("物业");
			}else if(id==18){
				$("title").text("派出所");
			}else if(id==19){
				$("title").text("学校");
			}else if(id==20){
				$("title").text("文体场馆");
			}else if(id==21){
				$("title").text("医院工作站");
			}else{
				$("title").text("工商税务");
			};
	 		
	 		this.titleList(id);
	 		
	 		return this;
	 	},
	 	
	 	titleList:function(id){
	 		//读取cookies中的用户ID
	 		var cookies=document.cookie.split(";");
	 		var userid=0;
	 		for(var i=0;i<cookies.length;i++)
 			{
	 			var key_value=cookies[i].split("=");
	 			if(key_value[0] == 'userid')
	 			{
	 				userid=key_value[1];
	 			}
 			}
	 		//请求数据
	 		$.ajax({
			type:"get",
			url:"/getArticelsBymodel/"+id+"/"+userid,
			async:true,
			dataType:"json",
			success:function(data){
				list(data);
				
			  }
			
			}); 
			
			
			//遍历数据动态拼接
			function list(data){
				
				$(data).each(function(index,element){
					var Data = data[index];
					console.log(Data.outUrl)
					console.log("modelId:"+id);
					
					//判断文章如何显示    是否有无外链 
					function outUrl(){
						if (Data.outUrl) {
							
						  $(".wrap ul").append(
							"<li><a href='"+Data.outUrl+"'>"
							+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'>"
							+"<p>"+Data.title+"</p>"
							+"</a></li>")
						  
						} else{
							
						  $(".wrap ul").append(
							"<li><a href='clxlcontent.html?id="+Data.id+"'>"
							+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'>"
							+"<p>"+Data.title+"</p>"
							+"</a></li>")
							
						}
					}
					
					
					outUrl()
					
				})
			}
		   return this;
	 	}
	 	
	 	
	 	
	 }
	 
	 main.init();
})(Zepto)
