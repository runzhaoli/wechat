<%@ page language="java" import="kklazy.utils.*, kklazy.persistence.utils.*, kklazy.common.constants.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" buffer="512kb" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dic" uri="/dictionary-tag" %>
<% String path = request.getContextPath(); %>
<% String version = "?v=" + StringUtils.random(); %>

<div class="pagebar">
	<input type="hidden" id="index" value="${page.number }" />
	<input type="hidden" id="size"  value="${page.size }" />
<c:if test="${page.firstPage }">
	<span>
		<a href="javascript:void(0);" class="pageinfo disabled" >first</a>
	</span>
	<span>
		<a href="javascript:void(0);" class="pageinfo disabled" >previous</a>
	</span>
</c:if>
<c:if test="${!page.firstPage }">
	<span>
		<a href="javascript:void(0);" class="pageinfo" onclick="javascript:redirect('first');">first</a>
	</span>
	<span>
		<a href="javascript:void(0);" class="pageinfo" onclick="javascript:redirect('previous');">previous</a>
	</span>
</c:if>
<c:if test="${!page.lastPage }">
	<span>
		<a href="javascript:void(0);" class="pageinfo" onclick="javascript:redirect('next');">next</a>
	</span>
	<span>
		<a href="javascript:void(0);" class="pageinfo" onclick="javascript:redirect('end');">end</a>
	</span>
</c:if>
<c:if test="${page.lastPage }">
	<span>
		<a href="javascript:void(0);" class="pageinfo disabled" >next</a>
	</span>
	<span>
		<a href="javascript:void(0);" class="pageinfo disabled" >end</a>
	</span>
</c:if>
	<span>
		<font style="padding-left: 10px">page：</font> 
		<label><input type="text" id="pageturn" value="${page.number + 1 }" validation="required integer" data-placement='top' maxlength="4" /></label>
		<font style="padding-right: 10px;"> / ${page.totalPages } </font>
		<a href="javascript:void(0);" title="Go" class="pageinfo" onclick="javascript:turn('${page.totalPages }');" ><i class="icon-check"></i></a>
	</span>
	<span>
		<font style="padding-left: 10px">rows：</font> 
		<dic:select clazz="pagesize" showcode="false" value="${page.size }" id="_pagesize" group="<%=ContextConstants.GROUP.PAGE_SIZE %>"></dic:select>
	   	<font style="padding-right: 10px;"> / ${page.totalElements } </font> 
	</span>
</div>
<%-- 	<span> Rows: ${page.numberOfElements } </span> --%>
<script type="text/javascript">
$(document).ready(function() {
	$("#_pagesize").change(function() {
		var _size = $(this).children('option:selected').val();
		$('#size').val(_size);
		redirect('first');
	});
});
function turn(total) {
	if (!validation()) {
		setTimeout(function () {
			$(":input").removeAttr("readonly");
			$(":button,:submit").attr("disabled", null);
			$('[data-toggle=popover]').popover('show');
		}, 500);
		
		return ;
	}
	if (total != null && total != '') {
		if (parseInt($('#pageturn').val()) <= 0) { $('#pageturn').val('1'); }
		if (parseInt($('#pageturn').val()) > total) { $('#pageturn').val(total); }
	} else { $('#pageturn').val('1'); }
	redirect('pageturn');
}
function redirect(type) {
	
	var _html = "";
	var pageindex;
	var pagesize = parseInt($('#size').val());

	if (type == 'first') { pageindex = 0; }
	if (type == 'previous') { pageindex = parseInt($('#index').val()) - 1; }
	if (type == 'next') { pageindex = parseInt($('#index').val()) + 1; }
	if (type == 'end') { pageindex = ${page.totalPages - 1 }; }
	if (type == 'pageturn') { pageindex = parseInt($('#pageturn').val()) - 1; }
	
// 	_html += "<input type='hidden' name='pageindex' id='pageindex' value='" + pageindex + "' />";
// 	_html += "<input type='hidden' name='pagesize'  id='pagesize'  value='" + pagesize + "' />";

	$('#pageindex').val(pageindex < 0 ? 0 : pageindex);
	$('#pagesize').val(pagesize);
	$('#${param.form }').attr('action', '<%=path %>${param.action }<%=version %>');
	$('#${param.form }').submit();
	
	parent._show_loading();
}
</script>