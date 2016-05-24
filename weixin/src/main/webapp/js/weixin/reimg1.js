
$(document).ready(function(){
	
	function changeData(data, tag){
	    for(var i = 0; i < data.length; i++){
	    	 //if(!data[i].hasAttribute("data-src") && data[i].hasAttribute("src") && (data[i].getAttribute("src")).lastIndexOf('http://', 0) === 0 && (data[i].getAttribute("src")).indexOf('licaime.com/') == -1){
	         //    data[i].setAttribute("data-src", data[i].getAttribute("src"));
	         //}
	         if(data[i].hasAttribute("data-src")){
	             datasrc = data[i].getAttribute("data-src");
	             alert(datasrc);
	             data[i].setAttribute("src", (tag == 'image' && datasrc.indexOf('http://read.html5.qq.com/image') == -1) ? "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=" + datasrc : datasrc);
	             data[i].removeAttribute("data-src");
	         }      
	        //重新加载图片
	        var html = showImg(data[i].getAttribute("src"));
	    	$(data[i]).after(html);
	    	$(data[i]).remove(); // 移除原来的img标签
	    }   
	}
	var imgs = document.getElementsByTagName('img');
	var videos = document.getElementsByClassName('video_iframe');
	changeData(imgs, 'image');
	changeData(videos, 'video');
	
	// show image
	function showImg(url) {
		 var frameid = 'frameimg' + Math.random();
	     window.img = '<img id="img" src=\''+url+'?'+Math.random()+'\' /><script>window.onload = function() { parent.document.getElementById(\''+frameid+'\').height = document.getElementById(\'img\').height+\'px\'; }<'+'/script>';
	// document.write(new_img);
	var new_img = '<iframe id="'+frameid+'" src="javascript:parent.img;" frameBorder="0" scrolling="no" width="100%"></iframe>';
	return new_img;
	}
	//替换图片
	//$(‘div.entry img’).each(function(i,d){
	//var html = showImg(this.src);
	//$(this).attr({‘src’:’/static/images/loading.gif’}); //显示loading动画
	//$(this).after(html);
	//$(this).remove(); // 移除原来的img标签
	//});
});