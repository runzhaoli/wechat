<%@ page language="java" import="kklazy.utils.*, kklazy.persistence.utils.*, kklazy.common.constants.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" buffer="512kb" %>
<% String path = request.getContextPath(); %>
<% String uri  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort(); %>
<% String url  = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI(); %>
<% String version = "?v=" + request.getSession().getId(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dic" uri="/dictionary-tag" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">

<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<link rel="icon" type="image/png" href="<%=path %>/images/favicon.png"  />

<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/jquery-2.1.0.min.js<%=version %>"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/script/jquery/jquery.form.js"></script>

<script type='text/javascript' charset="utf-8" src='<%=path %>/script/kklazy/kklazy-common-0.0.1.js'></script>

<script type='text/javascript' charset="utf-8" src='<%=path %>/script/weui/jquery-weui.min.js'></script>
<script type='text/javascript' charset="utf-8" src='<%=path %>/script/weui/city-picker.min.js'></script>
<script type='text/javascript' charset="utf-8" src='<%=path %>/script/weui/swiper.min.js'></script>

<link rel="stylesheet" type="text/css" href="<%=path %>/style/weui/weui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path %>/style/weui/jquery-weui.min.css" />

<style type="text/css">
.weui-header { padding: 50px 0; }
.weui-title { text-align: center; font-size: 34px; color: #3cc51f; font-weight: 400; margin: 0 15%; }
.weui-content-padded { padding: 15px; }
</style>
