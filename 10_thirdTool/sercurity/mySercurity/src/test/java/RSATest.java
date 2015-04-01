import org.junit.Test;
import own.hhw.Base64;
import own.hhw.sercurity.RSA;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-18
 * Time: 下午4:49
 * To change this template use File | Settings | File Templates.
 */
public class RSATest {
//    private static final String pubKey =
//            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqtsY2w7jjbV4hVoQ+8EHt8eiS" +
//                    "5Aa499tmLDAXTjTSiw3r9lR7/rq845901jxC4n+x7P+xwE6lmYiS4ae0Bw+bTSBy" +
//                    "/qLJdqY4zd9pQa21ZaT2tz8J0mGw4Nl4Jmb2wjT4BNlSx07sNrA2JHMc+UrdFoMM" +
//                    "yKZVVX815qzE4aUuTwIDAQAB";//公钥
//    private static final String privateKey =
//            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKq2xjbDuONtXiFWhD7w" +
//                    "Qe3x6JLkBrj322YsMBdONNKLDev2VHv+urzjn3TWPELif7Hs/7HATqWZiJLhp" +
//                    "7QHD5tNIHL+osl2pjjN32lBrbVlpPa3PwnSYbDg2XgmZvbCNPgE2VLHTuw2sD" +
//                    "Ykcxz5St0WgwzIplVVfzXmrMThpS5PAgMBAAECgYEAjH1qVjtwdsnUw37dyqYS" +
//                    "u+1vRyOdXK+twUHwOd3EntiNFnlcQcb6iuSQn6iHY0vfZt/x9aibkLDvsmE+kG" +
//                    "uXLRECCZugQSurN6rdJBAgEa3Y6QuR9g7O7lWVwhvNvuIkmPXYNHBKu+pdUgyj" +
//                    "pB47n1yTZDKh5jUgLfa8M0bJb0ECQQDdQkQfVE9QxDQuoCZdsphx7I8jba4c7B" +
//                    "ovNSnTmnPhf6QYhNhyckZYSBrgXCgOE5BaDvFnFsOoECzwfMf8AaSfAkEAxYTN" +
//                    "g0f/w3TtOxARF+NyoKS/6vM2XABVill5Mj9uZtar+poglN5CwXmx+nZ499yuQf" +
//                    "XwsMolmnSJK8GS4hDoUQJAMgbjpDnJ3TcfnVM0mYbiFRAppRcNgRiZKwdYN7H6" +
//                    "dYgIsEqJLgdMhHV5LjVoERa3UsWCK47oGj3eC1oLVZ2BfQJAT0zqVfLqNMcVF8l" +
//                    "zk2u18dkzVFkhUf6wWUNi7VGaOZ+mnI6U1jAGDTeWxUAbgSgQrlrXr6L5RfHTwM" +
//                    "WO+0RegQJAFv40P2hgH2ZeBwcIvWF7XcttfcPvABlA0urY0nnZ9H4V0+lFNJj5V" +
//                    "XXmwFLJ9+R85mj+qexHs0wqf7rWO7vZxA==";
    private static final String pubKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhsJY1zu0WASPiJ0NsVLMtdR1+MRZQhit\n" +
            "a1uluaKh2VFl89/jj43SEgIHMHy4LhTCDOoYJfqx3HgqfUxQJM9Lf2hGmbMWvqs/VfiRMOUtP3o9\n" +
            "FAQwnyfKu+ZqBfxPlhnpE/Gl3BCKf5cCPE9i/JTxqwCKx7q+bWl5LZX4HeElAE85eN2n/ojecgij\n" +
            "A6hEn7bbqtG6UcqVoKFgc3CdZw5+UVbmSNx0GkHyw/p/2iR3C+anBKxpIT9YP37uppzISDBuhUJA\n" +
            "WCL0eGw+2aR8TN7GoQxmNHikfzUaGc32KPTkvRqhebsP3HKrjMqtkSA3yYzeoMvLPre8ha9QN16V\n" +
            "SNiXMQIDAQAB";
    private static final String privateKey ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGwljXO7RYBI+InQ2xUsy11HX4\n" +
            "xFlCGK1rW6W5oqHZUWXz3+OPjdISAgcwfLguFMIM6hgl+rHceCp9TFAkz0t/aEaZsxa+qz9V+JEw\n" +
            "5S0/ej0UBDCfJ8q75moF/E+WGekT8aXcEIp/lwI8T2L8lPGrAIrHur5taXktlfgd4SUATzl43af+\n" +
            "iN5yCKMDqESfttuq0bpRypWgoWBzcJ1nDn5RVuZI3HQaQfLD+n/aJHcL5qcErGkhP1g/fu6mnMhI\n" +
            "MG6FQkBYIvR4bD7ZpHxM3sahDGY0eKR/NRoZzfYo9OS9GqF5uw/ccquMyq2RIDfJjN6gy8s+t7yF\n" +
            "r1A3XpVI2JcxAgMBAAECggEAV4NermfXdlF/5PgZLE7ZIjwXYLYiQQpFlLKvrR4wfTCfRQDghxlS\n" +
            "kt8+S/6ynTpdyoU7qW5kkwh2j2g/o6f7H4JYVvg2jRwoxWvm3hn2iIKZp6YQ7UqqHdxEfh6ckJ3C\n" +
            "/3CuhXs4diNbEE99uQoA50wy1AIyKFuWlkQOHVlY7hmQbZBV7guC/O50IbbJNVu8v/jCblfiQVdH\n" +
            "LmPg+rrN6b7kb2pCTFbUiAn+ZGDW91UsJLkqWQ0xskKa+ouHSkCewPfA3946zjrOSNMXLaHbmYJT\n" +
            "vJ7EPXxF1D4lwCC5PFjovm4+ovfZj1rp1vmzhL9U+rK8WHLP58dNQGY/KkAAAQKBgQDbISPv9HqE\n" +
            "28e/mGmG6j4Z+DnqvTHKcGEEQ6PkabHLKVRzT24T1eeDYivBixHGbtHZ1anjq2St330nfFigMRCu\n" +
            "ZWgds+dwbfCr7VZPruurlm67XZvA/DJ65hH4iWlJp86QIGWv8nvmHMo3yThbTegsB4FzlUyPssB2\n" +
            "08qwRk2bcQKBgQCdbwLXOyFNmjDPTbrTYt4V1nUmWMT27CbmvTT0eYmwPeAaJ4nLV9XPb94aa8Hg\n" +
            "NBsh6X4A7Hk0lG6kiR7N4Mv3jKk76C8JWwCZchJQB3ihzmG52/LTjvDkx+vDHotCwH8LbyNY4Y1P\n" +
            "+wrlHrGEuIvnwm9aSgyd18xEv0YigTNXwQKBgGgGq5DWdvNrgsAqEq7BtQmPFa+icWixQN1NXxio\n" +
            "NIdF75luM8um2Gk+yZbv9lhFc8n97NDwJLOGTVvZZBloMYsZCPxQ06ZECuUTns3lNGwY1iiMn8xV\n" +
            "7YD6h719PkaBK0awmpWUf80WDcm99MsjiPyd845zag0txsCieq6EMYOBAoGAJMtuOYl7VhzEbZ74\n" +
            "HDGOQEJYLPcwSZmV2fW8q6rAMG21s8cr4FeOBCZcHYaz0Ee9U6azqVmbVluepFvb1DhG03uPjg3M\n" +
            "+H3JXROZDhVA72hB1OOAFEvjJ0VGZ9r6p0ZAKxb1hu9oTJoayMPsr11GbYs25T5LRpTKDAM8TBIw\n" +
            "QsECgYEAqPK35B63tOxmKT1ZM7Vm66as6oSTIO1k8yaI3pMFHxQ8Xog0Lqb1PmW+DDwfD8qxET6y\n" +
            "IAunFV7loXXJoHMxZ6M6BcVAAU2+tVtqB5CL5twIGDRZySRCLDwD8XZj5457+Xt/L/QQxgWsWmEK\n" +
            "SrQEswzJFKHMcvp/qOLF5ON39H4=";
    @Test
    public void test() throws Exception {
        String inputCharset = "UTF-8";

        String plain = "我是带签名数据";
        String sign = RSA.sign(plain, privateKey, inputCharset);

        boolean verifySignFlag = RSA.doCheck(plain, sign, pubKey, inputCharset);
        System.out.println(verifySignFlag);
    }
}
