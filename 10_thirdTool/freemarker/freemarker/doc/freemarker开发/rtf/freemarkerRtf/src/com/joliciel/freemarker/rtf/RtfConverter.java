 /*
  * Written by Assaf Urieli, Joliciel Informatique (http://www.joli-ciel.com), assaf_at_joli-ciel_dot_com
  * Contributed to the Public Domain.
  */
 package com.joliciel.freemarker.rtf;
 
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @author Assaf Urieli
 * Converts an interpolation to an RTF-string.
 * Note that this conversion is limited to internationalization: that is, representing Unicode correctly (+ newlines).
 * Quite primitive so far, since it assumes certain fonts have been included in the RTF header's font table -
 * Ideally, we'd somehow allow the user to configure these.
 * Assumes the font table contains the following:
 * f0: 0ANSI
 * f1: 238Eastern European
 * f2: 204Russian
 * f3: 161Greek
 * f4: 177Hebrew
 * f5: 178Arabic
 * f6: 186Baltic
 */
public class RtfConverter implements TemplateMethodModel {
	private static Charset Windows1252 = Charset.forName("windows-1252");
	private static Charset Windows1250 = Charset.forName("windows-1250");
	private static Charset Windows1251 = Charset.forName("windows-1251");
	private static Charset Windows1253 = Charset.forName("windows-1253");
	private static Charset Windows1255 = Charset.forName("windows-1255");
	private static Charset Windows1256 = Charset.forName("windows-1256");
	private static Charset Windows1257 = Charset.forName("windows-1257");
    
    public RtfConverter() {
    }

    /* (non-Javadoc)
     * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
     */
    public Object exec(List arguments) throws TemplateModelException {
        String string = ((String) arguments.get(0));
        String result = this.convertToRtfString(string);
        return result;
    }
    
    /**
     * Convert a character array including RTF directives and Unicode text
     * to an RTF-friendly character array containing the same directives but RTF-converted ANSI text.
     */
    private String convertToRtfString(String string) {
        boolean rightToLeft = false;
        Charset charset = null;
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<string.length();i++) {
             char c = string.charAt(i);
             int integer = c;
             String hexString =  Integer.toHexString(integer);
             
             if ((c>=0x030&&c<=0x0039)
                || (c>=0x0041&&c<=0x005a)
                || (c>=0x0061&&c<=0x007a)) { // basic latin alpha-numerics
  	            if (rightToLeft) {
  	                sb.append("\\ltrch  ");
  	                rightToLeft = false;
  	            }
  	            if (!Windows1252.equals(charset)) {
  	                sb.append("\\f0 ");
  	                charset = Windows1252;
  	            }
  	            sb.append(c);
             } else if (c>=0x0000&&c<=0x007f) { // other characters shared by all windows charsets
                 switch (c) {
	              	case '\\': sb.append("\\\\"); break;
	             	case '{': sb.append("\\{"); break;
	             	case '}': sb.append("\\}"); break;
	             	case '\n': sb.append("\\par "); break;
                 	case '\r': break;
                 	default: sb.append(c);
                 }
  	        } else if ((c>=0x0080&&c<=0x00ff)
  	                ||(c==0x0153)||(c==0x0152)||(c==0x0160)||(c==0x0161)||(c==0x017d)||(c==0x017e)) {
                 // western european = windows-1252 = 0ANSI
  	            // including Œ,œ,Š,š,Ž,ž
  	            if (rightToLeft) {
  	                sb.append("\\ltrch  ");
  	                rightToLeft = false;
  	            }
  	            if (!Windows1252.equals(charset)) {
  	                sb.append("\\f0 ");
  	                charset = Windows1252;
  	            }
  	            sb.append(this.getHexString(c, Windows1252));			
             } else if (c>=0x0370&&c<=0x03ff) {
                 // greek = windows-1253 = 161Greek
  	            if (rightToLeft) {
  	                sb.append("\\ltrch  ");
  	                rightToLeft = false;
  	            }
  	            if (!Windows1253.equals(charset)) {
  	                sb.append("\\f3 ");
  	                charset = Windows1253;
  	            }
  	            sb.append(this.getHexString(c, Windows1253));
             } else if (c>=0x0400&&c<=0x04ff) {
                 // cyrillic = windows-1251 = 204Russian
  	            if (rightToLeft) {
  	                sb.append("\\ltrch  ");
  	                rightToLeft = false;
  	            }
  	            if (!Windows1251.equals(charset)) {
  	                sb.append("\\f2 ");
  	                charset = Windows1251;
  	            }
  	            sb.append(this.getHexString(c, Windows1251));
             } else if ((c>=0x0590&&c<=0x05ff)
                     || (c==0xfb1d)|| (c==0xfb1f)
                     || (c>=0xfb2a&&c<=0xfb2b)
                     || (c>=0xfb2e&&c<=0xfb2f)
                     || (c>=0xfb31&&c<=0xfb33)
                     || (c==0xfb35)||(c==0xfb3b) || (c==0xfb44) || (c==0xfb4a)
                     || (c>=0xfb4c&&c<=0xfb4f)){
                 // hebrew = windows-1255 = 177Hebrew
  	            if (!Windows1255.equals(charset)) {
  	                sb.append("\\f4 ");
  	                charset = Windows1255;
  	            }
  	            if (!rightToLeft) {
  	                sb.append("\\rtlch ");
  	                rightToLeft = true;
  	            }
  	            // let's start with the different yiddish possibilities
  	           if (c==0xfb1d) { // khirik yud
  	                sb.append(this.getHexString('\u05d9', Windows1255));
  	                sb.append(this.getHexString('\u05b4', Windows1255));
  	           } else if (c==0xfb1f) { // pasakh tsvey yudn
  	                sb.append(this.getHexString('\u05f2', Windows1255));
  	                sb.append(this.getHexString('\u05b7', Windows1255));
  	            } else if (c==0xfb2a) { // shin
  	                sb.append(this.getHexString('\u05e9', Windows1255));
  	                sb.append(this.getHexString('\u05c1', Windows1255));
  	            } else if (c==0xfb2b) { // sin
  	                sb.append(this.getHexString('\u05e9', Windows1255));
  	                sb.append(this.getHexString('\u05c2', Windows1255));
  	            } else if (c==0xfb2e) { // pasakh aleph
  	                sb.append(this.getHexString('\u05d0', Windows1255));
  	                sb.append(this.getHexString('\u05b7', Windows1255));
  	            } else if (c==0xfb2f) { // komets aleph
  	                sb.append(this.getHexString('\u05d0', Windows1255));
  	                sb.append(this.getHexString('\u05b8', Windows1255));
  	            } else if (c==0xfb35) { // dagesh vov
  	                sb.append(this.getHexString('\u05d5', Windows1255));
  	                sb.append(this.getHexString('\u05bc', Windows1255));
  	            } else if (c==0xfb3b) { // kof
  	                sb.append(this.getHexString('\u05db', Windows1255));
  	                sb.append(this.getHexString('\u05bc', Windows1255));
  	            } else if (c==0xfb44) { // pey
  	                sb.append(this.getHexString('\u05e4', Windows1255));
  	                sb.append(this.getHexString('\u05bc', Windows1255));
  	            } else if (c==0xfb4a) { // tof
  	                sb.append(this.getHexString('\u05ea', Windows1255));
  	                sb.append(this.getHexString('\u05bc', Windows1255));
  	            } else if (c==0xfb4c) { // veys
  	                sb.append(this.getHexString('\u05d1', Windows1255));
  	                sb.append(this.getHexString('\u05bf', Windows1255));
  	            } else if (c==0xfb4d) { // khof
  	                sb.append(this.getHexString('\u05db', Windows1255));
  	                sb.append(this.getHexString('\u05bf', Windows1255));
  	            } else if (c==0xfb4e) { // fey
  	                sb.append(this.getHexString('\u05e4', Windows1255));
  	                sb.append(this.getHexString('\u05bf', Windows1255));
  	            } else {
  	                sb.append(this.getHexString(c, Windows1255));
  	            }
             } else if (c>=0x0600&&c<=0x06ff) {
                 // arabic = windows-1256 = 178Arabic
  	            if (!Windows1256.equals(charset)) {
  	                sb.append("\\f5 ");
  	                charset = Windows1256;
  	            }
  	            if (!rightToLeft) {
  	                sb.append("\\rtlch ");
  	                rightToLeft = true;
  	            }
  	            sb.append(this.getHexString(c, Windows1256));
             } else if ((c>=0x0102&&c<=0x0107)
                     ||(c>=0x010c&&c<=0x0111)
                     ||(c>=0x0118&&c<=0x011b)
                     ||(c==0x0139||c<=0x013a)
                     ||(c==0x013d||c<=0x013e)
                     ||(c>=0x0141&&c<=0x0144)
                     ||(c>=0x0147&&c<=0x0148)
                     ||(c>=0x0150&&c<=0x0151)
                     ||(c>=0x0154&&c<=0x0155)
                     ||(c>=0x0158&&c<=0x015b)
                     ||(c>=0x015e&&c<=0x0165)
                     ||(c>=0x016e&&c<=0x0171)
                     ||(c>=0x0179&&c<=0x017e)) {
                 // iso-8859-2 = more or less windows-1250 Central European = 238Eastern European
  	            if (rightToLeft) {
  	                sb.append("\\ltrch  ");
  	                rightToLeft = false;
  	            }
  	            if (!Windows1250.equals(charset)) {
  	                sb.append("\\f1 ");
  	                charset = Windows1250;
  	            }
  	            sb.append(this.getHexString(c, Windows1250));
             } else if ((c>=0x0100&&c<=0x0101)
                     ||(c>=0x0104&&c<=0x0107)
                     ||(c>=0x010c&&c<=0x010d)
                     ||(c>=0x0112&&c<=0x0113)
                     ||(c>=0x0116&&c<=0x0119)
                     ||(c>=0x0122&&c<=0x0123)
                     ||(c>=0x012a&&c<=0x012b)
                     ||(c>=0x012e&&c<=0x012f)
                     ||(c>=0x0136&&c<=0x0137)
                     ||(c>=0x013b&&c<=0x013c)
                     ||(c>=0x0141&&c<=0x0146)
                     ||(c>=0x014c&&c<=0x014d)
                     ||(c>=0x015a&&c<=0x015b)
                     ||(c>=0x0160&&c<=0x0161)
                     ||(c>=0x016a&&c<=0x016b)
                     ||(c>=0x0172&&c<=0x0173)
                     ||(c>=0x0179&&c<=0x017e)) {
                 // windows-1257 Baltic = 186Baltic
  	            if (rightToLeft) {
  	                sb.append("\\ltrch  ");
  	                rightToLeft = false;
  	            }
  	            if (!Windows1257.equals(charset)) {
  	                sb.append("\\f6 ");
  	                charset = Windows1257;
  	            }
  	            sb.append(this.getHexString(c, Windows1257));
            }
        }
		if (rightToLeft) {
			sb.append("\\ltrch  ");
			rightToLeft = false;
		}
          
         return sb.toString(); 
     }
     
    /**
     * Give an RTF hex string of a character in a given character set.
     */
     private String getHexString(char c, Charset charset) {
         CharBuffer cb = CharBuffer.allocate(1);
         cb.put(c);
         cb.rewind();
         ByteBuffer bb = charset.encode(cb);
         byte b = bb.get();        
  		int byteInt = new Byte(b).intValue();
  		if (byteInt<0) byteInt += 256;
  		String hexString = "\\'"+ Integer.toHexString(byteInt);
         return hexString;
     }
}
