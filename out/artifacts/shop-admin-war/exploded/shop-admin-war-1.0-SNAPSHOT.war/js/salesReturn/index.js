/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  SalesReturn.js 2016-01-18 15:55:25 $
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
            input = "未处理";
        } else if (input == "8") {
            input = "失败";
        } else if (input == "6")
            input = "成功";
        return input;
    }
});

function modelListCtrl($scope, $http) {

    $scope.add = function () {
        var param = "name=" + $scope.addSalesReturnName;
        base.connectService('/salesReturn/addSalesReturn.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("增加成功！");
                window.location = "/salesReturn/list.do";
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
        base.connectService('/salesReturn/getSalesReturn.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                var salesReturn = data.bizData.salesReturn;
                $('#updateSalesReturnName').val(salesReturn.name);
                $('#updateId').val(salesReturn.id);
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.modify = function (id) {
        var params = "id=" + id + "&status=6";
        base.connectService('/salesReturn/modifySalesReturn.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                alert("修改成功");
                window.location = "/salesReturn/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    };

    $scope.update = function () {
        var salesReturnName = $('#updateSalesReturnName').val();
        var id = $('#updateId').val();

        var params = "id=" + id + "&name=" + salesReturnName;
        base.connectService('/salesReturn/modifySalesReturn.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                alert("修改成功");
                window.location = "/salesReturn/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.delete = function (id, status) {
        if (status == '6') {
            alert("已成功不能操作");
            return;
        }
        var param = "id=" + id;
        base.connectService('/salesReturn/deleteSalesReturn.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("拒绝成功");
                window.location = "/salesReturn/list.do";
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
    $scope.saleType = -1;
    $scope.targetPage = $scope.currentPage;

    $scope.load = function () {
        var searchName = $("#searchName").val();
        if (searchName == '请输入关键字') {
            searchName = '';
        }
        $scope.targetPage = $scope.currentPage;
        var param = "currentPage=" + $scope.currentPage + "&pageSize=" + $scope.pageSize + "&name=" + searchName + "&type=" + $scope.saleType;
        base.connectService('/salesReturn/querySalesReturnPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.salesReturnList = res.bizData.salesReturnList;
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

    $scope.deleteAll = function (status) {
        var me = this;
        var aa = this.selected;
        if (aa.length == 0) {
            alert("请选择所要操作的信息");
            return;
        } else {
            var r = confirm("确认操作？");
            if (r == true) {
                var param = "ids=" + aa + "&status=" + status;
                <!-- param={'ids':aa};-->
                base.connectService('/salesReturn/deleteAll.do', param, function (res) {
                    if (res.rtnCode == "0000000") {
                        alert("操作信息成功！");
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

    $scope.PStatus = false;

    $scope.show = function (SType) {
        $scope.saleType = SType;
        $scope.load();
        if (SType == '0')
            $scope.PStatus = true;
        else
            $scope.PStatus = false;
    };


    //初始化操作
    var base = null;

    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.load();
    }

    init();
}
