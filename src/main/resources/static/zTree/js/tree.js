var zNodes = null;
var zTreeObj, rMenu;
var op_type;//右键点击类型，1为增加，2为修改
var setting = {
    treeId : "",
    view: {
        dblClickExpand: true,
        showLine: true,
        selectedMulti: false,
        fontCss: function (treeId, treeNode) {
            var style = {};
            if (treeNode.level === 0 || treeNode.level === 1) {
                style = $.extend(style, {"font-weight": "bold"});
            }
            if ($.trim(treeNode.isgray) == "true") {
                style = $.extend(style, {"color": "gray", "cursor": "default"});
            }
            if (treeNode.Important == 0) {
                style = $.extend(style, {"background-color": "red", "color": "white"});
            }
            return style;
        }
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "_id",
            pIdKey: "_pId",
            rootPId: ""
        }
    },
    callback: {
        onRightClick: OnRightClick,
        onClick : zTreeNodeOnClick
    }
};

function zTreeNodeOnClick(event, treeId, treeNode) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" &&
        $(event.target).parents("a").length == 0) {
        zTreeObj.cancelSelectedNode();
        return;
    }
    $.ajax({
        type : "POST",
        url : "/getBy_id",
        dataType : "text",
        data : {
            id : treeNode._id
        },
        success : function (data) {
            var _node = JSON.parse(data);
            showNodeInfo(_node, event.clientX, event.clientY);
        },
        error : function () {
            alert("服务器错误！请重试")
        }
    });
}

function showNodeInfo(node, x, y) {
    var wH = $(window).height();
    var mH = $("#nodeInfoPanel").height();
    if ((y + mH) > wH) {
        y = y - mH;
    }
    var $labels = $("#nodeInfoPanel table tr td label");
    $labels[0].innerHTML = node.id;
    $labels[1].innerHTML = node.pId;
    $labels[2].innerHTML = node.name;
    $labels[3].innerHTML = node.CODE;
    $labels[4].innerHTML = node.PhoneCODE;
    $labels[5].innerHTML = node.LGTD;
    $labels[6].innerHTML = node.LATD;
    $labels[7].innerHTML = node.order;
    $labels[8].innerHTML = node._id;
    $labels[9].innerHTML = node._pId;
    $("#nodeInfoPanel").css({"top" : y + "px", "left" : x + "px", "visibility" : "visible"});
    $("body").bind("mousedown", onBodyMouseDown);
}


function OnRightClick(event, treeId, treeNode) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" &&
        $(event.target).parents("a").length == 0) {
        zTreeObj.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);
    } else if (treeNode) {
        if (treeNode.level === 5 || treeNode.level === 4) {
            zTreeObj.selectNode(treeNode);
            showRMenu("last", event.clientX, event.clientY);
        } else {
            zTreeObj.selectNode(treeNode);
            showRMenu("node", event.clientX, event.clientY);
        }
    }
}

function showRMenu(type, x, y) {
    var mH = $("#rMenu").height();
    var wH = $(window).height();
    if ((y + mH) > wH) {
        y = y - mH;
    }
    $("#rMenu ul").show();
    if (type == "root") {
        $("#m_del").hide();
        $("#m_gray").hide();
        $("#m_restore").hide();
        $("#m_hide").hide();
        $("#m_show").hide();
        $("#m_reset").hide();
    } else if (type == "last") {
        $("#m_add").hide();
        $("#m_gray").hide();
        $("#m_restore").hide();
    } else {
        $("#m_del").show();
        $("#m_gray").show();
        $("#m_restore").show();
        $("#m_hide").show();
        $("#m_show").show();
        $("#m_reset").show();
        $("#m_add").show();
    }
    y += document.body.scrollTop;
    x += document.body.scrollLeft;
    rMenu.css({"top": y + "px", "left": x + "px", "visibility": "visible"});
    $("body").bind("mousedown", onBodyMouseDown);
}

function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
        rMenu.css({"visibility": "hidden"});
    }
    if (!(event.target.id == "nodeInfoPanel" || $(event.target).parents("#nodeInfoPanel").length > 0)) {
        $("#nodeInfoPanel").css({"visibility": "hidden"});
    }
}

$(document).ready(function () {
    changeDataSource();
});

function changeDataSource() {
    var myOp = $("#section option:selected");
    $.ajax({
        type: "POST",
        url: "/treeList",
        data: {
            ds: myOp.val()
        },
        success: function (data) {
            showTree(data);
        },
        error: function () {
            $("#errorPr").html("服务器故障，请重试！");
        }
    });
}

function showTreeInfoPanel() {
    $("#treeInfoPanel").css("visibility", "visible");
}

function closePanel() {
    $("#treeInfoPanel").css("visibility", "hidden");
}


function showTree(data) {
    zNodes = JSON.parse(data);
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
    rMenu = $("#rMenu");

    var toExpandedNodes = zTreeObj.getNodesByFilter(function (treeNode) {
        return treeNode.level < 3;
    });
    $.each(toExpandedNodes, function (index, treeNode) {
        zTreeObj.expandNode(treeNode, true, false, false);
    });

    /*var allNodes = zTreeObj.transformToArray(zTreeObj.getNodes());
    for (var i = 0, len = allNodes.length; i < len; i++) {
        var _node = allNodes[i];
        $("#" + zTreeObj.setting.treeId + "_" + _node.tId + "_a").bind("mouseover", function () {
            alert(_node._id + "," + _node.name);
        });
    }*/
}

function addTreeNode() {
    hideRMenu();
    op_type = 1;//将操作类型变量置为1，代表增加节点
    var nodes = zTreeObj.getSelectedNodes();
    //var nodeString = $("#nodeData").val();//获取父节点信息
    showDataInPanel(JSON.stringify(nodes[0]));
    showTreeInfoPanel();
    /*var newNode = { name:"增加" + (addCount++)};*/
    /*if (zTreeObj.getSelectedNodes()[0]) {
        newNode.checked = zTreeObj.getSelectedNodes()[0].checked;
        zTreeObj.addNodes(zTreeObj.getSelectedNodes()[0], newNode);
    } else {
        zTreeObj.addNodes(null, newNode);
    }*/
}

function removeTreeNode() {
    hideRMenu();
    var nodes = zTreeObj.getSelectedNodes();
    if (nodes && nodes.length > 0) {
        if (nodes[0].children && nodes[0].children.length > 0) {
            var msg = "要删除的节点是父节点，如果要删除此节点，必须先删除其子节点，\n\n请谨慎操作！";
            alert(msg);
        } else {
            if (confirm("确定删除节点吗？\n\n操作不可回退！") == true) {
                removeNode(nodes[0]);
                zTreeObj.removeNode(nodes[0]);
            }
        }
    }
}

function removeNode(node) {
    $.ajax({
        type: "POST",
        url: "/deleteNode",
        dataType: "text",
        scriptCharset: "utf-8",
        data: {
            _id: node._id
        },
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert("服务器故障");
        }
    });
}

function simpleModifyToTreeNode(opType, value) {
    hideRMenu();
    var nodes = zTreeObj.getSelectedNodes();
    if (confirm("是否确定对该节点的操作？") == false) {
        return false;
    } else {
        $.ajax({
            url: "/hideNode",
            type: "POST",
            dataType: "text",
            data: {
                id: nodes[0]._id,
                opType: opType,
                value: value
            },
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert("服务器异常！")
            }
        });
    }
}


function modifyTree() {
    hideRMenu();
    op_type = 2;
    var nodes = zTreeObj.getSelectedNodes();
    showDataInPanel(JSON.stringify(nodes[0]));
    showTreeInfoPanel();
    /*var newNode = { name:"增加" + (addCount++)};*/
    /*if (zTreeObj.getSelectedNodes()[0]) {
        newNode.checked = zTreeObj.getSelectedNodes()[0].checked;
        zTreeObj.addNodes(zTreeObj.getSelectedNodes()[0], newNode);
    } else {
        zTreeObj.addNodes(null, newNode);
    }*/
}

function addOrModify() {
    var res = checkForm();
    if (!res) {
        alert("表单必填字段未填写");
    } else {
        var url = null;
        if (op_type == 1) {
            url = "/addNode";
        } else if (op_type == 2) {
            url = "/updateNode";
        }
        $.ajax({
            type: "POST",
            url: url,
            dataType: "text",
            scriptCharset: "utf-8",
            data: {
                id: $("#id").val(),
                pId: $("#pId").val(),
                name: $("#name").val(),
                CODE: $("#CODE").val(),
                phoneCODE: $("#PhoneCODE").val(),
                Important: $("#Important").val(),
                PhoneImportant: $("#PhoneImportant").val(),
                LGTD: $("#LGTD").val(),
                LATD: $("#LATD").val(),
                order: $("#order").val(),
                icon: $("#icon").val(),
                tcount: $("#tcount").val(),
                isgray: $.trim($("#isgray").val()),
                phonemodel: $("#phonemodel").val(),
                phoneshowmodel: $("#phoneshowmodel").val(),
                type: $("#type").val(),
                _id: $("#_id").val(),
                _pId: $("#_pId").val(),
                SoftType: $("#SoftType").val()
            },
            success: function (data) {
                alert(data);
            },
            error: function () {
                alert("服务器故障");
            }
        });
    }

}

/**
 * 向面板赋值
 * @param nodeString
 */
function showDataInPanel(nodeString) {
    var node = JSON.parse(nodeString);
    if (node != null) {
        if (op_type == 2) {
            $("#id").val(node.id);
            $("#pId").val(node.pId);
            $("#name").val(node.name);
            $("#CODE").val(node.CODE);
            $("#PhoneCODE").val(node.PhoneCODE);
            $("#Important").val(node.Important);
            $("#PhoneImportant").val(node.PhoneImportant);
            $("#LGTD").val(node.LGTD);
            $("#LATD").val(node.LATD);
            $("#order").val(node.order);
            $("#icon").val(node.icon.substring(node.icon.indexOf("icons")));
            $("#tcount").val(node.tcount);
            $("#isgray").val(node.isgray);
            $("#phonemodel").val(node.phonemodel);
            $("#phoneshowmodel").val(node.phoneshowmodel);
            $("#type").val(node.type);
            $("#_id").val(node._id);
            $("#_pId").val(node._pId);
            $("#SoftType").val(node.SoftType);
        } else if (op_type == 1) {
            var pu = $("#treeInfoPanel input[type=text]");
            for (var i = 0, len = pu.length; i < len; i++) {
                pu[i].value = "";
            }
            $("#Important").val(1);
            $("#PhoneImportant").val(1);
            $("#icon").val(node.icon.substring(node.icon.indexOf("icons")));
            $("#tcount").val(1);
            $("#phonemodel").val(2);
            $("#phoneshowmodel").val(2);
            $("#_pId").val(node._id);
        }
    }
}

function LATDAndLGTDConversion(str) {
    if (str.indexOf(",") == -1) {
        return str.toFixed(6);
    } else {
        var s = str.split(",");
        for (var i = 0; i < s.length; i++) {
            s[i] = parseFloat(s[i]);
        }
        var t1 = 60, t2 = 3600;
        var res = s[0] + s[1] / t1 + s[2] / t2;
        return res.toFixed(6);
    }
}

function checkNull(tag) {
    if (!tag.value) {
        tag.placeholder = "这是必填字段";
        tag.style.border = "1px solid red";
        return false;
    } else {
        tag.style.border = "";
        return true;
    }
}

function checkForm() {
    var pu = [$("#id"), $("#pId"), $("#name"), $("#Important")];
    var res = true;
    for (var i = 0, len = pu.length; i < len; i++) {
        if (!pu[i].val()) {
            pu[i].attr("placeholder", "必填字段必须填写");
            pu[i].css("border", "1px solid red");
            res = false && res;
        } else {
            pu[i].css("border", "");
            res = true && res;
        }
    }
    return res;
}






