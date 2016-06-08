/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  User.js 2016-01-18 15:55:26 $
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
        } else if (input == "8") {
            input = "停用";
        }
        return input;
    }
});

function modelListCtrl($scope, $http) {

    $scope.add = function () {
        var sex = $('#addSex').find("option:selected").text();
        var sexInt = 0;
        if (sex == "男")
            sexInt = 1;
        else if (sex == '女')
            sexInt = 2;
        else
            sexInt = 0;
        var param = "name=" + $scope.addUserName + "&phone=" + $scope.addPhone + "&email=" + $scope.addEmail + "&sex=" + sexInt
            + "&birthday=" + $scope.addBirthday + "&address=" + $scope.addAddress + "&nation=" + $scope.addNation + "&country=" + $scope.addCountry;
        base.connectService('/user/addUser.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("增加成功！");
                window.location = "/user/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.edit = function (id) {
        $("#P2").fadeIn('fast');
        var me = this;
        var param = "id=" + id;
        base.connectService('/user/getUser.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                var user = data.bizData.user;
                $('#updateUserName').val(user.name);
                $('#updatePhone').val(user.phone);
                $('#updateEmail').val(user.email);
                $('#updateBirthday').val(user.birthday);
                $('#updateAddress').val(user.address);
                $('#updateNation').val(user.nation);
                $('#updateCountry').val(user.country);
                $('#updateId').val(user.id);
                var sex = user.sex;
                if(sex==0){
                    $('#updateSex').find("option:selected").text('其他');
                }else if(sex==1) {
                    $('#updateSex').find("option:selected").text('男');
                }
                else
                    $('#updateSex').find("option:selected").text('女');
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.update = function () {
        var userName = $('#updateUserName').val();
        var phone = $('#updatePhone').val();
        var email = $('#updateEmail').val();
        var birthday = $('#updateBirthday').val();
        var address = $('#updateAddress').val();
        var nation = $('#updateNation').val();
        var country = $('#updateCountry').val();
        var id = $('#updateId').val();

        var sex = $('#addSex').find("option:selected").text();
        var sexInt = 0;
        if (sex == "男")
            sexInt = 1;
        else if (sex == '女')
            sexInt = 2;
        else
            sexInt = 0;

        var params = "id=" + id + "&name=" + userName + "&phone=" + phone + "&email=" + email + "&sex=" + sexInt
            + "&birthday=" + birthday + "&address=" + address + "&nation=" + nation + "&country=" + country;
        base.connectService('/user/modifyUser.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                alert("修改成功");
                window.location = "/user/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.delete = function (id) {
        var param = "id=" + id;
        base.connectService('/user/deleteUser.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("删除成功");
                window.location = "/user/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
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
        base.connectService('/user/queryUserPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.userList = res.bizData.userList;
                $scope.totalPage = Math.ceil(res.bizData.totalRecord / $scope.pageSize);
                $scope.endPage = $scope.totalPage;
                $scope.totalRecord = res.bizData.totalRecord;

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
        var toPage = $("#toPage").val();
        if (toPage > 0 && toPage <= $scope.totalPage) {
            $scope.currentPage = toPage;
            $scope.load();
        }
    };

    $scope.pageSizeChange = function () {
        $scope.load();
    };

    //批量删除信息

    $scope.deleteAll = function () {
        var me = this;
        var aa = this.selected;
        if (aa.length == 0) {
            alert("请选择所要删除的信息");
            return;
        } else {
            var r = confirm("确认删除？");
            if (r == true) {
                var param = "ids=" + aa;
                <!-- param={'ids':aa};-->
                base.connectService('/user/deleteAll.do', param, function (res) {
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


    $scope.checkAll = function ($event, list) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        if (action == 'add') {
            $scope.selected = [];
            angular.forEach(list, function (obj) {
                $scope.selected.push(obj.id);
            })
        } else {
            $scope.selected = [];
        }
    }

    $scope.selected = [];
    $scope.selectedTags = [];

    var updateSelected = function (action, id, name) {
        if (action == 'add' && $scope.selected.indexOf(id) == -1) {
            $scope.selected.push(id);
            $scope.selectedTags.push(name);
        }
        if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx, 1);
            $scope.selectedTags.splice(idx, 1);
        }
    }


    $scope.updateSelection = function ($event, id) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        updateSelected(action, id, checkbox.name);
    }


    $scope.isSelected = function (id) {
        return $scope.selected.indexOf(id) >= 0;
    };

    //启用
    $scope.open = function (id) {
        var me = this;
        var param = "id=" + id;
        base.connectService('/user/open.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                alert("启用成功！");
                window.location = "/user/list.do";
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
        base.connectService('/user/out.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                alert("停用成功！");
                window.location = "/user/list.do";
            } else {
                alert(res.msg);
            }
        }, function (res) {
        });
    }


    //初始化操作
    var base = null;

    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.load();
    }

    init();
}
