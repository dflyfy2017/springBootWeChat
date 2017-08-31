// JavaScript Document
$(function () {

    /*getData();*/

    /*四个导航的切换*/
    tabToggle("#conversation", ".conversation");
    tabToggle("#ContactP", ".ContactP");
    tabToggle("#more", ".more");
    tabToggle("#setting", ".setting");

    /*好友列表的打开与关闭*/
    var list = $(".friend_list #list");
    var ah4 = list.find("h4");
    var uls = list.find("ul");

    for (var i = 0; i < ah4.length; i++) {
        ah4[i].index = i;

        ah4[i].onmouseover = function () {
            this.style.backgroundColor = "#EBEBEC";
        }
        ah4[i].onmouseout = function () {
            this.style.backgroundColor = "#FFFFFF";
        }

        ah4[i].onclick = function () {
            if (this.className == '' || this.className == 'hover') {
                uls[this.index].style.display = 'block';
                this.className = '';
                this.className = 'active';
            } else {
                uls[this.index].style.display = 'none';
                this.className = '';
            }
        }
    }

    /*会话，联系人，群组的操作*/
    var alAli = $(".friend_list .list ul li,.group_list ul li"),
        chatLi = $(".conversation ul li"),
        friLi = $(".friend_list .list ul li"),
        groLi = $(".group_list ul li");
    /*会话左键单击*/
    for (var i = 0; i < chatLi.length; i++) {
        chatLi[i].onclick = function (e) {
            var txt = $(this).find("i").html();
            $(".chatPanel .head span").html(txt);
            $(".chatPanel").css("display", "block");
        };
    }
    /*好友和群组左键单击，打开聊天框的同时在会话列表增加一条*/
    for (var i = 0; i < alAli.length; i++) {
        alAli[i].onclick = function () {
            //获得名字
            var name = $(this).find("i").html();
            //比对名字
            console.info(findNameIsExist(name));
            //获得结果
            var re = findNameIsExist(name);
            if (re == -1) {//不存在
                //在会话列表的开头插入一条记录
                var nLi = $("<li><div class='icon'><img src='images/头像1.png'></div>" +
                    "<div class='cont'><i>" + name + "</i><br>我是" + name + "</div></li>");
                $(".conversation ul").prepend(nLi);
            }
            $(".chatPanel .head span").html(name);
            $(".chatPanel").css("display", "block");
        };
    }

    /*会话右键单击*/
    for (var i = 0; i < chatLi.length; i++) {
        chatLi[i].index = i;
        chatLi[i].oncontextmenu = function (e) {
            e.preventDefault();
            $(".rightClickMenu .li_index").val(this.index);
            console.info($(".rightClickMenu .li_index").val());
            RCMShow(".rightClickMenu", e.clientX, e.clientY);
        };
    }
    /*好友右键单击*/
    for (var i = 0; i < friLi.length; i++) {
        friLi[i].index = i;
        friLi[i].oncontextmenu = function (e) {
            e.preventDefault();
            RCMShow(".rightClickMenu_friend", e.clientX, e.clientY);
        };
    }
    /*群右键单击*/
    for (var i = 0; i < groLi.length; i++) {
        groLi[i].index = i;
        groLi[i].oncontextmenu = function (e) {
            e.preventDefault();
            RCMShow(".rightClickMenu_group", e.clientX, e.clientY);
        };
    }

    //点击空白区域，隐藏右键菜单
    document.addEventListener("click", function (event) {
        $(".rightClickMenu,.rightClickMenu_group,.rightClickMenu_friend").css("visibility", "hidden");
        $(".friendInfo").css("visibility", "hidden");
    });


    /*好友列表和群列表的切换*/
    var cateLi = $(".ContactP .cate ul li");
    cateLi[0].onclick = function () {
        cateLi[1].style.borderBottom = "";
        this.style.borderBottom = "2px solid #15BBF9";
        $(".ContactP .group_list").css("display", "none");
        $(".ContactP .friend_list").css("display", "block");

    };
    cateLi[1].onclick = function () {
        cateLi[0].style.borderBottom = "0px solid";
        this.style.borderBottom = "2px solid #15BBF9";
        $(".ContactP .friend_list").css("display", "none");
        $(".ContactP .group_list").css("display", "block");
    };

    /*打开个人信息弹框*/
    var hp = $(".mainPanel .head .head_top .headPortrait")[0];
    hp.onclick = function (e) {
        e = e || window.event;
        if (e.stopPropagation) {
            e.stopPropagation();
        } else {
            e.cancelBubble = true;
        }
        $(".box").css("display", "block");
        $(".personalInfo").css("top", '0');
        $(".personalInfo").animate({opacity: '1', top: '6%'}, 300);
    };

    /*修改备注弹框*/
    var modifyNote = $(".modifyNote");
    for (var i = 0; i < modifyNote.length; i++) {
        modifyNote[i].onclick = function () {
            $(".modify_note").css("visibility", "visible");
        }
    }

    /*查看资料信息*/
    var watchInfo = $(".watchInfo");
    for (var i = 0; i < watchInfo.length; i++) {
        watchInfo[i].onclick = function (e) {
            e = e || window.event;
            if (e.stopPropagation) {
                e.stopPropagation();
            } else {
                e.cancelBubble = true;
            }
            $(".friendInfo").css("visibility", "visible");
        }
    }

    /*关闭会话*/
    var closeConv = $(".rightClickMenu ul li");
    /*关闭单个会话*/
    closeConv[0].onclick = function () {
        var index = $(".rightClickMenu .li_index").val();
        console.info(index);
        $(".conversation ul li:eq(" + index + ")").remove();
    }
    //关闭全部会话
    closeConv[1].onclick = function () {
        $(".conversation ul li").remove();
    }

});

/*判断名字是否已经存在,
  如果存在，返回下标；
  如果不存在，返回-1
*/
function findNameIsExist(name) {
    var chatLi = $(".conversation ul li");
    var aLi = $(chatLi).find("i");
    var ary = new Array();
    for (var i = 0, len = aLi.length; i < len; i++) {
        ary[i] = aLi[i].innerHTML;
    }
    return ary.indexOf(name);
}

/*右键点击菜单显示*/
function RCMShow(cla, cx, cy) {
    //获取当前元素的高度
    var icH = $(cla).height();
    //获取当前浏览器可视区域的高度
    var wH = $(window).height();
    if ((cy + icH) > wH) {
        $(cla).css("top", cy - icH);
    } else {
        $(cla).css("top", cy);
    }
    $(cla).css("left", cx);
    $(cla).css("visibility", "visible");
}

//关闭修改备注弹框
function closeM() {
    $(".modify_note").css("visibility", "hidden");
}

function saveN() {
    var n = $("#note_m").val();

}

//关闭聊天面板
function closeC() {
    $(".chatPanel").css("display", "none");
}

function closeP() {
    $(".box").fadeOut(300);
    $(".personalInfo").animate({opacity: '0', top: '0'}, 300);
}

/*四个导航的切换*/
function tabToggle(operator, target) {
    $(operator).on({
        click: function () {
            $(".conversation").fadeOut(100);
            $(".ContactP").fadeOut(100);
            $(".more").fadeOut(100);
            $(".setting").fadeOut(100);
            $(target).slideToggle(200);
        }
    });
}

function getData() {
    $.ajax({
        type: "GET",
        url: "/getAllData",
        dataType: "json",
        scriptCharset: "utf-8",
        data: {
            email: $("#emailR").val(),
            name: $("#usernameR").val(),
            password: $("#passwordR").val()
        },
        success: function (data) {
            $("#errorPr").html(data);
        },
        error: function () {
            $("#errorPr").html("服务器故障，请重试！");
        }
    });
}

