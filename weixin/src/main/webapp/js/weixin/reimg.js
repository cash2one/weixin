function changeData(data, tag){
    for(var i = 0; i < data.length; i++){
        if(!data[i].hasAttribute("data-src") && data[i].hasAttribute("src")  && (data[i].getAttribute("src")).indexOf('licaime.com/') == -1){
//            data[i].setAttribute("data-src", data[i].getAttribute("src"));
        	if(data[i].getAttribute("src").indexOf('wx_fmt=png') >0 || data[i].getAttribute("src").indexOf('wx_fmt=jpeg') >0 || data[i].getAttribute("src").indexOf('wx_fmt=gif') >0){
            	data[i].setAttribute("src","/weixin/lian.htm?"+data[i].getAttribute("src"));
            }
        }
        if(data[i].hasAttribute("data-src")){
            datasrc = data[i].getAttribute("data-src");
            data[i].setAttribute("src", (tag == 'image' && datasrc.indexOf('http://read.html5.qq.com/image') == -1) ? "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=" + datasrc : datasrc);
            if(data[i].getAttribute("src").indexOf('wx_fmt=png') >0 || data[i].getAttribute("src").indexOf('wx_fmt=jpeg') >0 || data[i].getAttribute("src").indexOf('wx_fmt=gif') >0){
            	data[i].setAttribute("src","/weixin/lian.htm?"+data[i].getAttribute("src"));
            }
            data[i].removeAttribute("data-src");
            //重新加载图片
        }   
    }   
}
var imgs = document.getElementsByTagName('img');
var videos = document.getElementsByClassName('video_iframe');
changeData(imgs, 'image');
changeData(videos, 'video');