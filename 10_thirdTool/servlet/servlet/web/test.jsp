<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>JS分页Class </title>
    <style type="text/css">
        a {
            margin: 3px;
            text-decoration: none;
        }
    </style>
    <script type="text/javascript"
            src="https://trade.gffunds.com.cn/static/common/js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            var pageDiv1=new jsPage("list","all","p1","3");
            pageMethod.call(pageDiv1);

        })//$

        function pageMethod(){
            var obj=this;
            obj.resetTotal();
            obj.reloadpage("1",obj.list_class);
            obj.page(); //生成页码
            ready2go.call(obj);
        }

        function ready2go(){
            var obj=this;
            $("#"+obj.page_obj_id+" a").live("click",function(){ //点击页码的时候跳到相应页
                obj.target_p=parseInt($(this).attr("p"));
                gotopage.call(obj,obj.target_p);
            })
        }

        function showClass(list,x,pDiv,pSize){
            var pObj=new jsPage(list,x,pDiv,pSize);
            pageMethod.call(pObj);
        }

        function jsPage(list_id,list_class,page_obj_id,pagesize){
            // list_id 结果集UL的id
            // list_class 要显示的类别
            // page_id 存放页码的id
// pagesize 每页显示多少条
            this.list_obj_id=list_id;
            this.list_Obj=$("#"+list_id);
            this.li=$("#"+list_id+" li");
            this.li.hide();
            this.page_obj_id=page_obj_id;
            this.page_obj=$("#"+page_obj_id); //存放页码的div
            this.list_class=list_class; // 类别
            if(list_class=="all"){
                this.results=this.li.length; // 总记录数等于所有记录
            }else{
                this.results=$("#"+list_id+" li."+list_class).length; // 总记录数等于指定类别的li数
            }

            this.totalpage; // 总页数
            this.pagesize=pagesize; //每页记录数
            this.cpage=1; //当前页,默认显示第一页
            this.count;
            this.target_p;
            this.curcount;
            this.outstr= ""; // 输出页码html
        }//jsPage

        function gotopage(target){
            this.cpage = target;        //把页面计数定位到第几页
            this.page();
            this.reloadpage(target,this.list_class);
        }

        jsPage.prototype.reloadpage=function(p,resultType){
            this.li.hide();
            for(var i=this.pagesize*p-this.pagesize;i<this.pagesize*p;i++){
                if(resultType=="all"){
                    this.li.eq(i).show();
                }else{
                    $("#"+this.list_obj_id+" li."+resultType).eq(i).show();
                }
            }
        }
        jsPage.prototype.resetTotal=function(){
            if(this.results==0){
                this.totalpage=0;
                this.cpage=0;
            }else if(this.results<=this.pagesize){
                this.totalpage=1;
            }else if(parseInt(this.results/this.pagesize)==1){
                this.totalpage=2;
            }else if(parseInt(this.results/this.pagesize)>1 && this.results%this.pagesize==0){
                this.totalpage=this.results/this.pagesize;
            }else{
                this.totalpage=parseInt(this.results/this.pagesize)+1;
            }
        }//resetTotal()

        jsPage.prototype.page=function(){
            if(this.totalpage<=10){        //总页数小于十页   页码以十页为单位
                for (this.count=1;this.count<=this.totalpage;this.count++) {
                    if(this.count!=this.cpage) {
                        this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
                    }else{
                        this.outstr = this.outstr + "<span class='current' >"+this.count+"</span>";
                    }
                }
            }
            if(this.totalpage>10){        //总页数大于十页
                if(parseInt((this.cpage-1)/10) == 0) {
                    for (this.count=1;this.count<=10;this.count++) {
                        if(this.count!=this.cpage) {
                            this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
                        }else{
                            this.outstr = this.outstr + "<span class='current'>"+this.count+"</span>";
                        }
                    }
                    this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >»</a>";
                }else if(parseInt((this.cpage-1)/10) == parseInt(this.totalpage/10)){
                    this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+(parseInt((this.cpage-1)/10)*10)+"' >«<\/a>";
                    for (this.count=parseInt(this.totalpage/10)*10+1;this.count<=this.totalpage;this.count++)   {
                        if(this.count!=this.cpage) {
                            this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
                        }else{
                            this.outstr = this.outstr + "<span class='current'>"+this.count+"</span>";
                        }
                    }
                } else {
                    var lastP;
                    this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+(parseInt((this.cpage-1)/10)*10)+"' >«<\/a>";
                    for (this.count=parseInt((this.cpage-1)/10)*10+1;this.count<=parseInt((this.cpage-1)/10)*10+10;this.count++){
                        if(this.count!=this.cpage) {
                            this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
                        }else{
                            this.outstr = this.outstr + "<span class='current'>"+this.count+"</span>";
                        }
                        if(this.count==this.totalpage){
                            lastP="";
                        }else{
                            lastP="<a href='javascript:void(0)' p='"+(this.count+1)+"' >»</a>";
                        }
                    }
                    this.outstr = this.outstr + lastP;
                }
            }
            document.getElementById(this.page_obj_id).innerHTML = "<div><span id='info'>共"+this.totalpage+"页 第"+this.cpage+"页<\/span>" + this.outstr + "<\/div>";
            this.outstr = "";
        }
    </script>


</head>

<body>
<a onclick="showClass('list','mm','p1','3')" href="#">美女</a>
<a onclick="showClass('list','gg','p1','3')" href="#">帅哥</a>
<a onclick="showClass('list','all','p1','3')" href="#">全部</a>
<br />

<ul id="list">
    <li class="mm">
        美女A
    </li>
    <li class="gg">
        帅哥A
    </li>
    <li class="mm">
        美女B
    </li>
    <li class="gg">
        帅哥B
    </li>
    <li class="mm">
        美女C
    </li>
    <li class="gg">
        帅哥C
    </li>
    <li class="mm">
        美女D
    </li>
    <li class="gg">
        帅哥D
    </li>
    <li class="mm">
        美女E
    </li>
    <li class="gg">
        帅哥E
    </li>
    <li class="mm">
        美女F
    </li>
    <li class="gg">
        帅哥F
    </li>
    <li class="mm">
        美女G
    </li>
    <li class="gg">
        帅哥G
    </li>
    <li class="mm">
        美女H
    </li>
    <li class="gg">
        帅哥H
    </li>
    <li class="mm">
        美女A
    </li>
    <li class="gg">
        帅哥A
    </li>
    <li class="mm">
        美女B
    </li>
    <li class="gg">
        帅哥B
    </li>
    <li class="mm">
        美女C
    </li>
    <li class="gg">
        帅哥C
    </li>
    <li class="mm">
        美女D
    </li>
    <li class="gg">
        帅哥D
    </li>
    <li class="mm">
        美女E
    </li>
    <li class="gg">
        帅哥E
    </li>
    <li class="mm">
        美女F
    </li>
    <li class="gg">
        帅哥F
    </li>
    <li class="mm">
        美女G
    </li>
    <li class="gg">
        帅哥G
    </li>
    <li class="mm">
        美女H
    </li>
    <li class="gg">
        帅哥H
    </li>
    <li class="mm">
        美女A
    </li>
    <li class="gg">
        帅哥A
    </li>
    <li class="mm">
        美女B
    </li>
    <li class="gg">
        帅哥B
    </li>
    <li class="mm">
        美女C
    </li>
    <li class="gg">
        帅哥C
    </li>
    <li class="mm">
        美女D
    </li>
    <li class="gg">
        帅哥D
    </li>
    <li class="mm">
        美女E
    </li>
    <li class="gg">
        帅哥E
    </li>
    <li class="mm">
        美女F
    </li>
    <li class="gg">
        帅哥F
    </li>
    <li class="mm">
        美女G
    </li>
    <li class="gg">
        帅哥G
    </li>
    <li class="mm">
        美女H
    </li>
    <li class="gg">
        帅哥H
    </li>
    <li class="mm">
        美女A
    </li>
    <li class="gg">
        帅哥A
    </li>
    <li class="mm">
        美女B
    </li>
    <li class="gg">
        帅哥B
    </li>
    <li class="mm">
        美女C
    </li>
    <li class="gg">
        帅哥C
    </li>
    <li class="mm">
        美女D
    </li>
    <li class="gg">
        帅哥D
    </li>
    <li class="mm">
        美女E
    </li>
    <li class="gg">
        帅哥E
    </li>
    <li class="mm">
        美女F
    </li>
    <li class="gg">
        帅哥F
    </li>
    <li class="gg">
        帅哥G
    </li>
    <li class="gg">
        帅哥H
    </li>
    <li class="gg">
        帅哥G
    </li>
</ul>
<br />

<div id="p1"></div>

</body>
</html>




