
var wechatmenu = wechatmenu || {};

(function(wechatmenu) {
	
	wechatmenu.go = function(url) {
		location.href = url;
	}
	
	wechatmenu.open = function() {
		$(".layer-mask").show();
		$(".bottom-menu-content").animate({height:'400px',width:'240px'}, 100, "swing", function() {
			$(".context").show();
		});
	}
	
	wechatmenu.close = function() {
		$(".layer-mask").hide();
		$(".context").hide();
		$(".bottom-menu-content").animate({height:'0px',width:'0px'}, 100);
	}

})(wechatmenu);

$(document).ready(function() {
	
	$(".bottom-menu").click(function() {
		wechatmenu.open();
		return false;
	});
	
	$(".bottom-menu-close").click(function() {
		wechatmenu.close();
		return false;
	});

	$(".layer-mask").click(function() {
		wechatmenu.close();
		return false;
	});

});