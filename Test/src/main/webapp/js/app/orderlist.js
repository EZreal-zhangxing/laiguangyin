(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		this.titleList();
	 		this.cancel();
	 		return this;
	 	},

		
	 	titleList:function(){
	 		var userid=getCookie("userid");
	 		//请求数据
	 		$.ajax({
			type:"get",
			url:"/getUserOrder/"+userid,
			async:true,
			dataType:"json",
			success:function(data){
				list(data);
			  }
			
			}); 
			
			//遍历数据动态拼接
			function list(data){
				
				$(data).each(function(index,element){
					
					var Data = element;
					console.log(Data)
					//判断文章如何显示    是否有无外链 
					function outUrl(){
						var status;
						if(Data.statue==0){
							status="已取消";
						}else if(Data.statue==1){
							status="<a class='cancel' data-id='"+Data.id+"' href='#'>取消预约</a>"
						}else if(Data.statue==2){
							status="已过期";
						}
						$(".wrap table").append("<tr><td>"+Data.ModelName+"</td>"
						+"<td class='detail'><span>"+Data.title+"</span>"
						+"<time>"+Data.timeArea+"</time></td>"
						+"<td>"+status+"</td>")
					}
					outUrl()
					
				})
			}
		 
		   return this;
	 	},
	 	
	 	//取消预约
	 	cancel:function(){
	 		var id;
	 		$(".wrap table").on("click",".cancel",function(){
	 			$(".mask,.input").css("display","block");
	 			id=$(this).data("id");
	 		})
	 		$(".input .sub2").on("click",function(){
	 			var userid=getCookie("userid");
	 			$.ajax({
	 				type:"get",
	 				url:"/cancleOrder/"+id+"/"+userid,
	 				async:true,
	 				success:function(data){
	 					alert(data.message);
	 					window.location.reload();
	 				}
	 			});
	 		})
		}
	 	
	 	
	 }
	 
	 main.init();
})(Zepto)
