<%@ page language="java" import="kklazy.utils.*, kklazy.persistence.utils.*, kklazy.common.constants.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" buffer="512kb" %>
<%@ page import="kklazy.security.support.systemconfig.utils.SystemConfigUtils"%>
<% String path = request.getContextPath(); %>
<% String uri  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(); %>
<% String url  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI(); %>
<% String version = "?v=" + StringUtils.random(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dic" uri="/dictionary-tag" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Pragma" content="no-cache">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<style type="text/css"> body { display: none; } </style>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/jquery-2.1.0.min.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/jquery.form.js<%=version %>"></script>
<script type='text/javascript' charset="utf-8" src='<%=path %>/script/kklazy/kklazy-common-0.0.1.js<%=version %>'></script>
<script type="text/javascript" >
$(document).ready(function() {
	kklazy._loading_resources('<%=path %>', '/template/base', '<%=version %>', function() {
		setTimeout(function() { $("body").show(); }, 10);
	});
});
</script>