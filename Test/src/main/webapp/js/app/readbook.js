(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		
	 		var id= getQueryString("id");
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
	 		console.log("modelId:"+id);
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
				
				console.log(data)
				
				$(data).each(function(index,element){
					var Data = data[index];
					console.log(Data)
					$(".wrap ul").append(
					"<li><a href='readcontent.html?id="+Data.id+"'>"
					+"<div><img src='/readpicFile?filepath="+Data.articelPicPath+"'></div>"
					+"<h2>"+Data.title+"</h2>"
					+"<p>"+Data.articelDesc+"</p>"
					+"</a></li>")
				})
			}
			
			
			
			
		   return this;
	 	}
	
	 }
	 
	 main.init();
	
})(Zepto)
