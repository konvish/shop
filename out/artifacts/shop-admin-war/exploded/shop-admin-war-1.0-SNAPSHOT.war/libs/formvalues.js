var _strLoading = "<tr align=\"center\"><td colspan=\"10\"><img src=\"/web/images/loading.gif\"/></td></tr>";
var loadingImageSrc="/web/images/tiny-loading.gif";
function getValues(formId) {
		var formNode = $('#' + formId)[0];
		if ((!formNode) || (!formNode.tagName) || (!formNode.tagName.toLowerCase() == "form")) {
			base.alert('请指定一个正确的form节点！');
			return null;
		}
		var values = [];
		for (var i = 0; i < formNode.elements.length; i++) {
			var elm = formNode.elements[i];
			if (!elm || elm.tagName.toLowerCase() == "fieldset" || !_formFilter(elm)) {
				continue;
			}
			var name = elm.name;
			var type = elm.type.toLowerCase();
			if (type == "select-multiple") {
				for (var j = 0; j < elm.options.length; j++) {
					if (elm.options[j].selected) {
						values.push(name + "=" + encodeURIComponent(elm.options[j].value));
					}
				}
			} else if(type == "textarea"){
				values.push(name + "=" + encodeURIComponent($('#'+elm.id).val()));
			} else {
				if ($.inArray(type, ["radio", "checkbox"]) > -1) {
					if (elm.checked) {
						values.push(name + "=" + encodeURIComponent(elm.value));
					}
				} else {					
					values.push(name + "=" + encodeURIComponent(jQuery.trim(elm.value)) );					
				}
			}			
		}
		var inputs = $("input");
		for (var i = 0; i < inputs.length; i++) {
			var input = inputs[i];
			if (input.type.toLowerCase() == "image" && input.form == formNode && _formFilter(input)) {
				var name = input.name;
				alert(name + "=" + input.value);
				values.push(name + "=" + input.value);
				values.push(name + ".x=0");
				values.push(name + ".y=0");
			}
		}
		
		return values.join("&");
	}

/**
 * 表单可提交元素过滤器.
 */
 function _formFilter(/*object*/node) {
	var type = (node.type || "").toLowerCase();
//	!node.disabled &&
	return  node.name && !($.inArray(type, ["file", "submit", "image", "reset", "button"]) > -1);
}
 $(function (){
		window.tinyLoading = {
				
			    show: function( target ) {
			    	if (typeof(loadingImageSrc)=='undefined'){
			    		loadingImageSrc="";
			    	}
			    	
			        var loadingEl = $( '<div class="tiny-loading"><img class="img-load" alt="Loading..." src="'+loadingImageSrc+'"/></div>' );
			        
			        loadingEl.css({
			            display: target.css( "display" ),
			            "text-align":"center",
			            "float": target.css( "float" ),
			            position: target.css( "position" ),
			            top: target.css( "top" ),
			            left: target.css( "left" ),
			            width: target.outerWidth(),
			            height: target.outerHeight(),
			            "line-height": target.outerHeight() + "px"
			        }).insertBefore( target );
			        
			        target.hide()
			            .data( "loadingEl", loadingEl );
			    },
			    hide: function( target ) {
			        target.show()
			            .data( "loadingEl" )
			            .remove();
			    }
			};
	});
 
 

 function Base($http) {
	//连接服务器获取数据
	this.connectService = function(url, data, successCallback, errCallback,target) {

		var canShow=false;
		if(typeof target != 'undefined'&&target!=""){
			canShow=true;
			tinyLoading.show( $(target) );
		}
		//处理参数中值为"null"或者"undefined"的情况,直接替换为空
		parVals = data.split('&');
		data = "";
		for(var i=0;i<parVals.length;i++){
			data += parVals[i].replace('undefined','').replace('null','')+'&';
		}
		if ($http != undefined) {
			var p = $http({
				method : 'post',
				url : url,
				async:true,
//				params : data,
				data:data,
				headers : {			  
					'Content-Type' : 'application/x-www-form-urlencoded;charset=UTF-8'
				}
			});
			p.success(function(data, status) {
				if(canShow)
					tinyLoading.hide( $(target) );
				if (typeof successCallback === 'function')
					successCallback(data, status);
				
			});
			p.error(function(data, status) {
				if(canShow)
					tinyLoading.hide( $(target) );
				if (typeof errCallback === 'function')
					errCallback(data, status);
				
			});
		}
	}
}
 /**
  * 验证输入是否合法
  * @param inputString
  * @param strminlen
  * @param strmaxLen
  * @returns {Boolean}
  */
  function checkInputData(inputString,strminlen, strmaxLen) {
 	if (typeof inputString == 'undefined'
 			|| inputString.replace(/(^\s*)|(\s*$)/g, "") == "") {
 		return false;
 	}
 	if (typeof strminlen != 'undefined' && strminlen > -1) {
 		if (inputString.length<strminlen) {
 			return false;
 		}
 	}
 	if (typeof strmaxLen != 'undefined' && strmaxLen > -1) {
 		if (inputString.length>strmaxLen) {
 			return false;
 		}
 	}
 	return true;

 }
 function dateDiff( inputDate){ 
	    var currentDate=new Date();
	    
	    inputDate = stringToTime(inputDate); 
	    var lag2=Math.abs(currentDate - inputDate)/1000;
	    
		var min=Math.floor((lag2/60)%60);
		var hou=Math.floor(lag2/3600);
	    if(hou>0){
	    	if(hou>0&&hou<23){
	    		return new Date(inputDate).Format("hh:mm");
	    	}else{
	    		return new Date(inputDate).Format("M月d日");
	    	}
	    }else{
	    	if(min<2){
	    		return "刚刚";
	    	}else {
	    		return min+"分钟前";
	    	}
	    }
	    

	    return "";
	} 
	//字符串转成Time(dateDiff)所需方法 
	function stringToTime(string){ 
	    var f = string.split(' ', 2); 
	    var d = (f[0] ? f[0] : '').split('-', 3); 
	    var t = (f[1] ? f[1] : '').split(':', 3); 
	    return (new Date( 
	    parseInt(d[0], 10) || null, 
	    (parseInt(d[1], 10) || 1)-1, 
	    parseInt(d[2], 10) || null, 
	    parseInt(t[0], 10) || null, 
	    parseInt(t[1], 10) || null, 
	    parseInt(t[2], 10) || null 
	    )).getTime(); 
	 
	}
	Date.prototype.Format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}