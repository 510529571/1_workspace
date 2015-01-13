import org.junit.Test;
import own.hhw.sercurity.RSA;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-18
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public class RSATest {
    private static final String pubKey =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqtsY2w7jjbV4hVoQ+8EHt8eiS" +
                    "5Aa499tmLDAXTjTSiw3r9lR7/rq845901jxC4n+x7P+xwE6lmYiS4ae0Bw+bTSBy" +
                    "/qLJdqY4zd9pQa21ZaT2tz8J0mGw4Nl4Jmb2wjT4BNlSx07sNrA2JHMc+UrdFoMM" +
                    "yKZVVX815qzE4aUuTwIDAQAB";//公钥
    private static final String privateKey =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKq2xjbDuONtXiFWhD7w" +
                    "Qe3x6JLkBrj322YsMBdONNKLDev2VHv+urzjn3TWPELif7Hs/7HATqWZiJLhp" +
                    "7QHD5tNIHL+osl2pjjN32lBrbVlpPa3PwnSYbDg2XgmZvbCNPgE2VLHTuw2sD" +
                    "Ykcxz5St0WgwzIplVVfzXmrMThpS5PAgMBAAECgYEAjH1qVjtwdsnUw37dyqYS" +
                    "u+1vRyOdXK+twUHwOd3EntiNFnlcQcb6iuSQn6iHY0vfZt/x9aibkLDvsmE+kG" +
                    "uXLRECCZugQSurN6rdJBAgEa3Y6QuR9g7O7lWVwhvNvuIkmPXYNHBKu+pdUgyj" +
                    "pB47n1yTZDKh5jUgLfa8M0bJb0ECQQDdQkQfVE9QxDQuoCZdsphx7I8jba4c7B" +
                    "ovNSnTmnPhf6QYhNhyckZYSBrgXCgOE5BaDvFnFsOoECzwfMf8AaSfAkEAxYTN" +
                    "g0f/w3TtOxARF+NyoKS/6vM2XABVill5Mj9uZtar+poglN5CwXmx+nZ499yuQf" +
                    "XwsMolmnSJK8GS4hDoUQJAMgbjpDnJ3TcfnVM0mYbiFRAppRcNgRiZKwdYN7H6" +
                    "dYgIsEqJLgdMhHV5LjVoERa3UsWCK47oGj3eC1oLVZ2BfQJAT0zqVfLqNMcVF8l" +
                    "zk2u18dkzVFkhUf6wWUNi7VGaOZ+mnI6U1jAGDTeWxUAbgSgQrlrXr6L5RfHTwM" +
                    "WO+0RegQJAFv40P2hgH2ZeBwcIvWF7XcttfcPvABlA0urY0nnZ9H4V0+lFNJj5V" +
                    "XXmwFLJ9+R85mj+qexHs0wqf7rWO7vZxA==";
    @Test
    public void test() {
        String inputCharset = "UTF-8";

        String plain = "我是带签名数据";
        String sign = RSA.sign(plain, privateKey, inputCharset);

        boolean verifySignFlag = RSA.doCheck(plain, sign, pubKey, inputCharset);
        System.out.println(verifySignFlag);
    }
}
