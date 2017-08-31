/*ajax异步注册，注册成功返回账号*/
function register() {
    $.ajax({
        type : "POST",
        url : "/register",
        dataType : "text",
        scriptCharset : "utf-8",
        data : {
            email : $("#emailR").val(),
            name : $("#usernameR").val(),
            password : $("#passwordR").val()
        },
        success : function (data) {
            $("#errorPr").html(data);
        },
        error : function () {
            $("#errorPr").html("服务器故障，请重试！");
        }
    });
}

function login() {
    $.ajax({
        type : "POST",
        url : "/loginAjax",
        dataType : "json",
        scriptCharset : "utf-8",
        data : {
            account : $("#account").val(),
            password : $("#password").val()
        },
        success : function (data) {

        },
        error : function () {
            $("#errorPr").html("服务器故障，请重试！");
        }
    });
}
