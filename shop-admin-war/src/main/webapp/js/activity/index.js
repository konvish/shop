/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Activity.js 2016-03-01 16:40:27 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope,$http) {

    $scope.add=function(){
        var param="name="+ $scope.addActivityName+"&commodityId="+ $("#addCommodityName").val()+"&activityPrice="+$("#addActivityPrice").val()+"&startDate="+$("#addStartDate").val()+"&endDate="+$("#addEndDate").val();
        base.connectService('/activity/addActivity.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("增加成功！");
                window.location="/activity/list.do";
            }else{
                alert(data.msg);
            }
        }, function(data){});
    };

    $scope.edit=function(id,commodityId){
        $("#P2").fadeIn('fast');
        var me = this;
        var param="id="+id+"&commodityId="+commodityId;
        base.connectService('/activity/getActivity.do',param, function(data){
            if(data.rtnCode == "0000000"){
                var activity = data.bizData.activity;
                $('#updateActivityName').val(activity.name);
                $('#updateId').val(activity.id);
                $('#updateCommodityType').val(activity.categoryName);
                $('#updateCommodityName').val(activity.commodityName);
                $('#updateActivityPrice').val(activity.activityPrice);
                $('#updateStartDate').val(activity.startDate);
                $('#updateEndDate').val(activity.endDate);
            }else{
                alert(data.msg);
            }
        }, function(data){});
    }

    $scope.update=function() {
        var activityName=$('#updateActivityName').val();
        var id=$('#updateId').val();
        var commodityId = $('#updateCommodityName').val();
        var activityPrice = $('#updateActivityPrice').val();
        var startDate = $('#updateStartDate').val();
        var endDate = $('#updateEndDate').val();

        var params="id="+id+"&name="+activityName+"&commodityId="+commodityId+"&activityPrice="+activityPrice+"&startDate="+startDate+"&endDate="+endDate;
        base.connectService('/activity/modifyActivity.do',params, function(data){
            if(data.rtnCode=="0000000"){
                alert("修改成功");
                window.location = "/activity/list.do";
            }else{
                alert(data.msg);
            }
        }, function(data){});
    }

    $scope.delete=function(id){
        var param="id="+id+"&status=8";
        base.connectService('/activity/modifyActivity.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("删除成功");
                window.location="/activity/list.do";
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
        base.connectService('/activity/queryActivityPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.activityList=res.bizData.activityList;
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

    //查询商品类别
    function queryCategoryList(){
        var param="";
        base.connectService('/category/queryCategory.do',param, function(data){
            if(data.rtnCode=="0000000"){
                $scope.categoryList=data.bizData.categoryList;
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }

    //根据商品类别查询商品列表
    $scope.queryCommodityList = function(categoryId){
        var param ="categoryId="+categoryId+"&storeId="+$scope.storeId;
        base.connectService('/commodity/queryCommodityByCategoryId.do',param, function (data) {
            if(data.rtnCode=="0000000"){
                $scope.commodityList = data.bizData.commodityList;
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        },function(data){});
    };

    //检查参数
    $scope.checkData =function(op,keys,names) {
        var keyArr=keys.split(",");
        var nameArr=names.split(",");
        for (var i=0;i<keyArr.length ;i++ )
        {
            var value=$('#'+keyArr[i]).val();
            if(value.length<1){
                alert("请输入:"+nameArr[i]+"。");
                return;
            }else if(value=="?"){
                alert("请选择"+nameArr[i]+"。");
                return;
            }
        }

        if(op=="update"){
            $scope.update();
        }else{
            $scope.add();
        }
    };

    //批量删除信息

    $scope.deleteAll=function(){
        var me = this;
        var aa = this.selected;
        if (aa.length == 0) {
            alert("请选择所要删除的信息");
            return;
        } else {
            var r=confirm("确认删除？");
            if(r==true) {
                var param = "ids=" + aa;
                <!-- param={'ids':aa};-->
                base.connectService('/activity/deleteAll.do', param, function (res) {
                    if (res.rtnCode == "0000000") {
                        alert("删除的信息成功！");
                        me.load();
                    } else {
                        alert(res.msg);
                    }
                }, function (res) {
                });
            }
            else return;
        }


    }


    $scope.checkAll= function ($event,list) {
        var checkbox = $event.target;
        var action = (checkbox.checked?'add':'remove');
        if(action=='add'){
            $scope.selected=[];
            angular.forEach(list,function(obj){
                $scope.selected.push(obj.id);
            })
        }else{
            $scope.selected=[];
        }
    }

    $scope.selected = [];
    $scope.selectedTags = [];

    var updateSelected = function(action,id,name){
        if(action == 'add' && $scope.selected.indexOf(id) == -1){
            $scope.selected.push(id);
            $scope.selectedTags.push(name);
        }
        if(action == 'remove' && $scope.selected.indexOf(id)!=-1){
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx,1);
            $scope.selectedTags.splice(idx,1);
        }
    }


    $scope.updateSelection = function($event, id){
        var checkbox = $event.target;
        var action = (checkbox.checked?'add':'remove');
        updateSelected(action,id,checkbox.name);
    }



    $scope.isSelected = function(id){
        return $scope.selected.indexOf(id)>=0;
    }

    //初始化操作
    var base=null;
    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.load();
        $scope.storeId = window.localStorage.getItem("storeId");
        queryCategoryList()
    }
    init();
}
