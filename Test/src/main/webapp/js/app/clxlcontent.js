(function($){
	 
	 var main = {
	 	
	 	init:function(){
	 		
	 		//解析url
	 		var url=window.location.href;
	 		
	 		var id= getQueryString("id",url);
	 		
	 		this.article(id);
	 		
	 		return this;
	 	},
		
	 	article:function(id){
	 		
	 		//console.log("asdfjh")
	 		
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
					console.log("modelId:"+id);
				});
				var time2 = new Date(Data.date).Format("yyyy-MM-dd");
				$(".wrap").append("<h1>"+Data.title+"</h1>"
					+"<time>"+time2+"</time><br/><br/>"
					+"<div style='margin:auto 30px;'><p>"+Data.content+"</p></div>")
			}
		 
		   return this;
	 	}
	 	
	 	
	 	
	 	
	 }
	 
	 main.init();
})(Zepto)
