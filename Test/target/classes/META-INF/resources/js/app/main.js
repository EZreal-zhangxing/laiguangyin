define(function(require){
	
	var $ = Zepto;
	
	//1.定义一个对象保存各类功能
	
	var obj = {
		
		route:function(){
			
			//1.获取url地址
			
			var url = location.href;
			
			//2.获取最后一个/的位置 
			
			var lastIndex = url.lastIndexOf('/')+1;
			
			//3.根据最后一个索引位置 截取之后所有的字符
			
			url = url.substr(lastIndex);
			
			//4.从0开始截取  结束位置为.的位置
			
			url = url.substr(0,url.indexOf('.'));
			
			console.log(url)
			
			//5.加载对应的模块
			
			requirejs([url]);
			
			return this
			
		},
		//适配方法
		fit:function(){
			
			//console.log($)
			//1. 获取像素比值
			
			var num = 1/window.devicePixelRatio;
			
			//2. 动态生成视口标签
			
			$('body').append('<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale='+num+', maximum-scale='+num+', minimum-scale='+num+'" />')
			
			
			//3. 获取页面的1/10 大小设置为html的字号
			
			var fontSize = document.documentElement.clientWidth/10;
			
			//4.设置html字号
			
			$('html').css('font-size',fontSize);
			
			return this
		},
		//初始化
		init:function(){
			
			this.fit()
			.route()
			.extendEvent()
			
			return this
		},
		
		extendEvent:function(){
			
			//通过$.fn绑定新的扩展方法
			
			$.fn.touch = function(callback){
				//this就是当前的jq对象
				
				var self = this;
				
				var attr_touchStartX = 'touchStartX';
				
				var attr_touchStartY = 'touchStartY';
				
				var attr_touchStartTime = 'touchStartTime';
				
				var callback = $.isFunction(callback) ? callback : false;
				
				//1.绑定触摸开始事件
				
				this.on('touchstart',function(e){
					
					e = e.changedTouches ? e.changedTouches[0] : e;
					
					//2. 点时获取 当前时间、位置
					
					var time = new Date().getTime();
					
					//3.获取位置
					
					var x = e.clientX;
					
					var y = e.clientY;
					
					//4.向当前元素添加属性 绑定属性、绑定数据   保存关键数据
					
					this[attr_touchStartX] = x;
					
					this[attr_touchStartY] = y;
					
					this[attr_touchStartTime] = time;
					
					
				}).on('touchend',function(event){
					//触摸结束
					
					var e = event.changedTouches ? event.changedTouches[0] : event;
					
					//计算触摸消耗的时间
					
					var useTime = new Date().getTime()- this[attr_touchStartTime];
					
					//计算触摸移动的X Y距离
					
					var moveX = e.clientX - this[attr_touchStartX];
					
					var moveY = e.clientY - this[attr_touchStartY];
					
					//限制消耗的时间范围在  100-200之间
					
					if(!moveX && !moveY && useTime>10 && useTime<200){
						
						if(callback)callback.call(this,event)
					
					}
					
					
				})
				
				return this
				
			}
			
			
			
			return this
		}
		
	}
	
	//2.执行初始化
	
	obj.init();
	
	

	
})