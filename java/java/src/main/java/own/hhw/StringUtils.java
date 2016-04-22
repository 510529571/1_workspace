package own.hhw;


import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * User: hanwei
 * Date: 16-3-23
 * Time: ÏÂÎç2:12
 */
public class StringUtils {
    public static void main(String[] args) throws IOException {
        System.out.println(org.apache.commons.lang.StringUtils.countMatches("12|1|2|2233", "|"));
        int num = new BASE64Decoder().decodeBuffer("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAM+HD8HYF9elchYGSkZbSP21Sq3pub/EFgfmUPBWX4WEfC+h4GdNK5rm9LG6Uj0FxxhrpWWWzZyRhajn3csDXoM6iVu6t9MYjfMkpWI2kXti0ksheOED+FuxUVQ/EpHKrTSp+oiL6T3imTwvPtc6HfB841jWYyYzh4rJG9j94x3rAgMBAAECgYEAlkD5naWxjYbSFTQ0rsAFFvwdt9FTlY6JTOrqizheLLOBnpMbgG+VNLAnVk7KoDgAsKOvut/2und6SJKoOQaVdCuEU5vIfMrFuo4gq1nCoLVYU+WUyj1CVg+aKQRods3EE9b2jMY3cZg/GSwWlqKLNFuTUatfSaOYfLJZ220tsQECQQD15W6S2OPzjmy6ROgYzTnFA7OmfrCbFKie3Ww0xvvlsBmxfvBnGSekO+J1HNt+6IFEccM13JNhl3SL4aBJDkBBAkEA2A4IPSNUr29ZP4LblTGf06Ug8EZm6Up92sjNGsOZfIXiqUTgynFe3/TfAKYQRRlSIus0wj1/ARoBcRUoc1mTKwJBAIG55AcG8+PXfNccaVJEWzjsRggNiICYeoktiduiKty3dialMJFDvR0xFrzwV4Bxi1kfp3wv/XUcITl29Ef2g8ECQQCytpi2Cb2JCC/oyxNbS5sK9HJUjceefZGxIZwW4Wjaidy9a04N5s01r1WvMa4PmixI2gngCgI+JQbARWIaWCPVAkAw64MqIO8f3ZvY8b8RE9L22QHb3rZMpnWa8ODcOqjRci4pnxTqzqeEu9hym19gBi2VpjD/oQqUc9e7CXlfDYzl").length;
        System.out.println("³¤¶È£º" + num);
    }

}
