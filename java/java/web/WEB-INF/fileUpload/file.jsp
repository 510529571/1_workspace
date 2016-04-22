<%@page contentType="text/html"%> 
<%@page pageEncoding="GB2312"%> 
<%@page import="java.util.*" %> 
<%@page import="java.io.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.lang.String" %>
<% 

request.setCharacterEncoding("GBK"); 
response.setContentType("text/html;charset=GB2312"); 
char[] hchl={13,10}; 
String boundary=request.getContentType().substring(30);
String field_boundary="--"+boundary+new String(hchl);
String last_boundary="--"+boundary+"--"+new String(hchl);
String _msg="";
ServletInputStream getdata=request.getInputStream(); 
ByteArrayOutputStream temp=new ByteArrayOutputStream(); 
byte[] data_line=new byte[8192]; 
int line_byte_count=0; 
boolean found_boundary=false; 
while((line_byte_count=getdata.readLine(data_line,0,data_line.length))!=-1){ 
    if(!found_boundary){ 
        line_byte_count=getdata.readLine(data_line,0,data_line.length); 
    } 
    String temp_str=new String(data_line,0,line_byte_count);
    if(temp_str.indexOf("filename")!=-1){ 
        if(temp_str.substring(temp_str.indexOf("filename=")+9,temp_str.lastIndexOf("\"")+1).length()>2){ 
            String file_name=temp_str.substring(temp_str.lastIndexOf("\\")+1,temp_str.lastIndexOf("\""));
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            FileOutputStream myfile=new FileOutputStream("e:\\xx\\upload\\"+file_name,false); //文件存放目录
            boolean test=true; 
            while(test) { 
                line_byte_count=getdata.readLine(data_line,0,data_line.length); 
                if(line_byte_count==-1){ 
                    test=false; 
                    break; 
                } 
                if(temp.size()==0){ 
                    temp.write(data_line,0,line_byte_count); 
                }else{ 
                    if(new String(data_line,0,line_byte_count).equals(field_boundary) || new String(data_line,0,line_byte_count).equals(last_boundary)){
                        myfile.write(temp.toByteArray(),0,temp.toByteArray().length-2); 
                        temp.reset(); 
                        myfile.close(); 
                        //out.println(file_name+"上传成功了 <br>");
						_msg=_msg+file_name+"上传成功<br>"; 
                        test=false; 
                        found_boundary=true; 
                    }else{ 
                        temp.writeTo(myfile); 
                        temp.reset(); 
                        temp.write(data_line,0,line_byte_count); 
                    } 
                } 
            } 
        }else{ 
            String field_name=temp_str.substring(temp_str.indexOf("name")+6,temp_str.lastIndexOf(";")-1);
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            found_boundary=true; 
           // out.println(field_name+"没有选择上传文件！ <br>"); 
           // _msg=_msg+field_name+"没有选择上传文件<br>";
        } 
    } else{ 
        String field_name=temp_str.substring(temp_str.indexOf("name")+6,temp_str.lastIndexOf("\""));
        line_byte_count=getdata.readLine(data_line,0,data_line.length); 
        temp.reset(); 
        boolean test=true; 
        while(test) { 
            line_byte_count=getdata.readLine(data_line,0,data_line.length); 
            if(line_byte_count==-1){ 
                test=false; 
                break; 
            } 
            if(new String(data_line,0,line_byte_count).equals(field_boundary) || new String(data_line,0,line_byte_count).equals(last_boundary)){
                test=false; 
                found_boundary=true; 
                if(temp.size()>2){ 
                   // out.println(field_name+":"+new String(temp.toByteArray())+" <br>"); 
				   _msg=_msg+field_name+":"+new String(temp.toByteArray())+"<br>";
                }else{ 
                   // out.println(field_name+"没有内容！ <br>"); 
				   _msg=_msg+field_name+"没有内容！<br>";
                } 
                temp.reset(); 
            }else{ 
                temp.write(data_line,0,line_byte_count); 
            } 
        } 
    } 
    
} 
getdata.close(); 
out.println("<script>window.parent.Finish('"+_msg+"');</script>");
%>