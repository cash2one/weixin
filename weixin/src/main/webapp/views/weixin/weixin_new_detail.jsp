<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="referrer" content="never">
<link href="/static/css/inspector.css" rel="stylesheet" type="text/css">
<link href="/static/css/main.css" rel="stylesheet" type="text/css">
<link href="/static/css/base.css" rel="stylesheet" type="text/css">
<!--[if IE]>
<link href="/static/css/ie.css" rel="stylesheet" type="text/css">
<![endif]-->
<!-- Le fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/static/img/ico/apple-touch-icon-ipad3.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="/static/img/ico/apple-touch-icon-iphone4.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="/static/img/ico/apple-touch-icon-ipad.png">
<link rel="apple-touch-icon-precomposed" href="/static/img/ico/apple-touch-icon-iphone.png">
<link type="image/x-icon" href="${pageContext.request.contextPath}/ico/maogou.ico" rel="shortcut icon">

<title>${weiXinArticle.title }-- ${weiXinArticle.hao_name } -- 聪悟网</title>
<meta name="keywords" content="${weiXinArticle.title },${weiXinArticle.hao_name },聪悟网" />
<meta name="description" content="${weiXinArticle.article_pre }" />

<link rel="stylesheet" type="text/css" href="/static/css/article_improve.css"/>

<!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="/static/css/article_pc.css"/> <![endif]-->
<link media="screen and (min-width:1023px)" rel="stylesheet" type="text/css" href="/static/css/article_pc.css"/>
<style>ol,ul{list-style-position:inside;}</style>


</head>
<body>
<div>
<div>
<div>
<div class="header wrapper" id="layout_header" style="background: white;
min-height: 70px;">
<div class="contents">
<div class="row relative">
<div class="logo " style="padding: 5px 10px 5px;font-size: 15px;left: -60px;margin-left: -70px;">
	<a style="text-decoration: none;font-weight: bold;color: #333;height: 68px;" href="/index.htm" alt="聪悟网">
		<img src='${pageContext.request.contextPath}/img/weixin/chongwu02.png' width="160px" height="65px" alt="聪悟网">
	</a>
</div>
<!-- <div>
<div class=" search full_search instant_results">
<div class="search_contents navigator">
<form class="search_form" action="/search" method="get" name="search_form">
<div class="search_input">
<div class="input_wrapper ">
<input class="question_box light" name="q" type="text"  maxlength="250" placeholder="搜索账号" tabindex="1">
</div>
</div>
</form>
</div>
</div>
</div> -->
<!-- <div style="float: right;"><a href='http://werank.cn' target="_blank" style="color: #666;padding: 0 8px;">排行榜</a></div>
<div style="float: right;"><a href='/app' style="color: #666;padding: 0 8px;">客户端</a></div> -->
</div>
</div>
</div>
</div>
</div>
</div>

<!-- <script type="text/javascript">
var cpro_id = "u2358614";
</script>
<script src="http://cpro.baidustatic.com/cpro/ui/i.js" type="text/javascript"></script> -->

<div class="home_page contents content" style="width: 1000px;">
<div class="row">
<div class="main e_col main_col" style="width: 625px;">
<html><head></head><body>

${weiXinArticle.content}

        
        
        
    

    
  
  

    


 
</body></html>
<!-- <script type="text/javascript">
var cpro_id="u2312677";
(window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id]={at:"3",rsi0:"600",rsi1:"250",pat:"6",tn:"baiduCustNativeAD",rss1:"#FFFFFF",conBW:"0",adp:"1",ptt:"0",titFF:"%E5%BE%AE%E8%BD%AF%E9%9B%85%E9%BB%91",titFS:"14",rss2:"#000000",titSU:"0",ptbg:"90",piw:"0",pih:"0",ptp:"0"}
</script>
<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script> -->
<div style="border-top:1px dotted #C8D8F2; padding:5px 5px 0px 5px;border-bottom:1px dotted #C8D8F2; height:30px;margin:15px 0px 0px 0px;border-top:1px dotted #C8D8F2;">
<div class="bdsharebuttonbox" data-tag="share_bottom" style="font-size:13px; line-height:16px; float:right;"><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信">微信</a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间">QQ空间</a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博">新浪微博</a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博">腾讯微博</a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网">人人</a><a href="#" class="bds_twi" data-cmd="twi" title="分享到Twitter">Twitter</a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网">豆瓣</a><a href="#" class="bds_more" data-cmd="more"></a></div>
<div style="float:right;line-height:28px; color:#6c6c6c; font-size:12px;padding-right:15px;">觉得不错，分享给更多人看到</div>
</div>

<div id="RecentPosts">
<div><strong>${weiXinArticle.hao_name}</strong> 最新文章:</div>
<div class="feed_body">

<!-- 8-10篇相关文章 -->
<c:forEach var="weiXinArticle" items="${weiXinArticleList}" varStatus="index">
	
	<div class="pagedlist_item" tabindex="-1">
	<div class="feed_item" style="border-bottom: none;">
	<div class="e_col p1 w5_5" style="padding: 6px 0;">
	<div class="row">
	<div>
	<div class="feed_item_question">
	<h2>
	<span>
	<span class="timestamp" style="color: #999">${weiXinArticle.relativeTime}</span>&nbsp;&nbsp;&nbsp;
	<a class="question_link" href="/weixin/detail-${weiXinArticle.titlehash}.htm">${weiXinArticle.title }</a>
	</span>
	</h2>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</c:forEach>

</div>
<div>查看更多<span class="chevron">&nbsp;&nbsp;››&nbsp;&nbsp;</span><h2 id="page_more_name"><a href="/weixin/one-${weiXinArticle.hao_id}-1.htm" style="color: #19558d;">${weiXinArticle.hao_name}</a></h2></div>
</div>

<!-- <script type="text/javascript">
var cpro_id="u2326060";
(window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id]={at:"3",rsi0:"600",rsi1:"250",pat:"6",tn:"baiduCustNativeAD",rss1:"#FFFFFF",conBW:"0",adp:"1",ptt:"0",titFF:"%E5%BE%AE%E8%BD%AF%E9%9B%85%E9%BB%91",titFS:"14",rss2:"#000000",titSU:"0",ptbg:"90",piw:"0",pih:"0",ptp:"0"}
</script>
<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script> -->
</div>
<div class="side e_col side_col w2" style="margin-top: 10px;">
<!-- <div class="row section section_top" style="margin-bottom: 5px">
<a style="padding: 6px" class="download-ios" href="https://itunes.apple.com/cn/app/chuan-song-men-wei-xin-gong/id969898148?ls=1&amp;mt=8" target="_blank">传送门 for iOS 下载</a>
</div> -->
<div class="section">
<div>
<h3>关于 <a href="/weixin/one-${weiXinArticle.weixin_hao}-1.htm">${weiXinArticle.hao_name}</a></h3>
<div class="inline_editor_content">
<span class="inline_editor_value">
<div class="inline">
<span>${weiXinArticle.hao_desc}</span>
</div>
</span>
</div>
</div>
</div>
<!-- <div class="section">
<div>
<h3>文艺星球 微信二维码</h3>
<div class="inline_editor_content">
<span class="inline_editor_value">
<div class="inline">
<img width="210px" height="210px" src="http://q.chuansong.me/iartplanet.jpg" alt="文艺星球 微信二维码">
</div>
</span>
</div>
</div>
</div> -->
<div class="section share_question ">
<h3>分享这篇文章</h3>
<div class="row share_icons_row">
<div class="bdsharebuttonbox" data-tag="share_right"><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣网"></a><a href="#" class="bds_more" data-cmd="more"></a></div>
</div>
</div>

<!-- <div class="section">
<div>
<div class="inline_editor_content">
<span class="inline_editor_value">
<div class="inline">
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<ins class="adsbygoogle" style="display:inline-block;width:210px;height:210px"
data-ad-client="ca-pub-0996811467255783" data-ad-slot="2990020277"></ins>
<script>(adsbygoogle = window.adsbygoogle || []).push({});</script>
</div>
</span>
</div>
</div>
</div> -->
</div>
</div>
</div>


<div>
<div class=" footer wrapper">
<div class="contents">
<div class="footer_nav row">
<div class="e_col footer_links w8">
<ul class="nav_list">
<li class="about"><a href="/about">关于</a></li>
<!-- <li><span class="bullet"> • </span></li> -->
<!-- <li class="about"><a href="/submit/account">提交</a></li>
<li><span class="bullet"> • </span></li>
<li class="about"><a href="/app">App</a></li>
<li class="about" style="float: right"><a href="https://www.upyun.com/index.html" target="_blank">又拍云</a></li> -->
<!-- <li style="float: right"><span class="bullet"> • </span></li> -->
<li class="about" style="float: right"><a href="http://www.taochongwu.cn/weixn/weixin.htm" target="_blank">热榜</a></li>

</ul>
</div>
</div>
</div>
</div>
</div>
</body>
<!-- <script type="text/javascript">
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-40292976-1']);
_gaq.push(['_setDomainName', '.chuansong.me']);
_gaq.push(['_setAllowLinker', true]);
_gaq.push(['_trackPageview']);
(function() {
var ga = document.createElement('script');
ga.type = 'text/javascript';
ga.async = true;
ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
var s = document.getElementsByTagName('script')[0];
s.parentNode.insertBefore(ga, s);
})();
</script> -->
<script language="javascript" src="http://upcdn.b0.upaiyun.com/libs/jquery/jquery-1.4.2.min.js"></script>
<script language="javascript" src="/static/js/common.js"></script>
<script>window._bd_share_config={"common":{"bdSnsKey":{"tsina":""},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"share":[{"tag":"share_bottom","bdSize":16},{"tag":"share_right","bdSize":24,"bdStyle":"2"}]};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
<!-- <script>
var _hmt = _hmt || [];
(function() {
var hm = document.createElement("script");
hm.src = "//hm.baidu.com/hm.js?9d416cb1bdca85038c8810fc14eeee57";
var s = document.getElementsByTagName("script")[0];
s.parentNode.insertBefore(hm, s);
})();
</script> -->


<script language="javascript" src="/static/js/reimg1.js"></script>


</html>
