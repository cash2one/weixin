<%-- 剥离出来的新的首页 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shopel" uri="/WEB-INF/pagetag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="image/x-icon" href="${pageContext.request.contextPath}/ico/maogou.ico" rel="shortcut icon">
		<title>${tagname}-最大的微信公众号推荐平台-聪悟网</title>
		<meta name="keywords" content="微信公众号 ,微信二维码,微信订阅号,微信公众账号,微信公众账号导航,公众账号导航,微信公众平台,聪悟网-${tagname}频道" />
		<meta name="description" content="聪悟网是专业的微信公众号推荐平台，在这里您可以找到自己喜欢的公众账号。我们收录了国内主流的微信公众号信息，以及公众号相关文章。聪悟网-www.taochongwu.cn" />
		<!-- 公共setting配置 -->
		<%@include file="/views/commons/config.jsp" %>
		<!--Javascript-->
		<%-- <jsp:include page="/views/commons/cssandscript.jsp"></jsp:include> --%>
		<link href="${domain_css}/css/weixin/inspector.css" rel="stylesheet" type="text/css">
		<link href="${domain_css}/css/weixin/main.css" rel="stylesheet" type="text/css">
		<link href="${domain_css}/css/weixin/base.css" rel="stylesheet" type="text/css">
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
.tagclass{
font-weight: bold;
}
	</style>
	</head>
<body>
	<jsp:include page="/views/commons/weixin_header_content.jsp"></jsp:include>
	
	<div>
	<div class=" pmsg_container main wrapper hidden "></div>
	<div class=" main wrapper">
	<div class="home_page contents content main_content" style="width: 1000px;">
	<div>
	<div>
	<div class=" focus_feed">
	<div class="e_col w1" style="margin-left: -60px">
		<div class="row section">
		<div>
			<div class="row #">
			<ul class="simple_profile_tabs">
			<div>
			 	
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-1--.htm"  style="color: #538dc2;font-weight: normal">
				热门
				</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-2--.htm" style="color: #538dc2;font-weight: normal">推荐</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-8--.htm" style="color: #538dc2;font-weight: normal">段子.笑话</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-3--.htm" style="color: #538dc2;font-weight: normal">健康.养生</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-4--.htm" style="color: #538dc2;font-weight: normal">情感.心理</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-5--.htm" style="color: #538dc2;font-weight: normal">电影.音乐</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-6--.htm" style="color: #538dc2;font-weight: normal">爱.生活</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-7--.htm" style="color: #538dc2;font-weight: normal">理财.财经</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-9--.htm" style="color: #538dc2;font-weight: normal">汽车.达人</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-10--.htm" style="color: #538dc2;font-weight: normal">IT.科技</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-14--.htm" style="color: #538dc2;font-weight: normal">旅行.摄影</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-15--.htm" style="color: #538dc2;font-weight: normal">职场.生活</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-16--.htm" style="color: #538dc2;font-weight: normal">美食.菜谱</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-17--.htm" style="color: #538dc2;font-weight: normal">历史.文学</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-18--.htm"  style="color: #538dc2;font-weight: normal">人文.教育</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-19--.htm" style="color: #538dc2;font-weight: normal">恋爱.星座</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-20--.htm" style="color: #538dc2;font-weight: normal">运动.体育</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-11--.htm" style="color: #538dc2;font-weight: normal">服装.搭配.化妆</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-12--.htm" style="color: #538dc2;font-weight: normal">辣妈帮.育儿经</a>
				</li>
				<li class="tab #">
				<a class="link_label" href="${_contextPath}/weixin/list-13--.htm" style="color: #538dc2;font-weight: normal">杂说</a>
				</li>
			</div>
			</ul>
			</div>
		</div>
		</div>
	</div>
	<div class="main e_col w4_5 main_col">
		<div>
		<div>
		<div tabindex="-1">
		<div class="feed_body" style="border-top: 1px solid #e0e0e0">
		
		<c:forEach var="weiXinArticle" items="${weiXinArticleList}" varStatus="index">
			<div class="pagedlist_item" tabindex="-1" >
			<div class="feed_item">
			<div class="e_col p1 w4_5">
			<div class="feed_item_photo">
			<div>
			<a href="${_contextPath}/weixin/one-${weiXinArticle.weixin_hao}--.htm" target="_blank">
			<img class="profile_photo_img" src="${_contextPath}/weixin/head/${weiXinArticle.hao_name_hash}.jpg" alt="${weiXinArticle.hao_name }" width="50"  height="50"/>
			</a>
			</div>
			</div>
			<strong class="feed_item_title">
			<span class="light_gray normal">
			<a class="topic_name" href="${_contextPath}/weixin/one-${weiXinArticle.weixin_hao}--.htm" target="_blank">
			<span class="name_text">
			<span>${weiXinArticle.hao_name}</span>
			</span>
			</a>
			.
			<span class="timestamp">
					${weiXinArticle.relativeTime}
			</span>
			.
			<span class="timestamp"><a style="color: #999" href="#;" onclick="window.open('http://service.weibo.com/share/share.php?url=${_contextPath}/weixin/one-${weiXinArticle.weixin_hao}--.htm&amp;title=【${weiXinArticle.title}(分享自@聪悟网 www.taochongwu.cn)。&amp;appkey=&amp;', '_blank', 'width=550,height=370'); " class="tb">分享</a></span>
			</span>
			</strong>
			<div class="home_feed_item_row">
			<div>
			<div class="feed_item_question">
				<h2>
				<span>
				<a class="question_link" href="${_contextPath}/weixin/detail-${weiXinArticle.titlehash}.htm" target="_blank"> 
				<div class="question_text_icons">
				<span></span>
				</div>
				${weiXinArticle.title}
				</a>
				</span>
				</h2>
				<span >
					<a class="question_link" href="${_contextPath}/weixin/detail-${weiXinArticle.titlehash}.htm" target="_blank"  style="margin-left: 15px;color:#666 !important;text-decoration: none !important;"> 
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
		</div>
		
		</div>
		</div>
		</div>
		<%-- <jsp:include page="/views/commons/weixin_page.jsp"></jsp:include> --%>
		
		<div class="pagination pagination-centered">
	 	<ul style="padding-top: 2px;">
			<li><a href="${_contextPath}/weixin/list-${tagid}--.htm">首页</a></li>
			<%-- <c:if test="${preId!=null }">
				<li><a href="${_contextPath}/weixin/list-${tagid}--1.htm?prevId=${preId}">上一页</a></li>
			</c:if>
			<li><a href="${_contextPath}/weixin/list-${tagid}--1.htm?nextId=${nextId}">下一页</a></li> --%>
			<c:if test="${preId!=null }">
				<li><a href="${_contextPath}/weixin/list-${tagid}--${preId}.htm">上一页</a></li>
			</c:if>
			<li><a href="${_contextPath}/weixin/list-${tagid}-${nextId}-.htm">下一页</a></li>
		</ul>
		</div>
	</div>
	<div class="side col w2_5 side_col">
		<div>
			<div>
				<div class="section">
					<h3>最新收录</h3>
					<c:forEach var="weixinhao" items="${newWeiXinHao}" varStatus="index">
						<div class="simple_tabs">
						<ul class="list_contents topic_trending_tabs">
						<div>
						<li class="tab linked_list_item">
						<strong>
						<span class="meta_item_pic">
						<div>
						<a href="${_contextPath}/weixin/one-${weixinhao.weixin_id}--.htm" title="${weixinhao.name}" target="_blank">
						<img class="profile_photo_img" src="${_contextPath}/weixin/head/${weixinhao.namehash}.jpg" alt="${weixinhao.name}"  height="25" width="25">
						</a>
						</div>
						</span>
						<a class="link" href="${_contextPath}/weixin/one-${weixinhao.weixin_id}--.htm" title="${weixinhao.name}" target="_blank">
						<span>${weixinhao.name}</span>
						</a>
						</strong>
						</li>
						</div>
						</ul>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div>
			<div>
				<div class="section">
					<h3>推荐账号</h3>
					<%-- <c:forEach var="weixinhao" items="${tuijianWeiXinHao}" varStatus="index">
						<div class="simple_tabs">
						<ul class="list_contents topic_trending_tabs">
						<div>
						<li class="tab linked_list_item">
						<strong>
						<span class="meta_item_pic">
						<div>
						<a href="${_contextPath}/weixin/one-${weixinhao.id}--.htm" title="${weixinhao.name}" target="_blank">
						<img class="profile_photo_img" src="${_contextPath}/weixin/head/${weixinhao.hao_name_hash}.jpg" alt="${weixinhao.name}"  height="25" width="25">
						</a>
						</div>
						</span>
						<a class="link" href="${_contextPath}/weixin/one-${weixinhao.id}--.htm" title="${weixinhao.name}" target="_blank">
						<span>${weixinhao.name}</span>
						</a>
						</strong>
						</li>
						</div>
						</ul>
						</div>
					</c:forEach> --%>
				</div>
			</div>
		</div>
		<%-- <div>
			<div>
				<div class="section"><h3>热门账号</h3>
				<c:forEach var="weixinhao" items="${reWeiXinHao}" varStatus="index">
					<div class="simple_tabs">
					<ul class="list_contents topic_trending_tabs">
					<div>
					<li class="tab linked_list_item">
					<strong>
					<span class="meta_item_pic">
					<div>
					<a href="${_contextPath}/weixin/one-${weixinhao.id}-1.htm" title="${weixinhao.name}" target="_blank">
					<img class="profile_photo_img" src="${_contextPath}/weixin/head/${weixinhao.touxiang}.jpg" alt="${weixinhao.name}"  height="25" width="25">
					</a>
					</div>
					</span>
					<a class="link" href="${_contextPath}/weixin/one-${weixinhao.id}-1.htm" title="${weixinhao.name}" target="_blank">
					<span>${weixinhao.name}</span>
					</a>
					</strong>
					</li>
					</div>
					</ul>
					</div>
				</c:forEach>
				</div>
			</div>
		</div> --%>
	
		<div>
			<div>
				<div class="section"><h3>赞助商</h3>
					<div class="simple_tabs">
			<!-- 百度广告 -->
		    <!-- <div>
			<script type="text/javascript">
			    /*300*250 创建于 2015-09-03*/
			    var cpro_id = "u2294637";
			</script>
			<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
		    </div>
		    
		    <div>
		    <script type="text/javascript">
			    var cpro_id = "u2294639";
			</script>
			<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
		    </div> -->
					</div>
				</div>
			</div>
		</div>
	
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
