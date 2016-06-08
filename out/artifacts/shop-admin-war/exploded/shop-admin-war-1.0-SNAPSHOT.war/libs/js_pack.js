
// 框架动态尺寸
function resizeWin(){
  $("#menu").height($(window).height()-54+'px');
  $("#nav").height($(window).height()-93+'px');
  $("#main").height($(window).height()-54+'px').width($(window).width()-206+'px');
  $("#main iframe").height($(window).height()-93+'px').width($(window).width()-206+'px');
  $("#timeout").height($(window).height());
};
$(window).load(function(){resizeWin();popZise()});
$(window).resize(function(){resizeWin();popZise()});


// 输入框提示
function TxtOF(o){if(o.value==o.defaultValue){o.value='';o.style.color='#333'}};
function TxtOB(o){if(o.value==''){o.value=o.defaultValue;o.style.color='#666'}};

function PassOF(o){$(o).hide();$(o).prev('input').focus();};
function PassOB(o){if(o.value==''){$(o).next('b').show();}};


// 弹窗
function popOut(i){$(i).fadeOut('fast')};
function popIn(i){$(i).fadeIn('fast')};
function popZise(){$(".pop_bg").height($(window).height() > $(document.body).height() ? $(window).height() : ($(document.body).height())+40);};


// 站点设置
$(document).ready(function(){
  $('.stop li').click(function(){$(this).siblings().removeClass('cur');$(this).addClass('cur');})
});