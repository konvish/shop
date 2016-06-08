/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  ActivityOrder.js 2016-01-18 15:55:21 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope,$http) {

    $scope.add=function(){
        var param="name="+ $scope.addActivityOrderName;
        base.connectService('/activityOrder/addActivityOrder.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("增加成功！");
                window.location="/activityOrder/list.do";
            }else{
                alert(data.msg);
            }
        }, function(data){});
    }

    $scope.edit=function(id){
        $("#P2").fadeIn('fast');
        var me = this;
        var param="id="+id;
        base.connectService('/activityOrder/getActivityOrder.do',param, function(data){
            if(data.rtnCode == "0000000"){
                var activityOrder = data.bizData.activityOrder;
                $('#updateActivityOrderName').val(activityOrder.name);
                $('#updateId').val(activityOrder.id);
            }else{
                alert(data.msg);
            }
        }, function(data){});
    }

    $scope.update=function() {
        var activityOrderName=$('#updateActivityOrderName').val();
        var id=$('#updateId').val();

        var params="id="+id+"&name="+activityOrderName;
        base.connectService('/activityOrder/modifyActivityOrder.do',params, function(data){
            if(data.rtnCode=="0000000"){
                alert("修改成功");
                window.location = "/activityOrder/list.do";
            }else{
                alert(data.msg);
            }
        }, function(data){});
    }

    $scope.delete=function(id){
        var param="id="+id;
        base.connectService('/activityOrder/deleteActivityOrder.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("删除成功");
                window.location="/activityOrder/list.do";
            }else{
                alert(data.msg);
            }
        }, function(data){});
    }

    //分页查询
    $scope.currentPage = 1;
    $scope.totalPage = 1;
    $scope.pageSize = 10;
    $scope.pages = [];
    $scope.endPage = 1;
    $scope.targetPage = $scope.currentPage;

    $scope.load = function () {
        var searchName=$("#searchName").val();
        if(searchName == '请输入关键字'){
            searchName ='';
        }
        $scope.targetPage = $scope.currentPage;
        var param="currentPage="+$scope.currentPage+"&pageSize="+$scope.pageSize+"&name="+searchName;
        base.connectService('/activityOrder/queryActivityOrderPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.activityOrderList=res.bizData.activityOrderList;
                $scope.totalPage = Math.ceil( res.bizData.totalRecord / $scope.pageSize);
                $scope.endPage = $scope.totalPage;
                $scope.totalRecord=res.bizData.totalRecord;

                //生成数字链接
                if ($scope.currentPage > 1 && $scope.currentPage < $scope.totalPage) {
                    $scope.pages = [
                        $scope.currentPage - 1,
                        $scope.currentPage,
                        $scope.currentPage + 1
                    ];
                } else if ($scope.currentPage == 1 && $scope.totalPage > 1) {
                    $scope.pages = [
                        $scope.currentPage,
                        $scope.currentPage + 1
                    ];
                } else if ($scope.currentPage == $scope.totalPage && $scope.totalPage > 1) {
                    $scope.pages = [
                        $scope.currentPage - 1,
                        $scope.currentPage
                    ];
                }
            } else {
                alert(res.msg);
            }
        }, function (res) {
        });
    }
    $scope.next = function () {
        if ($scope.currentPage < $scope.totalPage) {
            $scope.currentPage++;
            $scope.load();
        }
    };

    $scope.prev = function () {
        if ($scope.currentPage > 1) {
            $scope.currentPage--;
            $scope.load();
        }
    };

    $scope.toPage = function () {
        var toPage=$("#toPage").val();
        if (toPage > 0 && toPage <= $scope.totalPage){
            $scope.currentPage = toPage;
            $scope.load();
        }
    };

    $scope.pageSizeChange = function(){
        $scope.load();
    };

    //初始化操作
    var base=null;
    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.load();
    }
    init();
}
