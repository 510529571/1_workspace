var FormValidator = require("js:validate/validate.js");

var page = (function(){

    var init = function(){
        initFormValidate(); //初始化表单验证
    };

    var bindEvent = function(){
    };


    /******* page private variables *******/
    window.validateForm = null;

    /******* page private function ********/
    var initFormValidate = function(){

        validateForm = new FormValidator(
            'login',

            [{
                name : 'username',                         //name为表单字段的name属性
                display : '邮箱地址',                   //display参数用于在错误提示语中显示,如:"xxx不能为空"
                rules : 'required|valid_email'          //多个rules使用|进行分割，默认自带的rule都提供错误提示语
            },{
                name : 'password',
                display: '密码',
                rules : 'required|no_blank|min_length[6]|max_length[10]'        //密码的校验可以根据自己的需求拼凑
            },{
                name:'verifyCode',
                display:'校验码',
                rules:'required|callback_verify_code'
            }],

            {
                fail : function(errors,evt){    //验证失败后的回调函数，errors包含验证失败的字段信息
                    //console.log(errors);
                    //这里可以对错误进行自定义处理
                    //如设置showError为false
                    //然后自己对这些错误进行自定义显示
                },
                success : function(datas,evt){  //验证通过后的回调函数,datas包含表单字段的json格式对象
                    var bSubmit=false;
                    //console.log(datas);
                    //这里可以使用ajax提交表单
                    $.ajax({
                        type: "post",
                        url: "/login/checkUser",
                        data: datas,
                        async: false,
                        success: function (result) {
                            var jsondata= result;
                            if(jsondata.flag =='0' ){
                                bSubmit=true;
                            }else if(jsondata.flag =='1' ){
                                alert("用户名错误！");
                                bSubmit=false;
                            }else if(jsondata.flag =='2' ){
                                alert("用户名错误！");
                                bSubmit=false;
                            }else{
                                bSubmit=false;
                            }
                        }
                    });
                    //前提是autoSubmit设置为false
                    return bSubmit;
                },
                autoSubmit : true,             //验证通过后，是否自动提交表单(默认为true),如果想使用ajax提交表单，可以设置为false
                showError : true                //验证失败后，是否自动显示错误(默认为true)
            }
        );

        //添加自定义校验逻辑(可用于验证如手机验证码等)
        validateForm.registerCallback('custom_rule',function(value){
            if(!isNaN(parseInt(value)) && parseInt(value) > 5){
                return true;
            }else{
                return false;
            }
        }).setMessage('custom_rule','该字段必须大于1'); //设置自定义校验逻辑的失败提示语


        validateForm.registerCallback('verify_code',function(value){
            //发送请求验证手机验证码是否一致,注意：ajax必须设置为同步(即async : false),这样才可以正常验证表单

            var verifyCodeUrl = "/login/verifyCode", //这个URL在server.conf中进行模拟
                result = false;

            var ret = $.ajax(verifyCodeUrl,{
                type : 'GET',
                async : false,
                data : {
                    verifyCode : value
                },
                success : function(ret){
                    if(ret.errno == 0){
                        result = true;
                    }else{
                        result = false;
                    }
                }
            });

            return result;

        }).setMessage('verify_code','手机验证码输入错误');
    };


    /******* export ********/
    return {
        init : init,
        bindEvent : bindEvent
    };
})();

//run the page code
$(function(){
    page.init();
    page.bindEvent();
});
