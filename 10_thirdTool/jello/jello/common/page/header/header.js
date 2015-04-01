var header = (function(){

    var _sayHello = function(){
        console.log("hello");
    };  

    var _init = function(){
        var header = document.getElementById("header");
        header.addEventListener("mouseover",_sayHello,false);
    };  

    return {
        init : _init
    };  
})();

module.exports = header;