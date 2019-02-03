<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${fn:containsIgnoreCase(resource.url, 'search') }">
	<button class="icon-btn" id="search">
		<i class="icon-check"></i>
		<div>查询</div>
	</button>
	<button class="icon-btn" id="create">
		<i class="icon-plus"></i>
		<div>新增</div>
	</button>
</c:if>
<c:if test="${fn:containsIgnoreCase(resource.url, 'detail') }">
	<button class="icon-btn" id="historyback">
		<i class="icon-undo"></i>
		<div>返回</div>
	</button>
</c:if>
<c:if test="${!fn:containsIgnoreCase(resource.url, 'search') and !fn:containsIgnoreCase(resource.url, 'detail') }">
	<button class="icon-btn" id="commit">
		<i class="icon-check"></i>
		<div>保存</div>
	</button>
	<button class="icon-btn" id="historyback">
		<i class="icon-undo"></i>
		<div>取消</div>
	</button>
</c:if>