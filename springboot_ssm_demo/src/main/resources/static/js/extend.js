$(function () {
   $("button").eq(0).on("click",function () {
       $.myjq();
   });
   $("button").eq(1).on("click",function () {
       $(".div1").myjq2();
   });
});