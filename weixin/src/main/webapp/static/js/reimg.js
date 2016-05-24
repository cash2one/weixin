function changeData(data, tag){
    for(var i = 0; i < data.length; i++){
        if(!data[i].hasAttribute("data-src") && data[i].hasAttribute("src") && (data[i].getAttribute("src")).lastIndexOf('http://', 0) === 0 && (data[i].getAttribute("src")).indexOf('chuansong.me/') == -1){
            data[i].setAttribute("data-src", data[i].getAttribute("src"));
        }
        if(data[i].hasAttribute("data-src")){
            datasrc = data[i].getAttribute("data-src");
            datasrc = datasrc.replace("https://v.qq.com/", 'http://v.qq.com/')
            data[i].setAttribute("src", (tag == 'image' && datasrc.indexOf('http://read.html5.qq.com/image') == -1) ? "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=" + datasrc : datasrc);
            data[i].removeAttribute("data-src");
        }   
    }   
}
var imgs = document.getElementsByTagName('img');
var videos = document.getElementsByClassName('video_iframe');
changeData(imgs, 'image');
changeData(videos, 'video');