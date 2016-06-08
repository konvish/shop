var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
    $interpolateProvider.startSymbol('[[');
    $interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope, $http) {

    //提交
    $scope.sub=function(){
        var account=$("#account").val();
        var password=$("#password").val();
        var code = $('#randCode').val();
        if(account==""){
            alert("请输入账号");
            return false;
        }else if(password==""){
            alert("请输入密码");
            return false;
        }else if(code ==""){
            alert("验证码错误")
            return false;
        }
        var params="account="+account+"&password="+password+"&code="+code;
		//alert(params);

        base.connectService('/bgLogin.do',params, function(data){
            if(data.bizData.adminList!=null){
                var admin=data.bizData.adminList.name;
                var storeName=data.bizData.adminList.storeName;
                var storeId = data.bizData.adminList.storeId;
                $.cookie("admin",admin);
                window.localStorage.setItem("storeName",storeName);
                window.localStorage.setItem("storeId",storeId);
               window.location=data.bizData.url;

            }else{
                alert(data.bizData.message);
            }

        }, function(data){});
     // window.location="/html/index.html";
    };

    //初始化操作
    var base=null;
    function init() {
        // 生成base类实例
        base = new Base($http);
        document.onkeydown=keyDown;
    }

    function keyDown(e){
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
        if (code == 13) {
            $scope.sub();//具体处理函数
        }
    }



    init();

}

//点击刷新验证码
var refreshRand = function() {
    //var rand =img.src;
    var rand = document.getElementById("img");
    rand.src = 'rand/code.do?code=' + Math.random();
};

