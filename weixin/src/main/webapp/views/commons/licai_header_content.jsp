<%-- 剥离出来的头部 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.zu-top-add-question {
float: left;
margin: 0 0 0 6px;
background: #0d77e4;
background-color: #0e78e7;
background-image: -moz-linear-gradient(top,#0e7bef,#0d73da);
background-image: -ms-linear-gradient(top,#0e7bef,#0d73da);
background-image: -webkit-gradient(linear,0 0,0 100%,from(#0e7bef),to(#0d73da));
background-image: -webkit-linear-gradient(top,#0e7bef,#0d73da);
background-image: -o-linear-gradient(top,#0e7bef,#0d73da);
background-image: linear-gradient(top,#0e7bef,#0d73da);
background-repeat: repeat-x;
filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#0e7bef', endColorstr='#0d73da', GradientType=0);
color: #FFF;
font-size: 14px;
width: 66px;
height: 32px;
line-height: 30px;
text-align: center;
text-shadow: 0 1px 1px rgba(0,0,0,.12);
cursor: pointer;
border: 1px solid #045BB2;
border-radius: 4px;
box-shadow: 0 1px 2px rgba(0,0,0,.05),inset 0 1px 1px rgba(255,255,255,.15);
-webkit-transition: all .2s ease-in-out;
transition: all .2s ease-in-out;
outline: 0;

margin-top: 28px;
margin-left: 310px;
}
</style>
<script type="text/javascript">
$(function () {
	$("#zu-top-add-question").click(function(){
		window.location = "/toask.htm";
	});
});
</script>
<div class="preheader">
  		  <div class="pre-h-content">
            <div class="pre-h-left">
                 欢迎邮件： <span>licaime@163.com</span> 服务时间：9:00 - 21:00 
                  &nbsp;&nbsp;关注微博<a href="http://t.qq.com/licaime_admin" class="qq" target="_blank"></a>
		          <a href="http://weibo.com/u/5236761311" class="weibo" target="_blank"></a>
                 <!-- <a class="weixin"></a> -->
            </div>
            <div class="pre-h-right">
              <ul>
              <c:if test="${username==null || username=='guest'}">
              	   <li><a href="${_contextPath}/member/toLogin.htm">登录</a></li>
                	<li><a href="${_contextPath}/member/toRegister.htm">免费注册</a></li>
              </c:if>
              <c:if test="${username!=null && username!='guest'}">
              	   <li><span style=" display: inline-block;font-size: 13px;line-height: 50px;padding: 1px 10px;font-weight: 600;">欢迎${username}</span></li>
              	   <li><a href="${_contextPath}/member/loginout.htm">退出</a></li>
              </c:if>
              </ul>
            </div>
        </div>
</div>
<div class="prenav">
	  <div class="prenav-content">
	   <a class="prenav-img" href="${_contextPath}/index.htm">
	    <img src="/img/licai/licaime1.png">
	   </a>
	   <div class="prenav-wenzi">
	    <ul>
	     <li class="${menu eq 'list'?'cur':''}"><a href="${_contextPath}/index.htm">银行理财</a></li>
	     <li class="${menu eq 'jigou'?'cur':''}"><a href="${_contextPath}/netlc/company.htm">理财机构</a></li>
	     <li class="${menu eq 'wenda'?'cur':''}"><a href="${_contextPath}/wenda.htm">理财经验</a></li>
	     <%-- <li class="${menu eq 'tongji'?'cur':''}"><a href="${_contextPath}/tongji.htm">数据统计</a></li> --%>
	     <li  class="${menu eq 'user'?'cur':''}"><a href="${_contextPath}/member/userinfo.htm">我的账户</a></li>
	     <button class="zu-top-add-question" id="zu-top-add-question">提问</button>
	    </ul>
	   </div>
	  </div>
</div>