package own.hhw.pdf;

import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class TestFlyingSauser {  
    public static void main(String[] args) throws Exception {  
        demo_1();  
        demo_2();  
    }  
  
    // 不支持中文  
    public static void demo_1() throws Exception {  
        String inputFile = "F:/test/temp.html";  
        String url = new File(inputFile).toURI().toURL().toString();  
        String outputFile = "F:/test/flying.pdf";  
        OutputStream os = new FileOutputStream(outputFile);  
        ITextRenderer renderer = new ITextRenderer(); 
        ITextFontResolver fontResolver = renderer.getFontResolver();  
        fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
        
        renderer.setDocument(url);  
        renderer.layout();  
        renderer.createPDF(os);  
        os.close();  
    }  
  
    // 支持中文  
    public static void demo_2() throws Exception { 
    	 String inputFile = "F:/Test/flying.html";  
         String url = new File(inputFile).toURI().toURL().toString();  
         System.out.println(url);
        String outputFile = "F:/Test/demo_3.pdf";  
        OutputStream os = new FileOutputStream(outputFile);  
        ITextRenderer renderer = new ITextRenderer();  
//        ITextFontResolver fontResolver = renderer.getFontResolver();  
//        fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
//        
    	InputStream in = new FileInputStream(new File(inputFile));
		InputStreamReader isr = new InputStreamReader(in,"UTF-8");
		char[] c = new char[1024];
		int a = isr.read(c);
		StringBuffer sb = new StringBuffer();
		while(a!=-1){
			sb.append(new String(c,0,a));
			a = isr.read(c);
		}
		System.out.println(sb);
        
        StringBuffer html = new StringBuffer();  
        // DOCTYPE 必需写否则类似于 这样的字符解析会出现错误  
        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");  
        html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">").append("<head>")  
            .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")  
            .append("<style type=\"text/css\"><!--  body {font-family: SimSun;}  --></style><style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")  
            .append("</head>")  
            .append("<body>");  
        html.append("<div><table width=\"790\" border=\"1\"><tr><td colspan=\"4\">1234</td></tr><tr><td width=\"378\">1</td><td>2</td><td>3</td><td>4</td></tr></table></div>");  
        html.append("</body></html>");  
        
        renderer.setDocumentFromString(html.toString());  
        // 解决图片的相对路径问题  
        // renderer.getSharedContext().setBaseURL("file:/F:/teste/html/");  
//        renderer.setDocument(url);  
        renderer.layout();  
        renderer.createPDF(os);  
        os.close();  
    }  
}  


