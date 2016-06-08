var noNullCheck = function (divId,msg,value) {

    if(value.length<1){
        $("#"+divId).html("&nbsp;&nbsp;&nbsp;"+msg);
    }else {
        $("#"+divId).html("");
    }

}
