//jquery扩展方式一
$.myjq = function () {
    alert("hello my jquery!");
}

//jquery扩展方式二,常用
$.fn.myjq2 = function () {
    $(this).text("extend jquery text!");
}