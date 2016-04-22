package own.hhw.app.xml;

import org.w3c.dom.Document;
import own.hhw.util.PublicTool;

import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-3-5
 * Time: ÉÏÎç10:38
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        String m="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<request>\n" +
                "\t<head>\n" +
                "\t\t<merchId>888110047220006</merchId>\n" +
                "\t\t<timeStamp>2014030510242648</timeStamp>\n" +
                "\t\t<tranCode>SYXSG</tranCode>\n" +
                "\t\t<version>V1.0</version>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<signNo>2014030410165</signNo>\n" +
                "\t\t<fundUser>271000119350</fundUser>\n" +
                "\t\t<fundTransId>999900006691</fundTransId>\n" +
                "\t\t<fundNo>270004</fundNo>\n" +
                "\t\t<tranType>1</tranType>\n" +
                "\t\t<liqdate>20140305</liqdate>\n" +
                "\t\t<fundReqAmount>32200.0</fundReqAmount>\n" +
                "\t\t<currencyType>1</currencyType>\n" +
                "\t\t<fundTradeNo>20140303009088</fundTradeNo>\n" +
                "\t\t<fundReqTime>2014030510242648</fundReqTime>\n" +
                "\t\t<fundAttach>Ö§¸¶</fundAttach>\n" +
                "\t</body>\n" +
                "\t<sign>UkOs2B67eK+KbBjhMSFI3KP+YlQIlI9B9uOy7v57zep8H9tcEadCx+WY7/Pgys32oBYa3plJrcyqRVkklgLMgtctjyzJK34dtLzJ5dY+L1O0Q5I3+YBsxOw6McIVQt/U7WWaxqERR3T3MkjztJ079X45W4WDmRKPfuyY1L7cB54=</sign>\n" +
                "</request>\n";
        Document dom= PublicTool.getDom(m);
        System.out.println(dom.getElementsByTagName("signNo").item(0).getTextContent());
    }
}
