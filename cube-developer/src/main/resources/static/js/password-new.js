$('#mf-main .mf-click-accomplish').click(function() {
	var actmd5 = $('#actmd5').val();
	var val = $('#mf-click-new').val();
	var val1 = $('#mf-click-new1').val();
	var reg = /^[\w]{6,12}$/;
	if(!val.match(reg)) {
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				form = layui.form();
			layer.open({
				content: '密码为6 ~ 12 位数字和字母组成！请重新输入！！'
			});
		});
		$('#mf-click-new').focus();
	} else if(!val1.match(reg)) {
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				form = layui.form();
			layer.open({
				content: '密码为6 ~ 12 位数字和字母组成！请重新输入！！'
			});
		});
		$('#mf-click-new1').focus();
	} else if(val == val1 && val != '' && val1 != '') {
		var port =  '/auth/password/mailupdate';
		var pwd = val;
		var data1 = {
			"newPwd":pwd,"actmd5":actmd5
		}
		$.ajax({
			type: "post",
			url: port,
			async: false,
			data: data1,
			success: function(data) {
				if(data.code==200){
					layui.use(['layer', 'form'], function() {
						var layer = layui.layer,
							form = layui.form();
						layer.open({
							content: '密码修改成功！'
						});
					});
				}else if(data.code==10012){
					layui.use(['layer', 'form'], function() {
						var layer = layui.layer,
							form = layui.form();
						layer.open({
							content: '链接已失效！！'
						});
					});
				}else if(data.code==100){
					layui.use(['layer', 'form'], function() {
						var layer = layui.layer,
							form = layui.form();
						layer.open({
							content: '未知错误！！'
						});
					});
				}
			},
			error: function() {
				console.log('修改失败！！');
			}
		});

	} else {
		layui.use(['layer', 'form'], function() {
			var layer = layui.layer,
				form = layui.form();
			layer.open({
				content: '两次输入的密码不一致！！'
			});
		});
	}
});
