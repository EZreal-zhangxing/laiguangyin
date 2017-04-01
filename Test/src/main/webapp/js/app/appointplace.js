(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		
	 		var id= getQueryString("id",url);
	 		
	 		
	 		this.titleList(id);
	 		
	 		return this;
	 	},
	 
		
	 	titleList:function(id){
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
					
					//判断文章如何显示    是否有无外链 
					function showoutUrl(){
						if (Data.outUrl != '') {
							
						  $(".wrap ul").append(
							"<li><a href='"+Data.outUrl+"'>"
							+"<p>"+Data.title+"</p>"
							+"</a></li>")
						  
						} else{
							
						  $(".wrap ul").append(
							"<li><a href='articontent.html?id="+Data.id+"'>"
							+"<p>"+Data.title+"</p>"
							+"</a></li>")
							
						}
						$(".wrap ul li").css({
							"background-image":"url(/readpicFile?filepath="+Data.articelPicPath+")",
							"background-size":"100% 100%",
							"background-repeat":"no-repeat"
						})
					}
			
					showoutUrl()
					
				})
			}
		 
		   return this;
	 	}
	 	
	 	
	 	
	 	
	 }
	 
	 main.init();
})(Zepto)
