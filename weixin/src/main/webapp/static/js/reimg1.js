
//jquery version

$(function(){
		//替换图片
		$("img").each(function(i){
			  //循环每个img
			//循环每个img
			  var datasrc=$(this).attr("data-src");
			  var src=$(this).attr("src");
			  //防止误换
			  if(typeof(datasrc)=="undefined" && typeof(datasrc)!="undefined" && src.indexOf('taochongwu.cn') == -1){
				  $(this).attr("data-src",src);
			  }
			  //换图片
			  datasrc= $(this).attr("data-src");
			  if(typeof(datasrc)=="undefined"){
				  return;
			  }
			  datasrc = datasrc.replace("https://v.qq.com/", 'http://v.qq.com/');
			  $(this).attr("src",( datasrc.indexOf('http://read.html5.qq.com/image') == -1) ? "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=" + datasrc : datasrc);
			  $(this).removeAttr("data-src");
		});
		
		//替视频换图片
		$(".video_iframe").each(function(i){
			  //循环每个img
			  var datasrc=$(this).attr("data-src");
			  var src=$(this).attr("src");
			  //防止误换
			  if(typeof(datasrc)=="undefined" && typeof(datasrc)!="undefined" && src.indexOf('taochongwu.cn') == -1){
				  $(this).attr("data-src",src);
			  }
			  //换图片
			  datasrc=$(this).attr("data-src");
			  if(typeof(datasrc)=="undefined"){
				  return;
			  }
			  datasrc = datasrc.replace("https://v.qq.com/", 'http://v.qq.com/');
			  $(this).attr("src",datasrc);
			  $(this).removeAttr("data-src");
		});
	
	//加载对象
	//var imgs = $("img");
	//var videos = $(".video_iframe");
	//changeData(imgs, 'image');
	//changeData(videos, 'video');
	
});