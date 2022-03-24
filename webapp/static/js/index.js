//绑定body
var bodyEle = document.body;
//绑定按钮元素
var bjt1 = document.getElementById('bjt1');
var bjt2 = document.getElementById('bjt2');
var bjt3 = document.getElementById('bjt3');
var bjt4 = document.getElementById('bjt4');
var bjt5 = document.getElementById('bjt5');
var bjt6 = document.getElementById('bjt6');

//定义背景图随机函数
var sj1 = function(){
	bodyEle.style.background = 'url(../static/img/bj1.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
var sj2 = function(){
	bodyEle.style.background = 'url(../static/img/bj2.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
var sj3 = function(){
	bodyEle.style.background = 'url(../static/img/bj3.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
var sj4 = function(){
	bodyEle.style.background = 'url(../static/img/bj4.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
var sj5 = function(){
	bodyEle.style.background = 'url(../static/img/bj5.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
var sj6 = function(){
	bodyEle.style.background = 'url(../static/img/bj6.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}

//定义随机数
function sjs(min,max){
	return Math.floor(Math.random()*(max-min+1))+1;
}


bjt1.onclick = function(){
	bodyEle.style.background = 'url(../static/img/bj1.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
bjt2.onclick = function(){
	bodyEle.style.background = 'url(../static/img/bj2.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
bjt3.onclick = function(){
	bodyEle.style.background = 'url(../static/img/bj3.jpg) no-repeat center';
	bodyEle.style.backgroundSize = '100% 100%';
}
bjt4.onclick = function(){
	bodyEle.style.background = 'url(../static/img/bj4.jpg)';
	bodyEle.style.backgroundSize = '100% 100%';
}
bjt5.onclick = function(){
	bodyEle.style.background = 'url(../static/img/bj5.jpg) no-repeat center';
	bodyEle.style.backgroundSize = '100% 100%';
}
bjt6.onclick = function(){
	bodyEle.style.background = 'url(../static/img/bj6.jpg) no-repeat center';
	bodyEle.style.backgroundSize = '100% 100%';
}








