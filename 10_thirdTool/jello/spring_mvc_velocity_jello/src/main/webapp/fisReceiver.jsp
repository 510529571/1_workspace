<%--
  Created by IntelliJ IDEA.
  User: liangzhongzhi
  Date: 14-6-5
  Time: 下午3:13
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.apache.commons.fileupload.FileItem,org.apache.commons.fileupload.FileItemFactory,org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>

<%
    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    if (isMultipart) {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("gbk");

        List items = upload.parseRequest(request);
        Iterator iter = items.iterator();
        String to = "";
        FileItem fileItem = null;

        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            if (item.isFormField()) {
                if (item.getFieldName().equals("to")) {
                    to = item.getString();
                }
            } else {
                fileItem = item;
            }
        }

        File tmpFile = new File(application.getRealPath(to));
        if (tmpFile.exists()) {
            tmpFile.delete();
        } else {
            File dirFile = new File(tmpFile.getParentFile().toString());
            dirFile.mkdirs();
        }

        String fileName = fileItem.getName();

        if (fileName == null || fileName.length() == 0) {
            out.println("file not found");
        } else {
            java.io.FileOutputStream fout = new java.io.FileOutputStream(application.getRealPath(to));
            fout.write(fileItem.get());
            fout.close();
            out.print('0');
        }
    }
%>