var da;
(function () {
    console.log("js文件执行");
    LoadData();
    function LoadData() {
        console.log("LoadData方法执行");
        $.getJSON("/treeList", {
        }, function(json) {
            testData(JSON.stringify(json));
        })
    }
    
    function testData(data) {
        da = data;
    }
    console.log(test);
}())