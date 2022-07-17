/*
* 模拟jquery，自定义自己的jquery框架
* */
(function(window,undefined) {
    var myjQuery = function (selector) {
        /*
        * 在jquery的js文件中找到如下代码  myjQuery.fn = myjQuery.prototype = {}
        * 所以下面的jQuery.fn.init()等价于 jQuery.prototype.init();
        * */
        //return new myjQuery.fn.init();
        return new myjQuery.prototype.init(selector);
    }
    myjQuery.prototype = {
        constructor : myjQuery,
        init : function (selector){
            /*
            * 1.传入 '',null,undefined,NaN,0,false
            * */
            if(!selector){
                return this;
            }else if (typeof selector === "string") {  //2.传入代码片段或选择器
                /*
                * 2.1判断是不是字符串代码片段
                * */
                if (selector.charAt(0) == "<" && selector.charAt(selector.length - 1) == ">"
                    && selector.length >= 3) {
                    console.log("这里输入的是代码片段");
                    //2.1.1 根据代码片段创建元素
                    //非常灵活的创建代码片段元素的方式，无需遍历，学以致用
                    var temp = document.createElement("div");
                    temp.innerHTML = selector;

                    //2.1.2 将创建好的一级元素添加到jquery对象中（如果代码片段有多级元素只存第一级别的元素
                    // 可以参考jquery官方源码）
                    for (var i = 0; i < temp.children.length; i++) {
                        this[i] = temp.children[i];
                    }
                    
                    //2.1.3 给jquery对象添加length属性
                    this.length = temp.children.length;

                    //2.1.4 返回加工好的jquery对象
                    return this;
                }

            }
        }
    }
    myjQuery.prototype.init.prototype = myjQuery.prototype;
    window.myjQuery = window.$ = myjQuery;
})(window);