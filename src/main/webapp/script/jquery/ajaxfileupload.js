/**
 * AjaxFileUpload
 */
jQuery.extend({
	createUploadIframe: function(d, b) {
		var a = "jUploadFrame" + d;
		if (window.ActiveXObject) {
			if (navigator.userAgent.indexOf("MSIE 9.0") > 0 || navigator.userAgent.indexOf("MSIE 10.0") > 0) {
				var c = document.createElement("iframe");
				c.id = a;
				c.name = a
			} else if (navigator.userAgent.indexOf("MSIE 6.0") > 0 || navigator.userAgent.indexOf("MSIE 7.0") > 0 || navigator.userAgent.indexOf("MSIE 8.0") > 0) {
				var c = document.createElement('<iframe id="' + a + '" name="' + a + '" />');
				if (typeof b == "boolean") {
					c.src = "javascript:false"
				} else {
					if (typeof b == "string") {
						c.src = b
					}
				}
			}
		} else {
			var c = document.createElement("iframe");
			c.id = a;
			c.name = a
		}
		c.style.position = "absolute";
		c.style.top = "-1000px";
		c.style.left = "-1000px";
		document.body.appendChild(c);
		return c
	},
	createUploadForm: function(g, b) {
		var e = "jUploadForm" + g;
		var a = "jUploadFile" + g;
		var d = jQuery('<form  action="" method="POST" name="' + e + '" id="' + e + '" enctype="multipart/form-data"></form>');
		var c = jQuery("#" + b);
		var f = jQuery(c).clone();
		jQuery(c).attr("id", a);
		jQuery(c).before(f);
		jQuery(c).appendTo(d);
		jQuery(d).css("position", "absolute");
		jQuery(d).css("top", "-1200px");
		jQuery(d).css("left", "-1200px");
		jQuery(d).appendTo("body");
		return d
	},
	ajaxFileUpload: function(k) {
		k = jQuery.extend({}, jQuery.ajaxSettings, k);
		var a = k.fileElementId;
		var b = jQuery.createUploadForm(a, k.fileElementId);
		var i = jQuery.createUploadIframe(a, k.secureuri);
		var h = "jUploadFrame" + a;
		var j = "jUploadForm" + a;
		if (k.global && !jQuery.active++) {
			jQuery.event.trigger("ajaxStart")
		}
		var c = false;
		var f = {};
		if (k.global) {
			jQuery.event.trigger("ajaxSend", [f, k])
		}
		var d = function(l) {
				var p = document.getElementById(h);
				try {
					if (p.contentWindow) {
						f.responseText = p.contentWindow.document.body ? p.contentWindow.document.body.innerHTML : null;
						f.responseXML = p.contentWindow.document.XMLDocument ? p.contentWindow.document.XMLDocument : p.contentWindow.document
					} else {
						if (p.contentDocument) {
							f.responseText = p.contentDocument.document.body ? p.contentDocument.document.body.innerHTML : null;
							f.responseXML = p.contentDocument.document.XMLDocument ? p.contentDocument.document.XMLDocument : p.contentDocument.document
						}
					}
				} catch (o) {
					jQuery.handleError(k, f, null, o)
				}
				if (f || l == "timeout") {
					c = true;
					var m;
					try {
						m = l != "timeout" ? "success" : "error";
						if (m != "error") {
							var n = jQuery.uploadHttpData(f, k.dataType);
							if (k.success) {
								k.success(n, m)
							}
							if (k.global) {
								jQuery.event.trigger("ajaxSuccess", [f, k])
							}
						} else {
							jQuery.handleError(k, f, m)
						}
					} catch (o) {
						m = "error";
						jQuery.handleError(k, f, m, o)
					}
					if (k.global) {
						jQuery.event.trigger("ajaxComplete", [f, k])
					}
					if (k.global && !--jQuery.active) {
						jQuery.event.trigger("ajaxStop")
					}
					if (k.complete) {
						k.complete(f, m)
					}
					jQuery(p).unbind();
					setTimeout(function() {
						try {
							jQuery(p).remove();
							jQuery(b).remove()
						} catch (q) {
							jQuery.handleError(k, f, null, q)
						}
					}, 100);
					f = null
				}
			};
		if (k.timeout > 0) {
			setTimeout(function() {
				if (!c) {
					d("timeout")
				}
			}, k.timeout)
		}
		try {
			var b = jQuery("#" + j);
			jQuery(b).attr("action", k.url);
			jQuery(b).attr("method", "POST");
			jQuery(b).attr("target", h);
			if (b.encoding) {
				b.encoding = "multipart/form-data"
			} else {
				b.enctype = "multipart/form-data"
			}
			jQuery(b).submit()
		} catch (g) {
			jQuery.handleError(k, f, null, g)
		}
		if (window.attachEvent) {
			document.getElementById(h).attachEvent("onload", d)
		} else {
			document.getElementById(h).addEventListener("load", d, false)
		}
		return {
			abort: function() {}
		}
	},
	uploadHttpData: function(r, type) {
		var data = !type;
		data = type == "xml" || data ? r.responseXML : r.responseText;
		if (type == "script") {
			jQuery.globalEval(data)
		}
		if (type == "json") {
			var da1 = data.split("{");
			var da2 = da1[1].split("}");
			var data2 = "{" + da2[0] + "}";
			eval("data = " + data2)
		}
		if (type == "text/html") {
			jQuery("<div>").html(data).evalScripts()
		}
		return data
	},
	handleError: function( s, xhr, status, e ){
		if ( s.error ) {
			s.error.call( s.context || s, xhr, status, e );
		}
		// Fire the global callback
		if ( s.global ) {
			(s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );
		}
	}
});
