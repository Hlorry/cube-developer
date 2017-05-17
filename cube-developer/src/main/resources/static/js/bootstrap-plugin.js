/**
 * message confirm
 */
		$.messager = function() {
			var e = function(e) {
				var c = '<div class="modal fade message-modal-plugin" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">';
				c += '<div class="modal-dialog modal-sm" style="margin: 15% auto;">';
				c += '<div class="modal-content">';
				c += '<div class="modal-body">';
				c += e;
				c += '</div>';
				c += '<div class="modal-footer">';
				c += '<button type="button" class="btn btn-success" data-dismiss="modal">确认</button>';
				c += '</div></div></div></div>';
				var m = $(".message-modal-plugin");
				if (m.html() != undefined) {
					m.remove();
				}
				$("body").append(c);
				$(".message-modal-plugin").modal('show')
			}, m = function(e, f) {
				var c = '<div class="modal fade message-modal-plugin" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">';
				c += '<div class="modal-dialog modal-sm" style="margin: 15% auto;">';
				c += '<div class="modal-content">';
				c += '<div class="modal-body">';
				c += e;
				c += '</div>';
				c += '<div class="modal-footer">';
				c += '<button type="button" class="btn btn-success confirm" >确认</button>';
				c += '</div></div></div></div>';
				var m = $(".message-modal-plugin");
				if (m.html() != undefined) {
					m.remove();
				}
				$("body").append(c);
				m = $(".message-modal-plugin");
				$(".message-modal-plugin").modal('show')
				m.find(".confirm").click(function() {
					m.modal('hide');
					f();
				});
			};
			return {
				alert : e,
				confirm : m
			};
		}(jQuery),
		$.confirm = function() {
			var e = function(e, f) {
				var c = '<div class="modal fade confirm-modal-plugin" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">';
				c += '<div class="modal-dialog modal-sm" style="margin: 15% auto;">';
				c += '<div class="modal-content">';
				c += '<div class="modal-body">';
				c += e;
				c += '</div>';
				c += '<div class="modal-footer">';
				c += '<button type="button" class="btn btn-success confirm" >确认</button>';
				c += '<button type="button" class="btn btn-success" data-dismiss="modal">取消</button>';
				c += '</div></div></div></div>';
				var m = $("body .confirm-modal-plugin");
				if (m.html() != undefined) {
					m.remove();
				}
				$("body").append(c);
				m = $("body .confirm-modal-plugin");
				m.modal('show');
				m.find(".confirm").click(function() {
					f();
					m.modal('hide');
				});
			};
			return e;
		}(jQuery),
		Date.prototype.format = function(format) {
			var date = {
				"M+": this.getMonth() + 1,
				"d+": this.getDate(),
				"h+": this.getHours(),
				"m+": this.getMinutes(),
				"s+": this.getSeconds(),
				"q+": Math.floor((this.getMonth() + 3) / 3),
				"S+": this.getMilliseconds()
			};
			if (/(y+)/i.test(format)) {
				format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
			}
			for (var k in date) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
				}
			}
			return format;
		}