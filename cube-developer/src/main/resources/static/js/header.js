/* 点击顶部跳转 */
$('#mf-nav .mf-header-nav-text a').click(function() {
	$(this).addClass('a-current').siblings().removeClass('a-current');
	var texta = $(this).text();
	if(texta == '首页') {
		window.location.href = 'http://cube.getcube.cn/index.html#header';
	} else if(texta == '价格') {
		window.location.href = 'http://cube.getcube.cn/index.html#cubeenginc';
	} else if(texta == '下载') {
		window.location.href = 'http://cube.getcube.cn/index.html#download';
	} else if(texta == '开发手册') {
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				form = layui.form();
			layer.open({
				content: '功能开发中！！'
			});
		});

	} else if(texta == '开发者社区') {
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				form = layui.form();
			layer.open({
				content: '功能开发中！！'
			});
		});
	} else if(texta == '关于我们') {
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				form = layui.form();
			layer.open({
				content: '功能开发中！！'
			});
		});
	} else {
		window.location.href = 'http://cube.getcube.cn/login.html';
	}
});

/* 点击login跳转登录 */
$('.mf-header-nav-img').click(function() {
	window.location.href = 'http://cube.getcube.cn/index.html';
});
/* 点击聊天图标 */
$('.ico-1').click(function() {
	$('.alert_bg').show();
	$('.customer-iframe').show();
});
$('.alert_bg').click(function() {
	$('.customer-iframe').hide();
	$('.alert_bg').hide();
});

function bgimg() {
	var bgimg = sessionStorage.bgimg;
	$('.click-img').attr('src', bgimg);
}

/* 底部链接 */
//$('#mf-footer .mf-click-link span').click(function(){
//	var val = $(this).text();
//	console.log('val',val);
//	if(val == '公司简介'){
//		alert('功能开发中');
//	}
//	else if(val == '团队介绍'){
//		alert('功能开发中');
//	}
//	else if(val == '联系方式'){
//		window.location.href = '#';
//	}
//	else if(val == '加入我们'){
//		window.location.href = 'service.html';
//	}
//	else if(val == '服务支持'){
//		window.location.href = 'service.html';
//	}
//	else if(val == '服务条款'){
//		window.location.href = 'service.html';
//	}
//	else if(val == 'SDK下载'){
//		alert('功能开发中');
//	}
//	else if(val == '知识库'){
//		alert('功能开发中');
//	}
//	else if(val == '开发者手册'){
//		alert('功能开发中');
//	}
//});

/* 移动端适配 */

/* 判断是否为移动设备 */
function IsPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = ["Android", "iPhone",
		"SymbianOS", "Windows Phone",
		"iPad", "iPod"
	];
	var flag = true;
	for(var v = 0; v < Agents.length; v++) {
		if(userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	return flag;
};
$(document).ready(function() {

	var token1 = sessionStorage.token;
	var port = commonProt + '/user/query_token';
	var data1 = {
		"token": token1
	}
	var succeed = '登录成功！！';
	var failure = '您尚未登录！！';
	if(token1 != undefined) {
		require(['js/library.js'], function(library) {
			library.aj(port, data1, succeed, failure, function(datum) {
				if(datum.code == 200) {
					$('.mf-last-img').show();
					$('#mf-nav .mf-header-nav .nav-right').hide();
					$('#mf-nav .mf-header-nav .mf-last-img').show();
					bgimg();
					console.log(succeed);
				}
				if(datum.code == 800) {
					$('#mf-nav .mf-header-nav .nav-right').show();
					$('#mf-nav .mf-header-nav .mf-last-img').hide();
					console.log(failure);
				}
			});
		});
	} else {
		$('.nav-right').show();
		$('#mf-nav .mf-header-nav .mf-last-img').hide();
		$('#mf-nav .mf-header-nav .mf-login-register').show();
		console.log(failure);
	}

	/* 对不同设备进行操作 */
	var isPC = IsPC();
	if(isPC) {
		console.log('这是pc端');
	} else {
		console.log('这是移动端');
		$('.mf-phone-img').show();

		$('.mf-phone-img').click(function() {
			//			$('.mf-phone-img').show();
			$('.mf-header-nav-text>div:last-child').slideToggle();
		});
		$('.phone-click').click(function() {
			//			$('.mf-phone-img').show(800);
			$('.mf-header-nav-text>div:last-child').slideUp();

		});

		$(document).click(function(e) {
			if($('.mf-header-nav-text>div:last-child').css('display') == 'block') {
				//				$('.mf-phone-img').show(800);
				$('.mf-header-nav-text>div:last-child').slideUp();
				$('.mf-nav-last').hide()
			}

		});
		$('#mf-nav').on('click', function(e) {
			e.stopPropagation();
		});


	}

});

/* 判断是否已经登录注册 */