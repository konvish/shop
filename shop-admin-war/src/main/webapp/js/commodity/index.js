/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Commodity.js 2016-01-18 15:55:24 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function ($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});

modelApp.filter('isSelf', function () {
    return function (input) {
        if (input == undefined) {
            input = "自营";
        }
        return input;
    }
});

function modelListCtrl($scope, $http) {

    $scope.add = function () {
        var param = "name=" + $scope.addCommodityName + "&storeId=" + $scope.storeId
            + "&typeId" + $scope.addThirdType + "&price=" + $scope.addPrice
            + "&brand=" + $scope.addBrand + "&description=" + $scope.addDescription
            + "&weight=" + $scope.addWeight + "&number=" + $scope.addNumber
            + "&production=" + $scope.addProduction + "&remark=" + $scope.addRemark
            + "&pic=" + $scope.pic + "&datailPic=" + $scope.datailPic;
        base.connectService('/commodity/addCommodity.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("增加成功！");
                $scope.pic = '';
                $scope.datailPic = '';
                window.location = "/commodity/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.edit = function (id) {
        var me = this;
        var param = "id=" + id;
        base.connectService('/commodity/getCommodity.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                var commodity = data.bizData.commodity;
                $('#updateCommodityName').val(commodity.name);
                $('#updateCategoryName').val(commodity.categoryName);
                $('#updatePrice').val(commodity.price);
                $('#updateBrand').val(commodity.brand);
                $('#updateDescription').val(commodity.description);
                $('#updateWeight').val(commodity.weight);
                $('#updateNumber').val(commodity.number);
                $('#updateProduction').val(commodity.production);
                $('#updateRemark').val(commodity.remark);
                $scope.pic=commodity.pic;
                $scope.datailPic=commodity.datailPic;
                $('#updateId').val(commodity.id);
                queryCategoryListById(commodity.typeId);
                $scope.thirdType = commodity.typeId;
                $("#P2").fadeIn('fast');
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    };

    function queryCategoryListById(categoryId) {
        var param = "categoryId=" + categoryId;
        base.connectService('/category/queryCategoryListById.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                $scope.categoryList = data.bizData.categoryList;
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.update = function () {
        var id = $('#updateId').val();
        var updateCommodityName = $('#updateCommodityName').val();
        var thirdType = $scope.thirdType;
        var updatePrice = $('#updatePrice').val();
        var updateBrand = $('#updateBrand').val();
        var updateDescription = $('#updateDescription').val();
        var updateWeight = $('#updateWeight').val();
        var updateNumber = $('#updateNumber').val();
        var updateProduction = $('#updateProduction').val();
        var updateRemark = $('#updateRemark').val();
        var params = "id=" + id + "&name=" + updateCommodityName
            + "&typeId=" + thirdType + "&price=" + updatePrice
            + "&brand=" + updateBrand + "&description=" + updateDescription
            + "&weight=" + updateWeight + "&number=" + updateNumber
            + "&production=" + updateProduction + "&remark=" + updateRemark;
        if ($scope.pic !='' || $scope.pic != undefined)
            params+="&pic=" + $scope.pic;
        if ($scope.datailPic !='' || $scope.pic != undefined)
            params+= "&datailPic=" + $scope.datailPic;
        base.connectService('/commodity/modifyCommodity.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                alert("修改成功");
                $scope.pic = '';
                $scope.datailPic = '';
                window.location = "/commodity/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    };

    $scope.delete = function (id) {
        var param = "id=" + id;
        base.connectService('/commodity/deleteCommodity.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("删除成功");
                window.location = "/commodity/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    };

    //自营查询用的
    $scope.isTrue = -1;
    $scope.isSelfAll = function (is) {
        $scope.isTrue = is;
        $scope.load();
    };

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
        var param = "currentPage=" + $scope.currentPage + "&pageSize=" + $scope.pageSize + "&name=" + searchName + "&storeId=" + $scope.storeId + "&is=" + $scope.isTrue;
        base.connectService('/commodity/queryCommodityPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.commodityList = res.bizData.commodityList;
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


    //###################### 上传图片   ############
    $scope.uploadfly = function (id) {
        var imgUrl = "imgUrl" + id.substring(9);
        $("#" + id).uploadify({
            'swf': '/libs/uploadify/uploadify.swf',
            'uploader': 'http://localhost:8080/upload/upload.do?type=' + id.substring(9) + '&storeId=' + $scope.storeId,
            'cancelImg': '/libs/uploadify/uploadify-cancel.png',
            'fileObjName': 'file', //后台取值一致
            'queueID': id, //与html的Div.id对应
            'method': 'post',
            'auto': true,
            'width': 50,
            'height': 20,
            'multi': true, //允许多个文件
            'queueSizeLimit': 999,
            'uploadLimit': 5,
            'itemTemplate': false,
            'buttonText': '上传图片',
            'fileTypeExts': '*.jpg;*.png;',
            'fileSizeLimit': '1MB',
            'fileTypeDesc': '可选择图片类型',
            'removeCompleted': 'true',
            'onUploadSuccess': function (file, data, response) {//每成功触发一次
                var paramUpload = '';
                base.connectService('/upload/getUploadMsg.do', paramUpload, function (data) {
                    if (data.rtnCode == "0000000") {
                        $("#" + imgUrl).html('<img src="' + data.bizData.uploadFile.relativePath + '" height="100" width="160" />')
                        if (id.substring(9) == "1" || id.substring(9) == "3") {
                            $scope.pic += data.bizData.uploadFile.relativePath + ",";
                        }
                        if (id.substring(9) == "2" || id.substring(9) == "4")
                            $scope.datailPic += data.bizData.uploadFile.relativePath + ",";
                    } else {
                        alert(data.msg);
                    }
                }, function (data) {
                });
            },
            //返回一个错误，选择文件的时候触发
            'onSelectError': function (file, errorCode, errorMsg) {
                switch (errorCode) {
                    case -100:
                        alert("图片大小超出系统限制的大小！");
                        break;
                    case -110:
                        alert("图片大小异常！");
                        break;
                    case -120:
                        alert("图片类型不正确！");
                        break;
                    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                        alert("上传的文件数量已经超出系统限制");
                        break;
                }
            },
        })
    };

    $scope.addCommodity = function () {
        $('#P1').fadeIn('fast');
        $scope.pic = '';
        $scope.datailPic = '';
    };

    $scope.quit = function (type) {
        if (type == 'add')
            popOut('#P1');
        else
            popOut('#P2');
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
                base.connectService('/menu/deleteAll.do', param, function (res) {
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

    //查询商品类别
    $scope.queryCategoryList = function (parentId, nodeLevel) {
        var params = "parentId=" + parentId + "&nodeLevel=" + nodeLevel;
        base.connectService('/category/queryCategoryList.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                if (nodeLevel == '1') {
                    $scope.firstCategoryList = data.bizData.categoryList;
                } else if (nodeLevel == '2') {
                    $scope.secondCategoryList = data.bizData.categoryList;
                } else
                    $scope.thirdCategoryList = data.bizData.categoryList;

            } else {
                alert("查询出错");
            }
        }, function (data) {
        });
    };

    //初始化操作
    var base = null;

    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.storeName = window.localStorage.getItem("storeName");
        $scope.storeId = window.localStorage.getItem("storeId");
        $scope.addStoreName = $scope.storeName;
        $scope.updateStoreName = $scope.storeName;
        $scope.queryCategoryList('', '1');
        $scope.uploadfly("uploadify1");
        $scope.uploadfly("uploadify2");
        $scope.uploadfly("uploadify3");
        $scope.uploadfly("uploadify4");
        $scope.load();
    }

    init();
}
