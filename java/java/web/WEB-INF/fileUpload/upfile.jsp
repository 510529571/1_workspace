<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>一亩三分-上传</title>
<SCRIPT src="/1m3f/Common_Development/Global.js"></SCRIPT>
<SCRIPT src="/1m3f/Common_Development/HttpSubmit.js"></SCRIPT>
<SCRIPT src="/1m3f/Common_Development/Tools.js"></SCRIPT>
<script language="javascript">
    
var totalByte=[0,0];
var uploadedByte=[0,0];
var halder=[];
var uploadForms=[];
//var totalBar=[];
var goBar=[];
var rightfulExts={};
rightfulExts.rar="";
rightfulExts.jpg="";
rightfulExts.exe="";
rightfulExts.iso="";
rightfulExts.gif="";
rightfulExts.ini="";
rightfulExts.rmvb="";
rightfulExts.pdf="";

function upload(){
    var l=uploadForms.length;
    for(var i=0;i<l;i++){
        var ext=uploadForms[i].fileData.value.split(".").pop().toLowerCase();
        if(!(ext in rightfulExts)&&ext!=""){
            alert("第"+(i+1)+"个文件类型不匹配！");
            return false;
        }
        if(ext!=""){
            uploadForms[i].saveName.value=getNewId("13","fileUpload13")+"."+ext;
        uploadForms[i].curCount.value=i;
        }
    }
    
    uploadForms[0].submit();
    totalByte[0]=0;
    halder[0]=window.setInterval("getTotalByte("+0+");",1);
    
    uploadForms[1].submit();
    totalByte[1]=0;
    halder[1]=window.setInterval("getTotalByte("+1+");",1);
}

function getTotalByte(c){
    totalByte[c]=getSession("totalByte"+c);
    if(totalByte[c]!=0&&totalByte[c]!=false){
        //totalBar[c].innerHTML=totalByte[c];
        window.clearInterval(halder[c]);
        var t=Math.ceil(Math.log(totalByte[c]));
        halder[c]=window.setInterval("getUploadedByte("+c+");",t*t);
    }
}

function getUploadedByte(c){
    uploadedByte[c]=getSession("uploadedByte"+c);
    var percent=Math.floor((uploadedByte[c]/totalByte[c])*100);
    goBar[c].style.width=percent+"%";
    if(totalByte[c]==uploadedByte[c]){
        window.clearInterval(halder[c]);
    }
}

function windowOnload(){
    uploadForms=[document.forms["uploadForm0"],document.forms["uploadForm1"]];
    //totalBar=[$("totalCountainer0"),$("totalCountainer1")];
    goBar=[$("uploadedCountainer0"),$("uploadedCountainer1")];
}
function windowInit(){
    $("upload").onclick=upload;
}
</script>

</head>
<body onload="windowInit();windowOnload();">
    <iframe src="about:blank" name="fileUploadFrame0" style="display:none;" >
    </iframe>
    <form name="uploadForm0" action=.upload method="post" enctype="multipart/form-data" target="fileUploadFrame0">
        <table>
            <tr>
                <td>
                    文件1
                </td>
                <td>
                    <input type="hidden" name="curCount"/>
                    <input type="hidden" name="savePath" value="images" />
                    <input type="hidden" name="saveName" />
                    <input type="hidden" name="canOverWrite" value="true" />
                    <input type="file" name="fileData" />
                </td>
            </tr>
        </table>
    </form>
    <div id="totalCountainer0" style="width:500px;border-style:solid;border-color:#4062E8">
        <div id="uploadedCountainer0" style="background-image:url(/1m3f/images/downloadbar.png);width:0%;"></div>
    </div>
    
    
    <iframe src="about:blank" name="fileUploadFrame1" style="display:none;" >
    </iframe>
    <form name="uploadForm1" action=.upload method="post" enctype="multipart/form-data" target="fileUploadFrame1">
        <table>
            <tr>
                <td>
                    文件2
                </td>
                <td>
                    <input type="hidden" name="curCount" />
                    <input type="hidden" name="savePath" value="images" />
                    <input type="hidden" name="saveName" />
                    <input type="hidden" name="canOverWrite" value="true" />
                    <input type="file" name="fileData" />
                </td>
            </tr>
        </table>
    </form>
    <div id="totalCountainer1" style="width:500px;border-style:solid;border-color:#4062E8">
        <div id="uploadedCountainer1" style="background-image:url(/1m3f/images/downloadbar.png);width:0%;"></div>
    </div>
    <input id="upload" type="button" value="上传" />
</body>
</html>