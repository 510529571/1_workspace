var util = require("common:widget/util/util.js"); //引用widget，widget的使用方法见文档

//声明一个页面page的单实例类，并只暴露init和bindEvent两个方法(无特殊情况下都按照这样的风格编写)
var page = (function(){
    
    //处理页面的初始化(如一些页面参数、widget的初始化）
    var init = function(){
        privateFun1();
    };

    //绑定页面元素的事件
    var bindEvent = function(){
        $("#test").on('click',clickFun);
    };

    //声明页面私有变量
    var privateVar = 123;

    //声明页面私有函数
    var privateFun = function(){
        privateVar = 222;
    };

    var clickFun = function(){
        //DO SOMETHING
    };



    return {
        init : init,
        bindEvent : bindEvent
    };
})();

//dom ready后调用初始化和事件绑定
$(function(){
    page.init();
    page.bindEvent();
});

//为了更好的封装因此将页面的所有js逻辑编写在一个名为page的单实例中
//页面的js通常只包含两件事，一个是数据的初始化，另外一个是为页面元素绑定事件,因此page只暴露init和bindEvent两个函数
//由于页面主要逻辑都包含在init和bindEvent中，因此这两个函数放在最上面方便查看,其他私有的函数和变量则往下放
//init和bindEvent函数中尽量只包含调用逻辑，不要包含实现逻辑，即具体的业务代码写在私有函数中，init和bindEvent只负责调用
//
