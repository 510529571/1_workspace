var FormValidator = require("common:widget/validate/validate.js"),
    datePicker = require("common:widget/datepicker/datepicker.js"), //注意这个日期空间，require进来之后没有返回任何东西
                                                                    //require之后，直接$('xxx').datepicker来调用即可
    btn = require("common:widget/btn/btn.js");

var page = (function(){

    var init = function(){
        
        initFormValidate(); //初始化表单验证
        initDatePicker();
        
    };

    var bindEvent = function(){
        $("#addTest2").on("change",processTestField);
        $("#addTest1").on("change",processTestField);
        $("#verifyCodeBtn").click(sendVerifyCode);
    };


    /******* page private variables *******/
    window.validateForm = null;

    /******* page private function ********/
    var initFormValidate = function(){
                
        validateForm = new FormValidator(
            'formName',

            [{
                name : 'email',                         //name为表单字段的name属性
                display : '邮箱地址',                   //display参数用于在错误提示语中显示,如:"xxx不能为空"
                rules : 'required|valid_email'          //多个rules使用|进行分割，默认自带的rule都提供错误提示语
            },{
                name : 'buyNum',
                display: '申购数',
                rules : 'required|decimal|greater_than[100]|less_than[1500000]' //校验申购金额的例子
            },{
                name : 'password',
                display: '密码',
                rules : 'required|no_blank|min_length[6]|max_length[10]'        //密码的校验可以根据自己的需求拼凑
            },{
                name : 'noRuleEle'                      //如果表单中有字段无任何校验规则，可以这样写
                                                        //如果不写，在success回调函数中不会出现在datas参数中
            },{
                name : 'telphone',
                display: '手机号码',
                rules : 'required|valid_tel_phone'      //手机号码校验（只匹配手机号码，座机什么的不行）
            },{
                name : 'verifyCode',
                display: '手机验证码',
                rules : 'required|callback_verify_code',
                posTarget : $("#verifyCodeBtn")
            },{
                name : 'phone',
                display: '电话号码',
                rules : 'required|valid_phone'          //普通的电话号码校验
            },{
                name : 'idNum',                         //rules可以是一个函数，处理后返回一个rules字符串
                display: '证件号码',                    //这个用法可以用在条件式校验，如手机验证码等
                rules : function(value){
                    var idType = $("#idType").val(),
                        rules = 'required';

                    if(idType == '1'){
                        return rules + "|valid_identity";
                    }else{
                        return rules;
                    }
                }
            },{
                name : 'dateEle',
                display: '日期',
                rules : 'required'
            },{
                name : 'customTest',
                rules : 'required|callback_custom_rule' //这里的自定义验证逻辑注意要加上callback_前缀
            },{
                name : 'posTestInput',
                display: '位置测试',
                rules : 'required',
                posTarget : $("#posTestBtn")            //有时候错误提示的位置需要定位在其他地方，可以这样做
            }],

            {
                fail : function(errors,evt){    //验证失败后的回调函数，errors包含验证失败的字段信息
                    //console.log(errors);
                    //这里可以对错误进行自定义处理
                    //如设置showError为false
                    //然后自己对这些错误进行自定义显示
                },
                success : function(datas,evt){  //验证通过后的回调函数,datas包含表单字段的json格式对象
                    //console.log(datas); 
                    alert('校验成功');
                    //这里可以使用ajax提交表单
                    //前提是autoSubmit设置为false
                },
                autoSubmit : false,             //验证通过后，是否自动提交表单(默认为true),如果想使用ajax提交表单，可以设置为false
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
            
            var verifyCodeUrl = "/wtgf/verifyCode", //这个URL在server.conf中进行模拟
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

    var initDatePicker = function(){
        $("#dateEle").datepicker({
            dateFormat : "yy-mm-dd",
            changeYear : true,
            changeMonth : true
        });
    };

    //这里是动态绑定的添加字段函数(这里其实可以用function类型的rules来完成，但是为了演示功能而使用addField）
    var processTestField = function(e){
        if($("#addTest2").get(0).checked){
            $(".addTestTarget").show();
            validateForm.addField({
                name : 'addTestInput',
                display : '动态添加测试字段',
                rules : 'required'
            });
        }else{
            $(".addTestTarget").hide();
            validateForm.deleteField('addTestInput');
        }
    };

    //发送手机验证码
    var sendVerifyCode = function(e){
        var error = validateForm.validateField('phone');
        if(error.length == 0){

            //执行发送验证码逻辑
            alert("手机验证通过，开始发送验证码");  //这里模拟一下发送验证码,实际就是发一个get的ajax

            var self = this,
                coverBtn = btn.disable(self),       //禁用按钮的实现方案是将当前按钮复制一个，然后盖在原有的按钮上
                count = 60,                         //已达到禁用的效果，因此如果想在禁用按钮后修改按钮的文字
                timer = setInterval(function(){     //就要操作盖在上面的那个按钮，btn.disable会返回这个按钮出来

                    $(coverBtn).html("已发送验证码(" + count-- + ")");      //这里就是在操作盖在上面的按钮
                                                                            //注意，如果修改后按钮大小比原来的小，会露馅
                    if(count < 0){
                        clearInterval(timer); 
                        btn.enable(self);
                    }
                },1000);
        }
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
