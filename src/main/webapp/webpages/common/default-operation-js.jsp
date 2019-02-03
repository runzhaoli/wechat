<%@ page language="java" import="kklazy.utils.*, kklazy.persistence.utils.*, kklazy.common.constants.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" buffer="512kb" %>
<% String path = request.getContextPath(), version = "?v=" + StringUtils.random(); %>
<script type="text/javascript">
$(document).ready(function() {

	$("#search").click(function() {
		$('#query').modal('show');
	});

	$("#query #commit").click(function() {
		$("#searchForm").attr("action", "search<%=version %>");
		$("#searchForm").submit();
		parent.kklazy._show_loading();
	});

	$("#query #cancel").click(function() {
		$("#searchForm input[type='text']").each(function() {
			$(this).val("");
		});
		$("#searchForm input[type='tel']").each(function() {
			$(this).val("");
		});
	});

	$("#create").click(function() {
		window.location.href = "create<%=version %>";
		parent.kklazy._show_loading();
	});

	$("#exceltemplate").click(function() {
		$("#searchForm").attr("action", "<%=path %>${namespace }exceltemplate<%=version %>");
		$("#searchForm").submit();
	});

	$("#exceldownload").click(function() {
		$("#searchForm").attr("action", "<%=path %>${namespace }exceldownload<%=version %>");
		$("#searchForm").submit();
	});

	$("#excelupload").click(function() {
		$('#upload').modal('show');
	});

	$(".right-icon-btn #commit").click(function() {
		modifyformcommit();
	});

	$("#historyback").click(function() {
		parent.kklazy._show_loading();
		history.back();
	});

});

function modifyformcommit() {
	if (!validation()) {
		popover();
		return ;
	}

	$("#modifyform").ajaxSubmit({
		type : 'POST',
		async : true,
		url : '<%=path %>${param.commiturl }<%=version %>',
		dataType : 'json',
		success : function(data) {
			if (data['result']) {
				showsuccessmessage("操作成功!", function (e) {
					window.location.href = '<%=path %>${param.searchurl }<%=version %>';
					parent.kklazy._show_loading();
				});
			} else {
				showerrormessage("操作失败, " + data['message'], function (e) {
					popover();
				});
			}
		},
		error : function(data, status, e) {
			alert(e);
			popover();
		}
	});
}
function showsuccessmessage(message, callback) {
	$("#alertsuccess").find("#message").text(message);
	$("#alertsuccess").modal('show');
	$("#alertsuccess").on('hidden.bs.modal', callback);
	setTimeout(function() {
		$("#alertsuccess").modal('hide');
	}, 1500);
}
function showerrormessage(message, callback) {
	$("#alerterror").find("#message").text(message);
	$("#alerterror").modal('show');
	$("#alerterror").on('hidden.bs.modal', callback);
	setTimeout(function() {
		$("#alerterror").modal('hide');
	}, 2000);
}
function popover() {
	setTimeout(function () {
		$(":input").removeAttr("readonly");
		$(":button,:submit").attr("disabled", null);
		$('[data-toggle=popover]').popover('show');
	}, 500);
}
function jumpentity(jumpurl) {
	window.location.href = jumpurl;
	parent.kklazy._show_loading();
}
function optionentity(optionurl) {
	$(":button,:submit").attr("disabled", "disabled");
	$.ajax({
		type : "POST",
		async : true,
		url : optionurl,
		dataType : "json",
		success : function(data) {
			showsuccessmessage("操作成功!", function (e) {
				$("#query #commit").click();
			});
		},
		error : function(data, status, e) {
			$(":button,:submit").attr("disabled", null);
		}
	});
}
</script>