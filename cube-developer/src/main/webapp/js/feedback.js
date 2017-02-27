
//反馈

$(function(){
	var form=$("#feedback_form");
	//form.eq(0).resetForm();
	form.bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            title: {
                validators: {
                    notEmpty: {
                        message: '标题不能为空'
                    },stringLength: {
                        min: 1,
                        max: 30,
                        message: '标题不能超过30个字符'
                    }
                }
            },
            content: {
                validators: {
                    notEmpty: {
                        message: '反馈容不能为空'
                    },stringLength: {
                        min: 1,
                        max: 240,
                        message: '内容不能超过240个字符'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
    	e.preventDefault();
    	$.ajax({
    		url:'/feedback/add',
    		type:'post',
    		dataType:'json',
    		data:{
    			title:form.find('[name=title]').val(),
    			content:form.find('[name=content]').val(),
    			phone:form.find('[name=phone]').val()
    		},
    		success:function(e){
    			if(e.httpCode==200){
    				window.location.href="/route/feedback_success";
    			}
    		},error:function(){
    			alert("服务器出错了，请稍后再试");
    		}
    	})
    });
})