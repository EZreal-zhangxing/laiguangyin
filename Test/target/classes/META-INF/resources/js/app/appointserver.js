(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		
	 		var id= getQueryString("id",url);
	 		console.log(id)
	 		
	 		
	 		this.titleList(id);
	 		
	 		this.dianzan();
	 		
	 		//this.cookies();
	 		
	 		return this;
	 	},

		
	 	titleList:function(id){
	 		
	 		//console.log("asdfjh")
	 		var cookies=document.cookie.split(";");
	 		var userid=getCookie("userid");
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
					function outUrl(){
						if (Data.outUrl) {
							console.log(Data.haslike);
							if(Data.haslike ==0)
							{
									 $(".wrap ul").append(
								"<li><a href='"+Data.outUrl+"'>"
								+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'></a>"
								+"<p><a href='"+Data.outUrl+"'><i>"+Data.title+"</i></a>"
								   +"<span><b class='left'><i class='on'>"+Data.orderNum+"</i>/<i>"+Data.maxMannum+"</i></b>"
								         +"<b class='right dz'><i class='heart'>♡</i>"
								         +"<i class='dznum' data-haslike='"+Data.haslike+"' data-id='"+Data.id+"'>"+Data.likeNum+"</i></b>"
								   +"</span></p></li>")
							}else
							{
									 $(".wrap ul").append(
								"<li><a href='"+Data.outUrl+"'>"
								+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'></a>"
								+"<p><a href='"+Data.outUrl+"'><i>"+Data.title+"</i></a>"
								   +"<span><b class='left'><i class='on'>"+Data.orderNum+"</i>/<i>"+Data.maxMannum+"</i></b>"
								         +"<b class='right dz on'><i class='heart'>&#9829;</i>"
								         +"<i class='dznum' data-haslike='"+Data.haslike+"' data-id='"+Data.id+"'>"+Data.likeNum+"</i></b>"
								   +"</span></p></li>")	
							}
						  
						} else{
							if(Data.haslike ==0)
							{
							  $(".wrap ul").append(
								"<li><a href='articlserver.html?id="+Data.id+"'>"
								+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'></a>"
								+"<p><a href='articlserver.html?id="+Data.id+"'><i>"+Data.title+"</i></a>"
								   +"<span><b class='left'><i class='on'>"+Data.orderNum+"</i>/<i>"+Data.maxMannum+"</i></b>"
								         +"<b class='right dz'><i class='heart'>♡</i>"
								         +"<i class='dznum' data-haslike='"+Data.haslike+"' data-id='"+Data.id+"'>"+Data.likeNum+"</i></b>"
								   +"</span></p></li>")
							}else
							{
								$(".wrap ul").append(
								"<li><a href='articlserver.html?id="+Data.id+"'>"
								+"<img src='/readpicFile?filepath="+Data.articelPicPath+"'></a>"
								+"<p><a href='articlserver.html?id="+Data.id+"'><i>"+Data.title+"</i></a>"
								   +"<span><b class='left'><i class='on'>"+Data.orderNum+"</i>/<i>"+Data.maxMannum+"</i></b>"
								         +"<b class='right dz on'><i class='heart'>&#9829;</i>"
								         +"<i class='dznum' data-haslike='"+Data.haslike+"' data-id='"+Data.id+"'>"+Data.likeNum+"</i></b>"
								   +"</span></p></li>")
							}
							
						}
					}
					
					
					outUrl()
					
				})
			}
		 
		   return this;
	 	},
	 	
	 	//点赞
	 	dianzan:function(){
	 		
	 		$(".wrap ul").on("click",".dz",function(){
	 			var dznum=$(".dznum",this);
	 			var hx=$(".heart",this);
	 			var id=dznum.data("id");
	 			var haslike=dznum.data("haslike");
	 			
	 			var userid=getCookie("userid");
	 			if(userid == null || userid==''){
	 				//请先登录
	 				window.location.href="appointcenter.html?addressUrl=1";
	 			}else{
	 					//点赞\取消点赞
	 					$.ajax({
			 				type:"get",
			 				url:"/pointLike?articelId="+id+"&userId="+userid,
			 				dataType:"json",
			 				async:true,
			 				success:function(data){
			 					if(data.code == 'dissuccess'){
			 						$(dznum).removeClass("on");
			 						hx.html("♡");
			 						//取消点赞成功
									var num=dznum.html();
									num=parseInt(num)-1;
									dznum.html(num);
			 					}else{
			 						$(dznum).addClass("on");
									hx.html("&#9829;");
			 						//点赞成功
									var num=dznum.html();
									num=parseInt(num)+1;
									dznum.html(num);
			 					}
			 				}
			 			});
	 			}
	 			
	 			
	 		})

        
	 	}
	 	
	 	
	 }
	 
	 main.init();
})(Zepto)
