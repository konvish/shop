/*
 * Copyright (c) 2013-2014, thinkjoy Inc. All Rights Reserved.
 *
 * Project Name: qky-resource
 * $Id:  Menu.js 2015-11-13 18:08:46 $
 */
/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope,$http) {

    $scope.add=function(){
        //var param="name="+ $scope.addMenuName+"&parentId="+$scope.addMenuParentId+"&nodeOrder="+$scope.addNodeOrder;
        var params = getTreeValue($('#P1 #rootNode'));
        if(params.length<1){
            alert("创建菜单目录为空");
            return;
        }
        for(var i=0; i<params.length; i++){
            //  alert( JSON.stringify(params[i].name));
            if(params[i].nodeOrder==""){
                alert("创建菜单排序为空");
                return;
            }
            else if(params[i].name==""){
                alert("创建菜单名称为空");
                return;
            }
            else if(params[i].menuUrl==""){
                alert("创建菜单地址为空");
                return
            }
        }
        var jsonMenus = JSON.stringify(params);
        var param="jsonMenus="+jsonMenus;

        base.connectService('/menu/addMenus.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("增加成功！");
                window.location="/menu/list.do";
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }
    $scope.addNode=function(id){
        $("#P1").fadeIn('fast');
        $scope.addMenuParentId=id;
    }

    $scope.edit=function(id){
        $("#P2").fadeIn('fast');
        var me = this;
        var param="id="+id;
        base.connectService('/menu/getExMenu.do',param, function(data){
            if(data.rtnCode == "0000000"){

                var menuList = data.bizData.menuList;
                var html = createTreeHtml(menuList[0],1);
                $('#P2 #rootNode').html(html);
                $("#P2").fadeIn('fast');
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }

    $scope.update=function() {
        /*var menuName=$('#updateMenuName').val();
         var id=$('#updateId').val();
         var menuParentId=$('#updateMenuParentId').val();
         var nodeOrder=$('#updateMenuNodeOrder').val();
         var params="id="+id+"&name="+menuName+"&nodeOrder="+nodeOrder+"&parentId="+menuParentId;*/
        var params = getTreeValue($('#P2 #rootNode'));
        if(params.length<1){
            alert("创建菜单目录为空");
            return;
        }
        for(var i=0; i<params.length; i++) {
            //  alert( JSON.stringify(params[i].name));
            if (params[i].nodeOrder == "") {
                alert("创建菜单排序为空");
                return;
            }
            else if (params[i].name == "") {
                alert("创建菜单名称为空");
                return;
            }
            else if (params[i].menuUrl == "") {
                alert("创建菜单地址为空");
                return
            }
        }
        var jsonMenus = JSON.stringify(params);
        var param="jsonMenus="+jsonMenus;



        base.connectService('/menu/addMenus.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("修改成功");
                window.location = "/menu/list.do";
            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
    }

    $scope.delete=function(id){
        var param="id="+id;
        base.connectService('/menu/deleteMenu.do',param, function(data){
            if(data.rtnCode=="0000000"){
                alert("删除成功");
                window.location="/menu/list.do";
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
        base.connectService('/menu/queryMenuPage.do', param, function (res) {
            if (res.rtnCode == "0000000") {
                $scope.menuList=res.bizData.menuList;
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
    $scope.preview = function(id){
        var me = this;
        var param="id="+id;
        base.connectService('/menu/getExMenu.do',param, function(data){
            if(data.rtnCode == "0000000"){
                var menuList = data.bizData.menuList;
                var html = previewHtml(menuList[0],1);
                $('#P3 #rootNode').html(html);

                /*$('#updateKnowledgeName').val(knowledge.name);
                 $('#updateKnowledgeParentId').val(knowledge.parentId);
                 $scope.updateKnowledgeGradeId=knowledge.gradeId;
                 $('#updateKnowledgeNodeOrder').val(knowledge.nodeOrder);
                 $scope.updateKnowledgeSubjectId=knowledge.subjectId;
                 $('#updateId').val(knowledge.id);*/
                //$scope.previewGradeName=knowledgeList[0].gradeName;
                //$scope.previewSubjectName=knowledgeList[0].subjectName;
                $("#P3").fadeIn('fast');

            }else{
                alert('登录超时或服务无响应，请重新登录。');
            }
        }, function(data){});
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

//jquery start
$(document).ready(function(){
    $('.creat_btn').click(function(){
        createNode($('#P1 #rootNode'),1,0);
        //$('.creat_01').slideToggle('fast');
        return false;
    });
    $(document).on("click",".creat_01",function(){
        $(this).find('.creat_01').slideToggle('fast');
        return false;
    });
    $(document).on("click",".sub_btn_crt",function(){
        var tempNode = $(this).parents('.parentNode:eq(0)');
        createNode(tempNode,2,tempNode.attr('level'));
        return false;
    });
    $(document).on("click",".btn_next03",function(){
        var tempNode = $(this).parents('.parentNode:eq(0)');
        createNode(tempNode,1,tempNode.attr('level'));
        return false;
    });
    $(document).on("click",'#rootNode .ipt01,#rootNode .ipt02',function(){
        return false;
    });


});
var knowsinde = 1;

function createNode(node,type,level){

    if(1==type||'undefined'==typeof(type)){
        level++;
    }
    var k = new change(level+"");
    var parentId = 0;
    var html='';
    html+='<div class="creat_01 parentNode" style="display: block" level="'+level+'">';
    html+='<div class="creat_box">';
    html+='<i onclick="deleteTreeNode($(this).parents(\'.parentNode:eq(0)\'))"></i>';
    html+='<span>'+(k.pri_ary())+'级目录：</span>';
    html+='<input name="nodeOrder" type="text" value="" placeholder="排序" class="ipt01 nodeOrder" title="排序">';
    html+='<input name="name" type="text" class="ipt02 name" placeholder="菜单名称" title="菜单名称" style="width:150px!important">';
    html+='<input name="menuUrl" type="text" class="ipt02 menuUrl" placeholder="菜单地址" title="菜单地址" style="width:150px!important">';
    html+='<input name="id" type="hidden" class="id" value="">';
    html+='<input name="" type="button" value="+同级目录" class="btn_crt sub_btn_crt" onclick="">';
    html+='<input name="" type="button" value="+子目录" class="btn_crt btn_next03" onclick="">';
    html+='</div>';
    html+='</div>';
    if(1==type||'undefined'==typeof(type)){
        $(node).append(html);
    }else if(2==type){
        $(node).after(html);
    }
    return false;
}

function deleteTreeNode(node){

    var params = {};
    var url = '/menu/deleteMenuByIds.do';
    var ids = getNodeIds(node);
    if(!isEmpty(ids)){
        ids = ids.substring(1);
        params.ids = ids;
    }
    $.post(url,params,function(data){
        if(data.rtnCode == "0000000"){
            $(node).remove();
        }
    });

    return false;
}
function getNodeIds(node){
    var nodeIds = getNodeId(node);
    var tempValTemp;
    var tempBoxNode;
    tempBoxNode = $(this).children('.creat_box');
    $(node).children('.creat_01').each(function(){

        var childValIds = getNodeIds(this);
        nodeIds+=childValIds;
    });
    return nodeIds;
}
function getNodeId(node){
    var tempBoxNode = $(node).children('.creat_box');
    var id = tempBoxNode.children('.id').val();
    if(isEmpty(id)){
        id ='' ;
    }else{
        id = ','+id;
    }
    return id;
}
function getTreeValue(node){
    var nodeValObj = new Array();
    var tempValTemp;
    var tempBoxNode;
    $(node).children('.creat_01').each(function(){
        tempValTemp = {};
        tempBoxNode = $(this).children('.creat_box');
        tempValTemp.nodeOrder = tempBoxNode.children('.nodeOrder').val();
        tempValTemp.name = tempBoxNode.children('.name').val();
        tempValTemp.menuUrl = tempBoxNode.children('.menuUrl').val();
        tempValTemp.nodeLevel = $(this).attr('level');
        var id = tempBoxNode.children('.id').val();
        if(!isEmpty(id)){
            tempValTemp.id = id ;
        }
        var childValObjs = getTreeValue(this);
        tempValTemp.menuList = childValObjs;
        //tempValTemp.gradeId=gradeId;
        //tempValTemp.subjectId=subjectId;
        nodeValObj.push(tempValTemp);
    });
    return nodeValObj;
}
function updateInitTree(){
    var url = "";
    var params = {};
    $.post(url,params,function(data){
        var results = new Array();
        var html = '';
        for(var i in results){
            html+=createTreeHtml(results[i]);
        }
        $('#P2 #rootNode').append(html);
    });
}
function createTreeHtml(obj,level){
    var k = new change(level+"");
    var html='';
    html+='<div class="creat_01 parentNode" style="display: block" parent_id=""  level="'+level+'">';
    html+='<div class="creat_box">';
    html+='<i onclick="deleteTreeNode($(this).parents(\'.parentNode:eq(0)\'))"></i>';
    html+='<span>  '+(k.pri_ary())+'级目录：</span>';
    html+='<input name="nodeOrder" type="text" class="ipt01 nodeOrder" value="'+getEmptyDrfault(obj.nodeOrder,'')+'" title="排序">';
    html+='<input name="name" type="text" class="ipt02 name" value="'+getEmptyDrfault(obj.name,'')+'" style="width:150px!important" title="菜单名称">';
    html+='<input name="menuUrl" type="text" class="ipt02 menuUrl" value="'+getEmptyDrfault(obj.menuUrl,'')+'" style="width:150px!important" title="菜单地址">';
    html+='<input name="id" type="hidden" class="id" value="'+obj.id+'">';
    html+='<input name="" type="button" value="+同级目录" class="btn_crt sub_btn_crt" onclick="">';
    html+='<input name="" type="button" value="+子目录" class="btn_crt btn_next03" onclick="">';
    html+='</div>';
    if(!isEmpty(obj.menuList)){
        for(var i in obj.menuList){
            html+=createTreeHtml(obj.menuList[i],level+1);
        }
    }
    html+='</div>';
    return html;
}
function previewHtml(obj,level){
    var k = new change(level+"");
    var html='';
    html+='<div class="creat_01 parentNode" style="display: block" parent_id=""  level="'+level+'">';
    html+='<div class="creat_box">';
    //html+='<i onclick="deleteTreeNode($(this).parents(\'.parentNode:eq(0)\'))"></i>';
    html+='<span>';/**'+(k.pri_ary())+'级目录：**/
    html+='</span>';
    html+=obj.name;
    html+='</div>';
    if(!isEmpty(obj.menuList)){
        for(var i in obj.menuList){
            html+=previewHtml(obj.menuList[i],level+1);
        }
    }
    html+='</div>';
    return html;

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
    return false;
}
function getEmptyDrfault(obj,emptyVal){
    if(isEmpty(obj)){
        return emptyVal;
    }
    return obj;
}
//jquery end