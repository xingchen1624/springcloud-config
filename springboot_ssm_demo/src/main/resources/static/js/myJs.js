//地图
var map = null;
//保存覆盖物
var overlays = [];
//经纬度
var lngLat = '';
//增删改节点初始样式
var log, className = "dark";
//当前选中的覆盖物
var overlay = {
    polygon : null,
    editStatus : false,
    type : 'polygon'
};

$(function () {
    //加载地图
    loadMap();

    //加载静态树
    //loadSimpleTree();

    //加载动态树
    loadDynamicTree();

});

function initTabs(){
    /*<![CDATA[*/
    var ctxPath = /*[[@{/}]]*/ '';
    /*]]>*/
    console.log(ctxPath);
    var url = window.location.href;
    var pathname = window.location.pathname;
    console.log(url);
    console.log(pathname);
    $.ajaxSetup ({
        cache: false //关闭AJAX相应的缓存
    });
    $('#myTabs a:first').tab('show');//初始化显示哪个tab
    $('#test').load('/'+ctxPath+'/templates/tree.html');
    $('#myTabs a').click(function (e) {
        e.preventDefault();//阻止a链接的跳转行为
        $(this).tab('show');//显示当前选中的链接及关联的content
    })
}

//加载百度地图
function loadMap() {
    map = new BMap.Map("map");          // 创建地图实例
    var point = new BMap.Point(116.404, 39.915);  // 创建点坐标
    map.centerAndZoom("杭州市", 15);  //根据城市名聚焦
    //map.centerAndZoom(point, 15);     // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    map.disableDoubleClickZoom();  //去除地图双击放大效果

    //覆盖物数组初始化
    var overlaycomplete;
    overlaycomplete = function (e) {
        overlays.push(e.overlay);
        //给覆盖物添加双击事件
        e.overlay.addEventListener("dblclick", function () {
            //弹出一个对话框保存该网格范围 - 和树联动
            $('.bottom').show();
            //清空弹出框
            $('#gridNo').val('');
            $('#gridName').val('');
            $('#supGridNo').val('');

            var path = e.overlay.getPath();//Array<Point> 返回多边型的点数组
            for (var i = 0; i < path.length; i++) {
                console.log("lng:" + path[i].lng + "\n lat:" + path[i].lat);
                lngLat += path[i].lng+','+path[i].lat+'|';
            }
            if(lngLat != null && lngLat != ''){
                lngLat = lngLat.substr(0,lngLat.length-1);
                alert(lngLat);
            }
        });

        //给覆盖物添加单击事件
        e.overlay.addEventListener("click", function () {
            console.log("单击覆盖物");
            overlay.polygon = e.overlay;
        });
    };

    var styleOptions = {
        strokeColor:"blue",    //边线颜色。
        fillColor:"skyblue",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,    //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    };
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: true, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
            scale: 0.8, // 工具栏缩放比例
            drawingModes:[
                BMAP_DRAWING_MARKER, // 画点
                BMAP_DRAWING_CIRCLE, // 画圆
                //BMAP_DRAWING_POLYLINE, // 画线
                BMAP_DRAWING_POLYGON, // 画多边形
                BMAP_DRAWING_RECTANGLE // 画矩形
            ]
        },
        circleOptions: styleOptions, //圆的样式
        polylineOptions: styleOptions, //线的样式
        polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式
    });

    //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);

}
//弹出保存网格对话框
function saveGrid() {
    $('.bottom').show();
}

/*
*  保存网格信息
* */
function saveGridInfo(){
    //获取输入信息
    var gridNo = $('#gridNo').val();
    var gridName = $('#gridName').val();
    var supGridNo = $('#supGridNo').val();

    //异步保存
    $.ajax({
        url: '/saveGridInfo',
        type: 'post',
        data : {'gridNo':gridNo,'gridName':gridName,'supGridNo':supGridNo,'lngLat':lngLat},
        dataType: "json",
        success:function(data){
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            treeObj.reAsyncChildNodes(null,"refresh",false,function () {
                //定位到新增的节点
                var gridNo = data.gridNo;
                var tree = $.fn.zTree.getZTreeObj("treeDemo");
                var node = tree.getNodeByParam("id",gridNo);
                var parent = node.getParentNode();
                if(!parent.open){
                    tree.expandNode(parent,true,true);
                }
                tree.checkNode(node , true,true);
            });
            alert("保存成功");

            $('.bottom').hide();
        },
        error: function(data){
            alert(data.message);
        }
    });
}

/**
 * 获取选中节点
 */
function getSelectedNodes() {
    var tree = $.fn.zTree.getZTreeObj("treeDemo");
    //复选框选中的节点
    var nodes = tree.getCheckedNodes();
    //光标选中的节点
    //var nodes = tree.getSelectedNodes();
    for (var i = 0; i < nodes.length; i++) {
        alert(nodes[i].id);
    }
}

/*
* 取消-关闭弹出框
* */
function cancel(){
    $('.bottom').hide();
}

/*
* 取消-左侧第二栏树div
* */
function cancel2(){
    $('.left2').hide(1000);
}

//弹出编辑网格对话框
function editGrid() {
    //开启网格编辑功能
    console.log(overlay);
    overlay.enableEditing();
}

//清空地图所有覆盖物
function clearAll() {
    for(var i = 0; i < overlays.length; i++){
        map.removeOverlay(overlays[i]);
    }
    overlays.length = 0;
}

//加载动态树
function loadDynamicTree() {
    var setting = {
        check: {
            enable: true,
            //chkboxType : {"Y": "ps", "N": "ps"},
            chkStyle : "checkbox"
        },

        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true,
            editNameSelectAll: true,
            showRemoveBtn: showRemoveBtn,
            showRenameBtn: showRenameBtn
        },

        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey:"pid",
                name:"name"
            }
        },
        async: {
            enable: true,
            url: "/getTreeData2",
            autoParam: ["id", "pid"]
        },
        callback: {
            onClick: loadMyMap,
            beforeDrag: beforeDrag,
            beforeEditName: beforeEditName,
            beforeRemove: beforeRemove,
            beforeRename: beforeRename,
            beforeClick: beforeClick,
            onRemove: onRemove,
            onRename: onRename
        }

    };

    //加载后端构建的ZTree树（节点的数据格式已在后端格式化好了）
    $.ajax({
        url: '/getTreeData2',
        type: 'post',
        data : {'orgId':'A1000'},
        dataType: "json",
        success:function(data){
            console.log(data);
            $.fn.zTree.init($("#treeDemo"), setting, data);//初始化树节点时，添加同步获取的数据
            fuzzySearch('treeDemo','#key',null,true); //初始化模糊搜索方法
        },
        error: function(data){
            alert(data.message);
        }
    });
}

//根据经纬度坐标加载地图
function loadMyMap(event, treeId, treeNode, clickFlag) {
    //获取选中节点的主键
    var id = treeNode.id;
    console.log(treeNode.isParent);  //判断一个节点是否是父节点

    //根据id获取经纬度
    $.ajax({
        url: '/getGridInfo',
        type: 'post',
        data : {'id':id},
        dataType: "text",
        success:function(data){
            //根据经纬度加载覆盖物
            console.log(data);
            var strs = new Array();
            strs = data.split("|");
            var polygonArray = new Array();
            for (var i = 0; i < strs.length; i++) {
                var pointArray = new Array();
                pointArray = strs[i].split(",");
                polygonArray.push(new BMap.Point(pointArray[0],pointArray[1]));
            }
            var polygon = new BMap.Polygon(polygonArray, {strokeColor:"blue", strokeWeight:2, strokeOpacity:1});
            //先清空覆盖物
            map.clearOverlays();
            map.addOverlay(polygon);           //增加多边形
            overlays.push(polygon);

            //重新给覆盖物添加双击事件
            polygon.addEventListener("dblclick", function () {
                //关闭可能存在的的编辑状态
                polygon.disableEditing();
                //弹出一个对话框保存该网格范围 - 和树联动
                $('.bottom').show();
                //清空弹出框
                $('#gridNo').val('');
                $('#gridName').val('');
                $('#supGridNo').val('');

                var path = polygon.getPath();//Array<Point> 返回多边型的点数组
                for (var i = 0; i < path.length; i++) {
                    console.log("lng:" + path[i].lng + "\n lat:" + path[i].lat);
                    lngLat += path[i].lng+','+path[i].lat+'|';
                }
                if(lngLat != null && lngLat != ''){
                    lngLat = lngLat.substr(0,lngLat.length-1);
                    alert(lngLat);
                }
            });

            //重新给覆盖物添加单击事件
            polygon.addEventListener("click", function () {
                console.log("单击覆盖物");
                overlay = polygon;
            });
        },
        error: function(data){
            alert(data.message);
        }
    });

}

/**
 * 增、删、改节点
 */
function beforeDrag(treeId, treeNodes) {
    return false;
}
function beforeEditName(treeId, treeNode) {
    className = (className === "dark" ? "":"dark");
    showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    setTimeout(function() {
        if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
            setTimeout(function() {
                zTree.editName(treeNode);
            }, 0);
        }
    }, 0);
    return false;
}
function beforeRemove(treeId, treeNode) {
    className = (className === "dark" ? "":"dark");
    showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}
function onRemove(e, treeId, treeNode) {
    showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
}
function beforeRename(treeId, treeNode, newName, isCancel) {
    className = (className === "dark" ? "":"dark");
    showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
    if (newName.length == 0) {
        setTimeout(function() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.cancelEditName();
            alert("节点名称不能为空.");
        }, 0);
        return false;
    }
    return true;
}
//处理只能选取叶子节点
function beforeClick(treeId, treeNode, clickFlag) {
    //当是父节点 返回false 不让选取
    return !treeNode.isParent;
}
function onRename(e, treeId, treeNode, isCancel) {
    showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
}
function showRemoveBtn(treeId, treeNode) {
    return !treeNode.isFirstNode;
}
function showRenameBtn(treeId, treeNode) {
    return !treeNode.isLastNode;
}
function showLog(str) {
    if (!log) log = $("#log");
    log.append("<li class='"+className+"'>"+str+"</li>");
    if(log.children("li").length > 8) {
        log.get(0).removeChild(log.children("li")[0]);
    }
}
function getTime() {
    var now= new Date(),
        h=now.getHours(),
        m=now.getMinutes(),
        s=now.getSeconds(),
        ms=now.getMilliseconds();
    return (h+":"+m+":"+s+ " " +ms);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
        return false;
    });
};
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
};

/*
* 判断一个点是否在一个多边形内
* 引申判断一个多边形是否在另一个多边形内：只要判断多边形的关键点是否都在另一个多边形内即可
* 注意：如果多边形点特别多，并且要比较的多边形很多的时候，考虑性能
* 解决方案2：使用各数据库提供的空间函数  eg:st_within(a,b)判断a,b两个多边形的包含关系等等
* */
function ptInPolygon(point) {  //判断传入的points点是否在ply多边形里面，是返回true，否返回false
    var overlays = [
        new BMap.Point(106.602849,29.562462),new BMap.Point(106.635619,29.595882),
        new BMap.Point(106.65143,29.571383),new BMap.Point(106.620532,29.512593),
        new BMap.Point(106.5948,29.52815),new BMap.Point(106.602849,29.562462)
    ];
    var ply = new BMap.Polygon(overlays);
    var result = BMapLib.GeoUtils.isPointInPolygon(point, ply);
    if (result == true) {
        console.log("yes");
        return true;
    } else {
        console.log("no");
        return false;
    }
}

function loadSimpleTree() {
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"父节点1 - 展开", open:true},
        { id:11, pId:1, name:"父节点11 - 折叠"},
        { id:111, pId:11, name:"叶子节点111"},
        { id:112, pId:11, name:"叶子节点112"},
        { id:113, pId:11, name:"叶子节点113"},
        { id:114, pId:11, name:"叶子节点114"},
        { id:12, pId:1, name:"父节点12 - 折叠"},
        { id:121, pId:12, name:"叶子节点121"},
        { id:122, pId:12, name:"叶子节点122"},
        { id:123, pId:12, name:"叶子节点123"},
        { id:124, pId:12, name:"叶子节点124"},
        { id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
        { id:2, pId:0, name:"父节点2 - 折叠"},
        { id:21, pId:2, name:"父节点21 - 展开", open:true},
        { id:211, pId:21, name:"叶子节点211"},
        { id:212, pId:21, name:"叶子节点212"},
        { id:213, pId:21, name:"叶子节点213"},
        { id:214, pId:21, name:"叶子节点214"},
        { id:22, pId:2, name:"父节点22 - 折叠"},
        { id:221, pId:22, name:"叶子节点221"},
        { id:222, pId:22, name:"叶子节点222"},
        { id:223, pId:22, name:"叶子节点223"},
        { id:224, pId:22, name:"叶子节点224"},
        { id:23, pId:2, name:"父节点23 - 折叠"},
        { id:231, pId:23, name:"叶子节点231"},
        { id:232, pId:23, name:"叶子节点232"},
        { id:233, pId:23, name:"叶子节点233"},
        { id:234, pId:23, name:"叶子节点234"},
        { id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
    ];

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
}