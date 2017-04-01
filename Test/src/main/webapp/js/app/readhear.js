(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		
	 		var id= getQueryString("id");
	 		console.log(id)
	 		if (id==23) {
			   $("title").text("晴耕雨读");
			}else if(id==24){
			   $("title").text("笃行同好");
			}else if(id==25){
			   $("title").text("愿抒心芷");
			}else if(id==26){
			   $("title").text("洽博多闻");
			}
	 		
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
					console.log(Data)
			
					function outUrl(){
				
						//判断文章如何显示    是否有无外链 
						if (Data.outUrl) {
							
						  $(".wrap ul").append(
					           "<li><a href='"+Data.outUrl+"'>"
								+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'>"
								+"<p>"+Data.title+"</p>"
								+"</a></li>")
						  
						} else{
							
						  $(".wrap ul").append(
							"<li><a href='readcontent.html?id="+Data.id+"'>"
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
