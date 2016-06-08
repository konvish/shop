/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: qky-resource
 * $Id:  User.js 2015-11-13 18:08:47 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function ($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});

modelApp.filter('isStatus', function () {
    return function (input) {
        if (input == "0") {
            input = "正常";
        } else if (input == "1") {
            input = "停用";
        }
        return input;
    }
});

function modelListCtrl($scope, $http) {

    function queryRoleList() {
        var param = "";
        base.connectService('/roles/queryRolesAll.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                $scope.rolesList = data.bizData.rolesList;
            } else {
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function (data) {
        });
    }

    $scope.checkData = function (op, keys, names) {
        var keyArr = keys.split(",");
        var nameArr = names.split(",");
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

        if (op == "update") {
            $scope.update();
        } else {
            $scope.add();
        }
    }

    $scope.add = function () {
        var storeName = $('#addStore').find("option:selected").text();
        var param = '';
        if(storeName!='自营'){
            param="name=" + $scope.addAdminName + "&account=" + $scope.addAccount
            + "&password=" + $scope.addPassword + "&roleId=" + $scope.addRolesId
            + "&telephone=" + $scope.addTelephone + "&email=" + $scope.addEmail+"&storeId="+$scope.addStore+"&storeName="+storeName;
        }else
            param="name=" + $scope.addAdminName + "&account=" + $scope.addAccount
                + "&password=" + $scope.addPassword + "&roleId=" + $scope.addRolesId
                + "&telephone=" + $scope.addTelephone + "&email=" + $scope.addEmail+"&storeId=0"+"&storeName="+storeName;
        base.connectService('/admin/addAdmin.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("增加成功！");
                window.location = "/admin/list.do";
            } else {
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function (data) {
        });
    }
    //启用
    $scope.open = function (id) {
        var me = this;
        var param = "id=" + id;
        base.connectService('/admin/open.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                alert("启用成功！");
                window.location = "/admin/list.do";
            } else {
                alert(res.msg);
            }
        }, function (res) {
        });
    }

    //停用
    $scope.out = function (id) {
        var me = this;
        var param = "id=" + id;
        base.connectService('/admin/out.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                alert("停用成功！");
                window.location = "/admin/list.do";
            } else {
                alert(res.msg);
            }
        }, function (res) {
        });
    }

    $scope.edit = function (id) {
        $("#P2").fadeIn('fast');
        var me = this;
        var param = "id=" + id;
        base.connectService('/admin/getAdmin.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                var admin = data.bizData.admin;
                $('#updateAdminName').val(admin.name);
                $('#updateId').val(admin.id);
                $('#updateAccount').val(admin.account);
                //$('#updatePassword').val();
                //$('#updateRolesId').val(user.roleId);
                $scope.updateRolesId = admin.roleId;
                $('#updateTelephone').val(admin.telephone);
                $('#updateEmail').val(admin.email);
            } else {
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function (data) {
        });
    }

    $scope.update = function () {
        var adminName = $('#updateAdminName').val();
        var id = $('#updateId').val();
        var account = $('#updateAccount').val();
        var rolesId = $scope.updateRolesId;
        var tel = $('#updateTelephone').val();
        var email = $('#updateEmail').val();
        var params = "id=" + id + "&name=" + adminName + "&account=" + account + ""
            + "&roleId=" + rolesId
            + "&telphone=" + tel + "&email=" + email;
        base.connectService('/admin/modifyAdmin.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                alert("修改成功");
                window.location = "/admin/list.do";
            } else {
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function (data) {
        });
    }

    $scope.changePasswordui = function(){
        var acount = 0;
        $("input:checkbox:checked").each(
            function(){
                acount++;
            }
        )
        if(acount==0){
            alert("请选择您要修改密码的用户");
            return;
        }
        else $('#P3').fadeIn('fast');
    }

    $scope.changePassword=function() {
        var newPassword = $('#newPassword').val();
        var confirmPassword = $('#confirmPassword').val();

        if(newPassword.length<6 || newPassword.length>12){
            alert("新密码不符合要求，请重新输入");
            $("#newPassword").focus();
            return;
        }

        if(newPassword!=confirmPassword){
            alert("对不起，密码不一致");
            $("#confirmPassword").focus();
            return;
        }

        var param = "newPassword="+newPassword;
        var acount = 0;
        $("input:checkbox:checked").each(
            function(){
                var a = $(this).val();
                param += "&adminId="+ a ;
                acount++;
            }
        )
        if(acount==0){
            alert("请选择您要修改密码的用户");
            return;
        }

        base.connectService('/admin/changePassword.do',param,function(data){
            if(data.rtnCode=="0000000"){
                alert("修改密码成功");
                window.location="/admin/list.do";
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        },function(data){

        })
    }

    $scope.blurcomfirm = function(){
        var password = $("#newPassword").val();
        var confirm = $("#confirmPassword").val();
        if(password!=confirm){
            $("#confirmPasswordTip").show();
        }else{
            $("#confirmPasswordTip").hide();
        }

    }

    $scope.blur = function(){
        var password = $("#newPassword").val();
        if(password.length<6 || password.length>12){
            $("#newPasswordTip").show();
        }else{
            $("#newPasswordTip").hide();
        }
    }

    $scope.keyup = function ($event){
        var password = $("#newPassword").val();
        var Mcolor,Wcolor,Scolor,Color_Html;
        var m=0;
        var Modes=0;
        for(i=0; i<password.length; i++){
            var charType=0;
            var t=password.charCodeAt(i);

            if(t>=48 && t <=57){charType=1;}
            else if(t>=65 && t <=90){charType=2;}
            else if(t>=97 && t <=122){charType=4;}
            else{charType=4;}
            Modes |= charType;
        }
        for(i=0;i<4;i++){
            if(Modes & 1){m++;}
            Modes>>>=1;
        }
        if(password.length<=4){m=1;}
        if(password.length<=0){m=0;}
        switch(m){
            case 1 :
                $("#code_pre01").css({
                    "width":"25%",
                    "height":"25px"
                });
                $("#code_pre02").css("width","0%");
                $("#code_pre03").css("width","0%");
                break;
            case 2 :
                $("#code_pre01").css({
                    "width":"25%",
                    "height":"25px"
                });
                $("#code_pre02").css({
                    "width":"25%",
                    "height":"25px"
                });
                $("#code_pre03").css("width","0%");
                break;
            case 3 :
                $("#code_pre01").css({
                    "width":"25%",
                    "height":"25px"
                });
                $("#code_pre02").css({
                    "width":"25%",
                    "height":"25px"
                });
                $("#code_pre03").css({
                    "width":"25%",
                    "height":"25px"
                });
                break;
            default :
                $("#code_pre01").css("width","0%");
                $("#code_pre02").css("width","0%");
                $("#code_pre03").css("width","0%");
                break;
        }
    }

    $scope.delete = function (id) {
        var r = confirm("确认删除?")
        if (r == true) {
            $scope.deleteData(id);
        }
    }
    $scope.deleteData = function (id) {
        var param = "id=" + id;
        base.connectService('/admin/deleteAdmin.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("删除成功");
                window.location = "/admin/list.do";
            } else {
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function (data) {
        });
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
                base.connectService('/admin/deleteAll.do', param, function (res) {
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
        var searchName = $("#searchName").val();
        if (searchName == '请输入关键字') {
            searchName = '';
        }
        $scope.targetPage = $scope.currentPage;
        var param = "currentPage=" + $scope.currentPage + "&pageSize=" + $scope.pageSize + "&name=" + searchName;
        base.connectService('/admin/queryAdminPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.adminList = res.bizData.adminList;
                $scope.totalPage = Math.ceil(res.bizData.totalRecord / $scope.pageSize);
                $scope.endPage = $scope.totalPage;
                $scope.totalRecord = res.bizData.totalRecord;

                var users = res.bizData.adminList;
                var status = null;
                for (var i = 0; i < users.length; i++) {
                    status = users[i].status;
                    if (status == "0") {
                        users[i].openShowType = 'none';
                        users[i].outShowType = '';
                        /* $("#out").attr("disabled",true);
                         alert("正常");
                         $("#out").hide();*/
                    } else {
                        users[i].openShowType = '';
                        users[i].outShowType = 'none';
                        /*    alert("禁用");
                         $("#open").attr("disabled",true);
                         $("#open").hide();*/
                    }
                    /*   users[i].openShowType = 'none';
                     users[i].outShowType = '';*/
                }

                $("#open").attr("disabled", true);
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

    var searchStore = function(storeName){
        var param = "name="+storeName+"&pageSize=100"+"&currentPage=1";
        base.connectService('/store/queryStorePage.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                $scope.storeList = data.bizData.storeList;
            } else {
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function (data) {
        });
    };


    //初始化操作
    var base = null;

    function init() {
        //生成base类实例
        base = new Base($http);
        queryRoleList();
        searchStore('');
        $scope.load();
    }

    init();
}
