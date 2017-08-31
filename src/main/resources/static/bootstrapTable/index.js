// JavaScript Document
$(function () {
    initTable();
});

function doQuery() {
    $.ajax({
        type: "POST",
        url: "/testTable",
        dataType: "json",
        data: {},
        success: function (data) {
            $("#table").bootstrapTable("load", data);
        },
        error: function () {
            alert("服务器错误");
        }
    });
}

function initTable() {
    $("#table").bootstrapTable({
        url: "/testTable",
        method: "post",
        contentType: "application/x-www-form-urlencoded",//后台接收数据必备
        toolbar: '#toolbar',  //使用自定义的工具条
        cache: false,         //缓存
        striped: true,
        pagination: true,
        sortable: true,
        sortOrder: "asc",
        sidePagination: "server",  //采用服务器分页方式
        queryParams: queryParams,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        clickToSelect: true, //是否启用点击选中行

        showExport: true,
        searchOnEnterKey: true,
        search: true,
        strictSearch: true,
        height: 500,
        pageNumber: 1,
        pageSize: 10,
        pageList: [10, 25, 50, 100],
        responseHandler: responseHandler,
        uniqueId: 'id',
        //表格行内样式
        rowStyle: function (row, index) {
            var strClass = "";
            if (index % 2 == 0) {
                strClass = 'info';
            } else if (index % 5 == 0) {
                strClass = 'danger';
            } else {
                return {};
            }
            return {classes: strClass}
        },

        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '序号',
            align: 'center',
            sortable: true
        }, {
            field: 'group_name',
            title: '群名',
            align: 'center'
        }, {
            field: 'group_lord',
            title: '群主',
            align: 'center'
        }, {
            field: 'group_notes',
            title: '群备注',
            align: 'center',
            editable: {
                type: 'text',
                title: '群备注',
                validate: function (v) {
                    if (!v) return '不能为空';
                }

            }
        }, {
            field: 'createDate',
            title: '时间',
            align: 'center',
            //自定义函数
            formatter: function (value, row, index) {
                 return formatDate(value, "yyyy-MM-dd");
            },
            editable : {
                type : 'date',
                title : '时间'
            }
        }, {
            field: 'cz',
            title: '操作',
            align: 'center',
            //自定义函数
            formatter: function (value, row, index) {
                var a = "<button class='btn btn-info'>修改</button>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<button class='btn btn-danger'>删除</button>";
                return a;
            }
        }],

        //行内编辑交互函数
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                type: "POST",
                url: "/testTable",
                data: row,
                dataType: "text",
                success: function (data, status) {
                    if (status == "success") {
                        alert("数据提交成功");
                    }
                },
                error: function () {
                    alert("服务器连接失败");
                }
            });
        }

    });

}

function formatDate(date) {
    var d = new Date(parseInt(date)).toLocaleString().replace(/:\d{1,2}$/,'');
    console.log(d);
}

//处理后台传出的数据
function responseHandler(res) {
    if (res) {
        return {
            "rows": res.data,
            "total": res.count
        };
    } else {
        return {
            "rows": [],
            "total": 0
        };
    }
}

//整理需要传给后台的数据
function queryParams(params) {
    var temp = {
        limit: this.limit,   //页面大小
        offset: this.offset,
        pageIndex: this.pageNumber,
        pageSize: this.pageSize,
        userName: $("#userName").val()
    };
    return temp;
}

/**
 * 将时间戳格式化为日期字符串
 * @param timestamp
 * @param formatString
 * @returns {string}
 */
function formatDate(timestamp, formatString){
    var newDate = new Date(timestamp),
        y = newDate.getFullYear(),
        m = newDate.getMonth() + 1,
        d = newDate.getDate(),
        h = newDate.getHours(),
        mm = newDate.getMinutes(),
        s = newDate.getSeconds();
    m = m < 10 ? "0"+m : m;
    d = d < 10 ? "0"+d : d;
    h = h < 10 ? "0"+h : h;
    mm = mm < 10 ? "0"+mm : mm;
    s = s < 10 ? "0"+s : s;

    var len = formatString.length;

    var sep1 = formatString.charAt(4), 	//获取年份后面的分隔符
        sep2 = formatString.charAt(7),	//获取月份后面的分隔符
        sepR = formatString.charAt(10);
    var dS = y + sep1 + m + sep2 + d + sepR;

    if(len > 11) {
        var	sep3 = formatString.charAt(len-6), //获取小时后面的分隔符
            sep4 = formatString.charAt(len-3), //获取分钟后面的分隔符
            sepT = formatString.charAt(len-9);	//获取日期与时间之间的分隔符
        dS = dS + sepT + h + sep3 + mm + sep4 + s
    }
    return dS;
}



