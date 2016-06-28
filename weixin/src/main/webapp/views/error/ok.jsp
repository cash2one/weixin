<%-- 剥离出来的新的首页 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shopel" uri="/WEB-INF/pagetag.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="image/x-icon" href="${pageContext.request.contextPath}/ico/licaime.ico" rel="shortcut icon">
		<!-- 公共setting配置 -->
		<%@include file="/views/commons/config.jsp" %>
		<link rel="stylesheet" href="${domain_css}/css/licai/style.css?${version_css}" />
	</head>
	<body>
		<div style="width: 1100px; margin: 50px auto 0px;padding-bottom: 50px;">
		        <div class="section">
		        <p class="helpp1" style=" font-size: 15px;font-weight: bold;">微信精选-聪悟网（taochongwu.cn）</p>
		        <br></br>
		        <p  class="helpp">执行成功！</p>
		        </div>
		</div>
	</body>
	<!--Javascript-->
	<jsp:include page="/views/commons/cssandscript.jsp"></jsp:include>
</html>