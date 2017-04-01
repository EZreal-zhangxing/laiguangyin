(function($){
	
	
	var main = {
		
	    init:function(){
		
			this.banner();
			return this;
	   },
	   
	   banner:function(){
			
			//1.发请求获取banner
			$.ajax({
				type:"get",
				url:"/getAllbanner",
				dataType:'json',
				success:function(data){
					console.log(data)
					//遍历img标签 修改src
					$('.banner .list img').each(function(i){
						
						if(!data[i]) return;
						//更新自己的src
						$(this).attr('src',"/readpicFile?filepath="+data[i].bannerPath)
					})
			
				}
			});
			
			
			return this
		}

	}
	
	main.init();
	
})(Zepto)



