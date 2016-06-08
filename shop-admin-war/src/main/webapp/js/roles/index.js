/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: qky-resource
 * $Id:  Roles.js 2015-11-13 18:08:47 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope,$http) {

    $scope.checkData = function (op, keys, names,treeId,tip) {
        var keyArr = keys.split(",");
        var nameArr = names.split(",");
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var sNodes = treeObj.getCheckedNodes(true);
        for (var i = 0; i < keyArr.length; i++) {
            var value = $('#' + keyArr[i]).val();
            if (value.length < 1) {
                alert("请输入" + nameArr[i] + "。");
                return;
            } else if (value == "?") {
                alert("请选择" + nameArr[i] + "。");
                return;
            }
        }
        if(sNodes.length < 1){
            alert("请选择" + tip + "。");
            return;
        }

        if (op == "update") {
            $scope.update();
        } else {
            $scope.add();
        }
    }

    $scope.add=function(){
        var checkValue=$(':radio[name=check]:checked ').val();
        var param="name="+ $scope.addRolesName+"&description="+$scope.addDesc+"&needCheck="+checkValue;
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo1");
        var sNodes = treeObj.getCheckedNodes(true);
        var menuIds = '';
        var menuObjS = new Array();
        if (sNodes.length > 0) {
            menuIds+=sNodes[0].id;
            for(var i=1;i<sNodes.length;i++){
                menuIds+=','+sNodes[i].id;
            }
        }
        param+='&menuIds='+menuIds;

        base.connectService('/roles/addRoles.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("增加成功！");
                window.location="/roles/list.do";
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }

    $scope.edit=function(id){
        $("#P2").fadeIn('fast');
        var me = this;
        var param="id="+id;
        base.connectService('/roles/getRoles.do',param, function(data){
            if(data.rtnCode == "0000000"){
                var roles = data.bizData.roles;
                getMenuList('updateTreeDemo1',roles.menuIds);
                $('#updateRolesName').val(roles.name);
                $('#updateId').val(roles.id);
                $('#updateDesc').val(roles.description);
                var ckvalue=roles.needCheck;

                if(ckvalue==1){
                    $(":radio[name=updateCheck][value='1']").attr("checked","true");
                }else{
                    $(":radio[name=updateCheck][value='0']").attr("checked","true");
                }
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }

    $scope.update=function() {
        var rolesName=$('#updateRolesName').val();
        var id=$('#updateId').val();
        var description=$('#updateDesc').val();
        var needCheck= $('input[name="updateCheck"]:checked').val();
        var params="id="+id+"&name="+rolesName+"&description="+description+"&needCheck="+needCheck;
        var treeObj = $.fn.zTree.getZTreeObj("updateTreeDemo1");
        var sNodes = treeObj.getCheckedNodes(true);
        var menuIds = '';
        var menuObjS = new Array();
        if (sNodes.length > 0) {
            menuIds+=sNodes[0].id;
            for(var i=1;i<sNodes.length;i++){
                menuIds+=','+sNodes[i].id;
            }
        }
        params+='&menuIds='+menuIds;
        base.connectService('/roles/modifyRoles.do',params, function(data){
            if(data.rtnCode=="0000000"){
                alert("修改成功");
                window.location = "/roles/list.do";
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }
    $scope.delete = function (id) {
        var r = confirm("确认删除?")
        if (r == true) {
            $scope.deleteData(id);
        }
    }

    $scope.deleteData=function(id){
        var param="id="+id;
        base.connectService('/roles/deleteRoles.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("删除成功");
                window.location="/roles/list.do";
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }



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
                base.connectService('/roles/deleteAll.do', param, function (res) {
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
        base.connectService('/roles/queryRolesPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.rolesList=res.bizData.rolesList;
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

    //下拉数据 start
    $scope.showSubSystem = function (){
        $('#P1').fadeIn('fast');
        getMenuList();
    }

    //初始化操作
    var base=null;
    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.load();
    }
    init();
}
/*jquery start*/
//获取菜单数据数据
var setting = {
    check: {
        enable: true,
        chkboxType: { "Y" : "p", "N" : "ps" }
    },
    data: {
        key: {
            title: "title"
        },
        simpleData: {
            enable: true
        }
    },
    callback: {
        onCheck: onCheck
    },view: {
        showLine: false,
        showIcon: false,
        selectedMulti: false,
        dblClickExpand: false
    }
};
function onCheck(e, treeId, treeNode) {
    //count();
}
function getMenuList(id,sel){

    if(isEmpty(id)){
        id= 'treeDemo1';
    }
    var url = "/menu/queryMenuAll.do";
    var params = {};

    $.post(url,params,function(data){
        if (data.rtnCode == "0000000") {
            var treeDemo = $('#'+id);
            var nodes = data.bizData['menuList'];
            treeDemo = $.fn.zTree.init(treeDemo, setting, nodes);

            var node;
            if(!isEmpty(sel)){

                var selIds = sel.split(',');
                for(var i=0;i<selIds.length;i++){
                    node = treeDemo.getNodeByParam("id",selIds[i]);
                    treeDemo.checkNode(node, true, true);
                }
            }
            treeDemo.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
        } else {
            alert(res.msg);
        }
    });
}
//判断js对象是不是empty
function isEmpty(obj){
    if('undefined'==typeof(obj)){
        return true;
    }
    if(null==typeof(obj)){
        return true;
    }
    if('string'==typeof(obj) && obj.length==0){
        return true;
    }
    if('string'==typeof(obj) && obj.length==0){
        return true;
    }
    if(obj instanceof Array && obj.length==0){
        return true;
    }

}
/*jquery end*/