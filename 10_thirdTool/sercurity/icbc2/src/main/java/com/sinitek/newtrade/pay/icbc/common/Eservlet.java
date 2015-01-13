package com.sinitek.newtrade.pay.icbc.common;

import cn.com.icbc.CMS.commontools.TranslationTool;
import cn.com.infosec.icbc.ReturnValue;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Eservlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        String version = null;
        String tran_code = null;
        String body = null;
        String signature = null;
        String errorCode = null;

        byte[] cert = null;

        int state = 1;

        if (req.getMethod() != "POST") {
            rsp.setStatus(405);
            rsp.flushBuffer();
        } else {
            System.out.println("�յ� 1 �ʷ�������");
            version = req.getParameter("Version");
            tran_code = req.getParameter("TransCode");//���״���
            body = req.getParameter("reqData");//���ذ���Ϣ
            signature = req.getParameter("PackageID");//ָ������к�

            try {
                cert = TranslationTool.readFile(ConfigContext.ep_cerfile);
            } catch (IOException e4) {
                System.out.println("�޷���ȡ�������ݲ㹫Կ�ļ�");
            }

//			try
//			{
//				message = B64Code.decode(body.toCharArray());
//			}
//			catch (Exception e)
//			{
//				System.out.println("�Ƿ����ݱ���");
//			}

            try {
                if (tran_code.equals("DZD") || tran_code.equals("ACI")) {//����ǩ�������²���
                    try {
                        String postbody = getstrFromBASE64(body);//���������ֶν���
                        System.out.println(postbody);
                        int mLength = Integer.parseInt((postbody.substring(0, 10)).trim());
                        String message = postbody.substring(10, 10 + mLength);
                        String seperator = postbody.substring(10 + mLength, 10 + mLength + 7);
                        if (seperator == null || !seperator.equals("ICBCCMP")) {
                            System.out.println("���ذ��ָ�������");
                        }

                        byte[] crymessage = getbyteFromBASE64(postbody.substring(10 + mLength + 7));

                        try {
                            state = ReturnValue.verifySign(message.getBytes(ICBCUtils.ICBC_ENCODING), mLength, cert, crymessage);
                        } catch (Exception e) {
                            System.out.println("�޷���֤����ǩ��");
                        }
                        if (state != 0) {
                            System.out.println("�Ƿ�����ǩ��");
                        } else {
                            String filename = System.currentTimeMillis() + tran_code + ".xml";
                            File outputfile = new File(filename);
                            FileOutputStream fos = new FileOutputStream(outputfile);
                            fos.write(message.getBytes());
                            fos.close();
                            System.out.println("��֤ǩ���ɹ�,�������ݼ�¼���ļ� " + filename);
                        }
                        rsp.setStatus(200);
                        rsp.flushBuffer();
                    } catch (Exception e1) {
                        System.out.println("�����쳣��" + body);
                        rsp.setStatus(200);
                        rsp.flushBuffer();
                        return;
                    }
                } else {
                    try {
                        String postbody = getstrFromBASE64(body);//���������ֶν���

                        System.out.println("���ܱ��ģ�"+postbody);

                        String filename = System.currentTimeMillis() + tran_code + ".xml";
                        File outputfile = new File(filename);
                        FileOutputStream fos = new FileOutputStream(outputfile);
                        fos.write(postbody.getBytes());
                        fos.close();
                        System.out.println("�������ݼ�¼���ļ� " + filename);

                        rsp.setStatus(200);
                        rsp.flushBuffer();
                    } catch (Exception e1) {
                        System.out.println("�����쳣��" + body);
                        rsp.setStatus(200);
                        rsp.flushBuffer();
                        return;
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    public static String getstrFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,ICBCUtils.ICBC_ENCODING);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * base64����
     *
     * @param s
     * @return
     */
    public static byte[] getbyteFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            return decoder.decodeBuffer(s);
        } catch (Exception e) {
            return null;
        }
    }

}
