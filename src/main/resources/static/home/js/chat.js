var username = '${sessionScope.username}';//获取登录的username
var ws = null;//定义WebSocket对象
//通过登录的用户名定义要开启的WebSocket通道的地址
var target = "ws://localhost:8080/chatSocket?name=" + username;

$(function () {
    //进入聊天页面，就打开socket通信管道
    if ("WebSocket" in window) {
        ws = new WebSocket(target);
    } else if ("MozWebSocket" in window) {
        ws = new MozWebSocket(target);
    } else {
        alert("WebSocket is not supported by this browser");
        return;
    }

    //显示当前用户
    var us = "当前用户：" + username;
    $("#currentUser").append(us);

    //ws接收消息
    ws.onmessage = function (event) {
        //把传过来的消息赋值给一个变量
        eval("var msg=" + event.data + ";");
        //提取json数据中的欢迎信息
        if (undefined != msg.welcome)
        //显示json数据中的欢迎信息
            $("#content").append(msg.welcome + "<br>");
        //提取json数据中的当前在线的所有用户信息
        if (undefined != msg.usernames) {
            //清空用户列表中的内容
            $("#userList").html("");
            //遍历获得的用户列表
            $(msg.usernames).each(function () {
                //定义checkbox
                var ch = "<input type='checkbox' value='" + this + "'/>";
                //显示每个当前在线的用户
                $("#userList").append(ch + "<a href='singleChat.jsp?toUser="+ this + "'" +"target='_blank'>" + this + "</a>" + "<br/>");
            })
        }

        //提取json数据中的聊天内容信息
        if (undefined != msg.content) {
            //显示聊天内容信息
            $("#content").append(msg.content + "<br>");
        }
    };
})