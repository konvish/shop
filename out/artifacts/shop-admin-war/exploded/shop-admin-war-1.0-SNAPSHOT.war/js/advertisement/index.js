/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: shop
 * $Id:  Advertisement.js 2016-02-24 16:58:19 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function ($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});
function modelListCtrl($scope, $http) {

    $scope.add = function () {
        if($scope.adPic.length<1){
            alert("请上传图片");
            return;
        }
        var param = "name=" + $scope.addAdvertisementName+"&adPic="+$scope.adPic+"&picLink="+$scope.addPicLink+"&categoryId="+$scope.addCategoryName;
        base.connectService('/advertisement/addAdvertisement.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("增加成功！");
                window.location = "/advertisement/list.do";
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
        base.connectService('/advertisement/getAdvertisement.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                var advertisement = data.bizData.advertisement;
                $('#updateAdvertisementName').val(advertisement.name);
                $('#updateCategoryName').val(advertisement.categoryId);
                $('#imgUrl6').html('<img src="' + advertisement.adPic + '" height="100" width="160" />');
                $('#updatePicLink').val(advertisement.picLink);
                $('#updateId').val(advertisement.id);
                $scope.adPic = advertisement.adPic;
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.update = function () {
        var advertisementName = $('#updateAdvertisementName').val();
        var updateCategoryName = $('#updateCategoryName').val();
        var updatePicLink = $('#updatePicLink').val();
        var id = $('#updateId').val();
        var params = "id=" + id + "&name=" + advertisementName+"&adPic="+$scope.adPic+"&picLink="+updatePicLink+"&categoryId="+updateCategoryName;
        base.connectService('/advertisement/modifyAdvertisement.do', params, function (data) {
            if (data.rtnCode == "0000000") {
                alert("修改成功");
                window.location = "/advertisement/list.do";
            } else {
                alert(data.msg);
            }
        }, function (data) {
        });
    }

    $scope.delete = function (id) {
        var param = "id=" + id;
        base.connectService('/advertisement/deleteAdvertisement.do', param, function (data) {
            if (data.rtnCode == "0000000") {
                alert("删除成功");
                window.location = "/advertisement/list.do";
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
        base.connectService('/advertisement/queryAdvertisementPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.advertisementList = res.bizData.advertisementList;
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
                base.connectService('/advertisement/deleteAll.do', param, function (res) {
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
    };


    $scope.updateSelection = function ($event, id) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        updateSelected(action, id, checkbox.name);
    };


    $scope.isSelected = function (id) {
        return $scope.selected.indexOf(id) >= 0;
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
            'uploadLimit': 1, //最多上传数
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
                        $scope.adPic = data.bizData.uploadFile.relativePath;
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

    $scope.addAd = function () {
        $('#P1').fadeIn('fast');
        $scope.adPic = '';
    };

    $scope.quit = function (type) {
        if (type == 'add')
            popOut('#P1');
        else
            popOut('#P2');
        $scope.adPic = '';
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

    //初始化操作
    var base = null;

    function init() {
        //生成base类实例
        base = new Base($http);
        $scope.uploadfly("uploadify5");
        $scope.uploadfly("uploadify6");
        $scope.load();
    }

    init();
}
