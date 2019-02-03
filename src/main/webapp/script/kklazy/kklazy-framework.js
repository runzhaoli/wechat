/**
 * kklazy framework
 */
var framework = framework || {};

(function(framework) {

	framework._jump = function(iframe, url) {
		if (iframe != null && url != null) {
			frames[iframe].location.href = url;
		}
	}
	
	framework._navigation_off = function() {
		$(".left-icon-btn.on").fadeIn(200);
		$(".left-icon-btn.off").animate({ left : "-242px" }, 200);
		$(".bottom-icon-btn").animate({ left : "-242px" }, 200);
		$(".navigation").animate({ left : "-240px" }, 200);
		$(".layer-mask").fadeOut(200);
	}

	framework._navigation_on = function() {
		$(".left-icon-btn.on").fadeOut(200);
		$(".left-icon-btn.off").animate({ left : "200px" }, 200);
		$(".bottom-icon-btn").animate({ left : 0 }, 200);
		$(".navigation").animate({ left : 0 }, 200);
		$(".layer-mask").fadeIn(200);
	}

	framework._load_center = function() {
		setTimeout(function() {
			$(".center").find("iframe").css('min-height', ($(window).height() - 3 - 80) + 'px');
		}, 50);
	}
	
})(framework);

var _is_navigation_off = true;

$(document).ready(function() {

	$(".left-icon-btn.off").click(function() {
		framework._navigation_off();
		return false;
	});

	$(".left-icon-btn.on").click(function() {
		framework._navigation_on();
		return false;
	});
	
	$(".layer-mask").click(function() {
		framework._navigation_off();
		return false;
	});
	
	var $nav = $(".navigation");
	
	var $left  = $nav.find(".head").find(".left");
	var $right = $nav.find(".head").find(".right");
	
	$left.find("button").click(function() {
		var $div = $right.find("." + $(this).attr("id"));
		$div.siblings("div").fadeOut(100);

		setTimeout(function() { $div.fadeIn(200); }, 150);

		$left.find("button").removeClass("selected");
		$(this).addClass("selected");
	});

	$nav.find("li").find(".title").click(function() {
		$nav.find("li").find(".list-item").slideUp(150);
		$nav.find("li").find(".tag").fadeOut(50);
		$nav.find("li").removeClass("selected");
		
		var $div = $(this).siblings(".list-item");

		if ($div.is(":hidden")) {
			$(this).parent().addClass("selected");
			setTimeout(function() {
				$div.slideDown(200);
				$div.find(".tag").fadeIn(100);
			}, 100);
		}
		
	});

	$nav.find("li").find("a").click(function() {
		kklazy._show_loading();
		
		var $div = $(this).siblings(".list-item");
		if (!$(this).parent().hasClass("selected")) {
			$nav.find("li").find(".item").removeClass("selected");
			$(this).parent().addClass("selected");
		}
		setTimeout(function() {
			if (_is_navigation_off) {
				framework._navigation_off();
			}
		}, 200);
	});
	
	$(window).resize(function() {
		framework._load_center();
	});
	
	$(".center").find("iframe").load(function() {
		framework._load_center();
		kklazy._hide_loading();
	});
	
	$("#logout").click(function() {
		$('#exit').modal('show');
	});
	
	$("#lock").click(function() {
		if ($(this).hasClass("on")) {
			$(this).removeClass("on").addClass("off");
			$(".center").animate({ left : "0px" }, 200);
			$(".layer-mask").fadeIn(200);
			$(".left-icon-btn.off").fadeIn(200);
			_is_navigation_off = true;
			$(this).find("i").removeClass("icon-unlock").addClass("icon-lock");
			$(this).removeAttr("style");
		} else {
			$(this).removeClass("off").addClass("on");
			$(".center").animate({ left : "242px" }, 200);
			$(".layer-mask").fadeOut(20);
			$(".left-icon-btn.off").fadeOut(20);
			_is_navigation_off = false;
			$(this).find("i").removeClass("icon-lock").addClass("icon-unlock");
			$(this).css({
				"color" : "#fff",
				"background-color" : "#999"
			});
		}
	});
	
});
