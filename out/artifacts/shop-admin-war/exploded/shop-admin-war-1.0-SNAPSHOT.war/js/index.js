/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: qky-resource
 * $Id:  Format.js 2015-11-13 18:08:44 $
 */
/* App Module */
/*
 var modelApp = angular.module('modelApp', []);

 modelApp.config(function($interpolateProvider) {
 $interpolateProvider.startSymbol('[[');
 $interpolateProvider.endSymbol(']]');
 });
 function modelListCtrl ($scope,$http) {
 //初始化操作
 var base=null;
 function init() {
 //生成base类实例
 base = new Base($http);
 var today=new Date();
 var strDate=(" "+today.getYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日");
 var n_day=today.getDay();
 switch(n_day){
 case 0:
 {strDate=strDate+" 星期日 "}break;
 case 1:
 {strDate=strDate+" 星期一 "}break;
 case 2:
 {strDate=strDate+" 星期二 "}break;
 case 3:
 {strDate=strDate+" 星期三 "}break;
 case 4:
 {strDate=strDate+" 星期四 "}break;
 case 5:
 {strDate=strDate+" 星期五 "}break;
 case 6:
 {strDate=strDate+" 星期六 "}break;
 case 7:
 {strDate=strDate+" 星期日 "}break;
 }
 $scope.strDate=strDate;
 }
 init();
 }
 */
$(function(){
    $.post('/menu/queryMenuByAdmin.do',function(data){

        if(data.rtnCode=="0000000"){
            var menuList = data.bizData.menuList;
            createMenus(menuList);
        }else{
            alert('登录超时或服务无响应，请重新登录。');
        }
    });
});
function createMenus(menus){
    var html = '';
    for(var index in menus){
        html+='<ul>';
        html+='<span><em><img src="images/icon_c3.png"/></em>'+menus[index].name+'<b></b></span>';
        var menuList = menus[index].menuList;
        for(var i in menuList){
            html+='<li><a href="'+menuList[i].menuUrl+'" target="mainFrame">'+menuList[i].name+'</a></li>';
        }
        html+='</ul>';
    }
    $('#nav').html(html);
    myMenu = new FOLDMenu("nav",true);
    myMenu.init();
    $("#name").text($.cookie("admin"));
}