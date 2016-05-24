<%-- 剥离出来的新的首页 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shopel" uri="/WEB-INF/pagetag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="image/x-icon" href="${pageContext.request.contextPath}/ico/maogou.ico" rel="shortcut icon">
		<title>${weiXinHao.hao_name}--${weiXinHao.hao_name}微信公众号 -- 微精选</title>
<meta name="keywords" content="${weiXinHao.hao_name},${weiXinHao.hao_name}-微信公众号 ,${weiXinHao.hao_name}微信,${weiXinHao.hao_name}微信二维码,${weiXinHao.hao_name}微信订阅号" />
<meta name="description" content="${weiXinHao.description }-${weiXinHao.hao_name}" />
		<!-- 公共setting配置 -->
		<%@include file="/views/commons/config.jsp" %>
		<!--Javascript-->
		<jsp:include page="/views/commons/cssandscript.jsp"></jsp:include>
		<link href="${domain_css}/css/weixin/inspector.css" rel="stylesheet" type="text/css">
		<link href="${domain_css}/css/weixin/main.css" rel="stylesheet" type="text/css">
		<link href="${domain_css}/css/weixin/base.css" rel="stylesheet" type="text/css">
	</head>
	<style>
	.pagination-centered {
text-align: center;
}
.pagination {
margin: 20px 0;
}
.pagination ul {
border-radius: 4px;
box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
display: inline-block;
margin-bottom: 0;
margin-left: 0;
}
.pagination ul > li {
display: inline;
}
li {
list-style-type: none;
}
.pagination ul > li:first-child > a, .pagination ul > li:first-child > span {
border-bottom-left-radius: 4px;
border-left-width: 1px;
border-top-left-radius: 4px;
}
.pagination ul > li > a, .pagination ul > li > span {
-moz-border-bottom-colors: none;
-moz-border-left-colors: none;
-moz-border-right-colors: none;
-moz-border-top-colors: none;
background-color: #FFFFFF;
border-color: #DDDDDD;
border-image: none;
border-style: solid;
border-width: 1px 1px 1px 0;
float: left;
line-height: 20px;
padding: 4px 8px;
text-decoration: none;
}
.pagination ul > .active > a, .pagination ul > .active > span {
color: #999999;
cursor: default;
}
	</style>
<body>
<jsp:include page="/views/commons/weixin_header_content.jsp"></jsp:include>

<div id="ld_fadXXq_105">
<div class=" pmsg_container main wrapper hidden " id="__w2_PMOmhhZ_pmsg_container"></div>
<div class=" main wrapper">
<div class="topic_page contents content main_content">
<div id="__w2_P3AAXTa_content">
<div id="ld_YKjjsP_251">
<div class="topic_page_redesign">
<div id="ld_E6G600_278"></div>
<div id="ld_E6G600_279"></div>
<div class="row">
<div class="e_col side_col w3_5">
<span id="ld_Ak3SCj_2243">
<div class="share fr">
<a class="weixin" href="javascript:;" data-id="weixin" title="分享到微信"></a>
<a class="tsina" href="javascript:;" data-id="tsina" title="分享到新浪微博"></a>
<a class="qzone" href="javascript:;" data-id="qzone" title="分享到QQ空间"></a>
<a class="tqq" href="javascript:;" data-id="tqq" title="分享到腾讯微博"></a>
<a class="douban" href="javascript:;" data-id="douban" title="分享到豆瓣"></a>
</div>
</span>
</div>
<div class="e_col w4_5 topic_header">
<div id="ld_E6G600_283">
<div id="ld_E6G600_291">
<div class="profile_photo" id="__w2_m4ynERI_photo">
<img class="profile_photo_img" src="${_contextPath}/weixin/head/${weiXinHao.hao_name_hash}.jpg" width="50" alt="${weiXinHao.hao_name}" height="50">
</div>
</div>
</div>
<div id="ld_E6G600_284">
<div class="row topic_name_editor">
<div class=" inline_editor_content" id="__w2_kgil14i_inline_editor_content">
<span class="inline_editor_value"><h1 class="inline"><span
id="ld_XT398O_291"><a href="${_contextPath}/weixin/one-${weiXinHao.weixin_hao}-1.htm">${weiXinHao.hao_name }</a></span></h1></span></div>
</div>
</div>
</div>
</div>
<div class="row">
<div class="# horizontal_tabs">
<!-- <ul class="list_contents" id="__w2_wvJevug_tabs">
<li class="tab linked_list_item first" style="margin-right: 8px;">

<span class="link selected">最新</span>

</li>
<li class="tab linked_list_item" style="margin-right: 8px;">

<a class="link" href="/account/one_hanhan/old">最旧</a>

</li>
<li class="tab linked_list_item">

<a class="link" href="/account/one_hanhan/hot">热门</a>

</li>
</ul> -->
</div>
</div>
<div class="row">
<div class="main_col col w4_5">
<div id="ld_E6G600_277">
<div id="__w2_ZjF4TCx_actionable">
<div id="ld_Jzw32O_287"></div>
<div id="ld_Jzw32O_288">
<div class="row"></div>
</div>
<div id="__w2_ZjF4TCx_topic_feed">

<div tabindex="-1" id="ld_Jzw32O_289">
<div class="feed_body">
<c:if test="${weiXinArticleList ==null}">
暂无更多数据
</c:if>
<c:if test="${weiXinArticleList!=null }">
<c:forEach var="weiXinArticle" items="${weiXinArticleList}" varStatus="index">
	<div class="pagedlist_item" tabindex="-1" id="ld_B2wwd3_299">
	<div class="feed_item" id="__w2_jLtaXf1_item">
	<div class="mute_item_overlay"
	id="__w2_fLCgg4u_mute_item_overlay"></div>
	<div class="e_col p1 w4_5" id="__w2_jLtaXf1_feed_item">
	<div class="row">
	<div id="ld_ixjTtR_307">
	<div class="feed_item_question">
	<h2>
	<span id="ld_ixjTtR_312">
	<a class="question_link" href="/weixin/detail-${weiXinArticle.titlehash}.htm" target="_blank"> 
	${weiXinArticle.title}
	</a>
	&nbsp;&nbsp;&nbsp;
	<span class="timestamp" style="color: #999"><fmt:formatDate value="${weiXinArticle.createTime}" pattern="yyyy年MM月dd日 "/></span>
	&nbsp;&nbsp;&nbsp;
	<span style="float: right;font-weight: normal"><a style="color: #999" href="#;" onclick="window.open('http://service.weibo.com/share/share.php?url=http://www.51maogou.com/weixin/one-${weixinhao.id}-1.htm&amp;title=【${weiXinArticle.title}(分享自@猫狗杂谭  www.51maogou.com)。&amp;appkey=4149632123&amp;', '_blank', 'width=550,height=370'); " class="tb">分享</a></span>
	</span>
	</h2>
	
	<span >
		<a class="question_link" href="/weixin/detail-${weiXinArticle.titlehash}.htm" target="_blank"  style="color:#666 !important;text-decoration: none !important;margin-left: 15px;"> 
		${weiXinArticle.article_pre}
		</a>
	</span>
	<c:if test="${not empty weiXinArticle.read_times }">
	<div style="color: #bbb;">
		阅读：${weiXinArticle.read_times}次
	</div>
	</c:if>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</c:forEach>
</c:if>

</div>
<jsp:include page="/views/commons/weixin_page.jsp"></jsp:include>
</div>

</div>
</div>
</div>
</div>
<div class="e_col side_col w2_5">
<div id="ld_E6G600_285">
<div class="row section section_top">
<h3 style="margin-bottom: -4px; overflow: hidden;">
<div id="ld_E6G600_305">
微信ID:${weiXinHao.weixin_hao}
</div>
</h3>
</div>
</div>
<div class="section">
<div id="ld_E6G600_286"><h3>介绍信息
</h3>
<div class=" inline_editor_content" id="__w2_hcSeTk5_inline_editor_content">
<span class="inline_editor_value"><div class="inline" id="__w2_qcGcPvc_text_snip">
<span id="__w2_qcGcPvc_text_snip_content">

${weiXinHao.hao_desc}


</span>
</div>
</span>
</div>
</div>
</div>
<%-- <div id="ld_E6G600_287">
<div class="row section related_topics"><h3>二维码</h3>
<div>
<!-- <img width="260px" height="260px" src="http://q.chuansong.me/one_hanhan.jpg" alt="一个·韩寒"> -->
<img width="260px" height="260px" src="${_contextPath}/weixin/ma/${weiXinHao.ma}.jpg" alt="${weiXinHao.name }">
</dif>
</div>
</div>
</div> --%>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

	<!-- footer -->
	<jsp:include page="/views/commons/weixin_footer.jsp"></jsp:include> 
</div>
</body>
<script type="text/javascript" src="${domain_js}/js/weixin/common.js?${version_js}"></script>
</html>
