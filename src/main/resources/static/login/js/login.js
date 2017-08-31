// JavaScript Document
$(function(){
	$(".regt,.f_regt").on({
		click:function(){
			$("#loginPanel").fadeOut(1000);
			$("#findPwd").fadeOut(500);
			$("#registerPanel").slideDown(1000);
		}
	});	
	$(".lg,.f_lg").on({
		click:function(){
			$("#registerPanel").fadeOut(1000);
			$("#findPwd").fadeOut(500);
			$("#loginPanel").slideDown(1000);
		}
	});	
	
	$(".fpwd").on({
		click:function(){
			$("#loginPanel").fadeOut(1000);
			$("#registerPanel").fadeOut(1000);
			$("#findPwd").slideDown(1000);
		}
	});
	
	$(".loginPanel,.registerPanel,.findPwd").mouseenter(function(){
		$(this).animate({marginTop:"145px"},300);
		$(this).css("box-shadow", "0 10px 20px #D8D8D8");
	})
	
	$(".loginPanel,.registerPanel,.findPwd").mouseleave(function(){
		$(this).animate({marginTop:"164px"},300);
		$(this).css("box-shadow", "0 10px 20px #D8D8D8");
	})
	
	
	mouEnter(".regt", "注册", {marginLeft:"205px",width:"50px",borderRadius:"20px"});
	mouleave(".regt", {marginLeft:"220px",width:"18px",borderRadius:"50px"});
	
	mouEnter(".f_regt", "注册", {width:"50px",borderRadius:"20px"});
	mouleave(".f_regt", {width:"18px",borderRadius:"50px"});
	
	mouEnter(".fpwd", "找回密码？", {width:"80px",borderRadius:"20px"});
	mouleave(".fpwd", {width:"18px",borderRadius:"50px"});
	
	mouEnter(".lg", "登录", {marginLeft:"205px",width:"50px",borderRadius:"20px"});
	mouleave(".lg", {marginLeft:"220px",width:"18px",borderRadius:"50px"});
	
	mouEnter(".f_lg", "登录", {width:"50px",borderRadius:"20px"});
	mouleave(".f_lg", {width:"18px",borderRadius:"50px"});

    /**
	 * 注册表单验证
     */
	$("#reForm").validate({
		/*debug:false,*/
		focusInvalid : true,
		rules:{
			emailR:{
				required:true,
				email:true,
				remote : {
					url : "/regEmail",
					type : "POST",
					data : {
						email : function () {
							return $("#emailR").val();
                        }
					}

				}
			},
			usernameR:{
				required:true,
				minlength:2,
				maxlength:14
			},
			passwordR:{
				required:true,
				minlength:2,
				maxlength:16
			},
			confirmPassword:{
				equalTo:"#passwordR"
			}
		},
		messages:{
			emailR:{
				required:"邮箱必须填写",
				email:"邮箱格式不正确",
				remote : "邮箱已存在"
			},
			usernameR:{
				required:"账号必须填写",
				minlength:"账号最小为2位",
				maxlength:"账号最大为14位"
			},
			passwordR:{
				required:"密码必须填写",
				minlength:"密码最小为2位",
				maxlength:"密码最大为16位"
			},
			confirmPassword:{
				equalTo:"两次输入的密码不一致"
			}
			
		},
		groups:{
			register:"emailR usernameR passwordR confirmPassword"
		},
		errorPlacement:function(error, element){
			error.appendTo("#errorPr");
		}
	});
	
	$("#regt_btn").click(function(){
		if($('#reForm').valid())
			/*如果表单通过验证，调用方法执行注册*/
            register();
	});

    /**
	 * 登录表单验证
     */
	$("#lgForm").validate({
		rules:{
            account:{
				required:true,
				minlength:2,
				maxlength:14
			},
			password:{
				required:true,
				minlength:2,
				maxlength:16
			}
		},
		messages:{
            account:{
				required:"账号必须填写",
				minlength:"账号最小为2位",
				maxlength:"账号最大为14位"
			},
			password:{
				required:"必须填写密码",
				minlength:"密码最小为2位",
				maxlength:"密码最大为16位"
			}
		},
		groups:{
			login:"account password"
		},
		errorPlacement:function(error, element){
			error.appendTo("#errorP");
		}
	});
	
	$("#login_btn").click(function(){
		if($('#lgForm').valid())
            $("#lgForm").attr("action" ,"/login");
            $("#lgForm").submit();
            /*login();*/
	});
	
})

//鼠标经过事件
function mouEnter(cla, txt, json){
	$(cla).mouseenter(function(){
		$(this).animate(json,300)
		$(this).html(txt);
	})
}

//鼠标离开事件
function mouleave(cla, json){
	$(cla).mouseleave(function(){
		$(this).animate(json, 300);
		$(this).html("");
	})
}

