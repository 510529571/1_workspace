var FormValidator = require("js:validate/validate.js");

var page = (function(){

    var init = function(){
        initFormValidate(); //��ʼ������֤
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
                name : 'username',                         //nameΪ���ֶε�name����
                display : '�����ַ',                   //display���������ڴ�����ʾ������ʾ,��:"xxx����Ϊ��"
                rules : 'required|valid_email'          //���rulesʹ��|���зָĬ���Դ���rule���ṩ������ʾ��
            },{
                name : 'password',
                display: '����',
                rules : 'required|no_blank|min_length[6]|max_length[10]'        //�����У����Ը����Լ�������ƴ��
            },{
                name:'verifyCode',
                display:'У����',
                rules:'required|callback_verify_code'
            }],

            {
                fail : function(errors,evt){    //��֤ʧ�ܺ�Ļص�������errors������֤ʧ�ܵ��ֶ���Ϣ
                    //console.log(errors);
                    //������ԶԴ�������Զ��崦��
                    //������showErrorΪfalse
                    //Ȼ���Լ�����Щ��������Զ�����ʾ
                },
                success : function(datas,evt){  //��֤ͨ����Ļص�����,datas�������ֶε�json��ʽ����
                    var bSubmit=false;
                    //console.log(datas);
                    //�������ʹ��ajax�ύ��
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
                                alert("�û�������");
                                bSubmit=false;
                            }else if(jsondata.flag =='2' ){
                                alert("�û�������");
                                bSubmit=false;
                            }else{
                                bSubmit=false;
                            }
                        }
                    });
                    //ǰ����autoSubmit����Ϊfalse
                    return bSubmit;
                },
                autoSubmit : true,             //��֤ͨ�����Ƿ��Զ��ύ��(Ĭ��Ϊtrue),�����ʹ��ajax�ύ������������Ϊfalse
                showError : true                //��֤ʧ�ܺ��Ƿ��Զ���ʾ����(Ĭ��Ϊtrue)
            }
        );

        //����Զ���У���߼�(��������֤���ֻ���֤���)
        validateForm.registerCallback('custom_rule',function(value){
            if(!isNaN(parseInt(value)) && parseInt(value) > 5){
                return true;
            }else{
                return false;
            }
        }).setMessage('custom_rule','���ֶα������1'); //�����Զ���У���߼���ʧ����ʾ��


        validateForm.registerCallback('verify_code',function(value){
            //����������֤�ֻ���֤���Ƿ�һ��,ע�⣺ajax��������Ϊͬ��(��async : false),�����ſ���������֤��

            var verifyCodeUrl = "/login/verifyCode", //���URL��server.conf�н���ģ��
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

        }).setMessage('verify_code','�ֻ���֤���������');
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
