/**!
 * Common JavaScript Library v0.0.1
 * 
 * Author: kkLazy
 * Date: 2013-12-15T10:21Z
 */
var kklazy = kklazy || {};

(function(kklazy) {

	kklazy._jump = function(url) {
		if (url != null) {
			location.href = url;
		}
	}

	kklazy._browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return {
				trident : u.indexOf('Trident') > -1,
				presto : u.indexOf('Presto') > -1,
				webKit : u.indexOf('AppleWebKit') > -1,
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,
				mobile : !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/),
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
				android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
				iPhone : u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1,
				iPad : u.indexOf('iPad') > -1,
				webApp : u.indexOf('Safari') == -1
			};
		}()
	}

	kklazy._iswechat = function() {
		var ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == "micromessenger") {
			return true;
		} else {
			return false;
		}
	}

	kklazy._loading_resources = function(path, url, version, success, error) {
		$.ajax({
			type : 'POST',
			async : false,
			url : path + url + version,
			dataType : "json",
			success : function(data) {
				if (data.result) {
					$.each(data.body['script'], function(index, entity) {
						$.getScript(path + this + version);
					});
					$.each(data.body['style'], function() {
						$.getStyle(path + this + version);
					});
					if (success != null) success.call(window, data);
				} else {
					if (error != null) error.call(window, data);
				}
			},
			error : function(data) {
				if (error != null) error.call(window, data);
			}
		});
	}

	kklazy._show_loading = function() {
		$("#loading").show();
	}

	kklazy._hide_loading = function() {
		$("#loading").hide();
	}
	
})(kklazy);

;(function($) {

	$.fn.warning = function (msg) {
		$(this).addClass("alert-warning");
		$(this).attr("data-content", msg);
	};
	
	$.extend({
		warning : function (entity, msg) {
			$(entity).addClass("alert-warning");
			$(entity).attr("data-content", msg);
		}
	});

	$.extend({
		isUndefined : function (entity) {
			return typeof(entity) == "undefined" || entity == null || entity == undefined;
		}
	});

	$.extend({
		verify : function (id, url) {
			$("#"+ id).attr("src", url + "?v=" + Math.random());
		}
	});
	
	$.extend({
		isEmpty : function (val) {
			return val == null || val.length < 1 || val == "";
		}
	});
	
	$.fn.isEmpty = function () {
		return $(this).val() == null || $(this).length < 1 || $(this).val() == "";
	};

	$.extend({
		isEmail : function (val) {
			var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			return reg.test(val);
		}
	});

	$.fn.isEmail = function () {
		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		return reg.test($(this).val());
	};

	$.extend({
		isPhone : function (val) {
			var reg = /^(1[0-9][0-9])\d{8}$/;
			return reg.test(val);
		}
	});

	$.fn.isPhone = function () {
		var reg = /^(1[0-9][0-9])\d{8}$/;
		return reg.test($(this).val());
	};
	
	$.extend({
		isReadonly : function (entity) {
			return !($(entity).attr("readonly") == undefined);
		}
	});

	$.fn.isReadonly = function () {
		return !($(this).attr("readonly") == undefined);
	};

	$.extend({
		isDisabled : function (entity) {
			return !($(entity).attr("disabled") == undefined);
		}
	});

	$.fn.isDisabled = function () {
		return !($(this).attr("disabled") == undefined);
	};

	$.extend({
		getStyle : function (url) {
			if(!$.isEmpty($.trim(url))) {
//				var link = document.createElement("link");
//				link.setAttribute("rel", "stylesheet");
//				link.setAttribute("type", "text/css");
//				link.setAttribute("href", $.trim(url));
//				document.getElementsByTagName("head")[0].appendChild(link);
				$("head").append("<link type='text/css' rel='stylesheet' href='" + $.trim(url) + "' />");
			}
		}
	});
	
	$.extend({
		inCollection : function (arr, element) {
			var _retval = false;
			$.each($.trim(arr).split(","), function(i, n) {
				if ($.trim(n) == $.trim(element))
					_retval = true;
			});
			return _retval;
		}
	});
	
	$.extend({
		format : function(str, step, splitor) {

			var prefix = "";
			var suffix = "";

			if (isNaN(str.substr(0, 1)) && str.substr(0, 1) == "-") {
				prefix = str.substr(0, 1);
				str = str.substr(1, str.length);
			}

			if (str.indexOf(".") > -1) {
				suffix = str.substr(str.indexOf("."), str.length);
				str = str.substr(0, str.indexOf("."));
			}
			
			str = str.toString();
			var len = str.length;

			if (len > step) {
				var l1 = len % step,
					l2 = parseInt(len / step),
					arr = [],
					first = str.substr(0, l1);
				if (first != '') {
					arr.push(first);
				}
				for ( var i = 0; i < l2; i++) {
					arr.push(str.substr(l1 + i * step, step));
				}
				str = arr.join(splitor);
			}
			return prefix + str + suffix;
		}
	});

	$.fn.slideLeftHide = function(speed, callback) {
		this.animate({
			width : "hide",
			paddingRight : "hide",
			paddingLeft : "hide",
			marginRight : "hide",
			marginLeft : "hide"
		}, speed, callback);
	};

	$.fn.slideLeftShow = function(speed, callback) {
		this.animate({
			width : "show",
			paddingLeft : "show",
			paddingRight : "show",
			marginLeft : "show",
			marginRight : "show"
		}, speed, callback);
	};  

})(jQuery);

//document.documentElement.onkeydown = function(evt) {
//	var b = !!evt, oEvent = evt || window.event;
//	if (oEvent.keyCode == 8) {
//		var node = b ? oEvent.target : oEvent.srcElement;
//		var reg = /^(input|textarea)$/i, regType = /^(text|password|number|email|datetime|datetime-local|date|month|time|week|url|search|tel|textarea)$/i;
//		if (!reg.test(node.nodeName) || !regType.test(node.type) || node.readOnly || node.disabled) {
//			if (b) {
//				oEvent.stopPropagation();
//			} else {
//				oEvent.cancelBubble = false;
//				oEvent.keyCode = 0;
//				oEvent.returnValue = false;
//			}
//			return false;
//		}
//	}
//}
