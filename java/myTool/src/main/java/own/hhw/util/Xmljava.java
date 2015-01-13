package own.hhw.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * xml文件解析
 * @class Xmljava
 * @description 
 * @author 胡寒伟
 * @copyRight copyright(c) 2012 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Dec 7, 2012 3:22:34 PM
 */
public class Xmljava
{
	public static void parseXml() throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		 // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)

		Document doc = db.parse(new File("src/a.xml"));

		// TODO hhw 完成代码

		NodeList nodeList = doc.getElementsByTagName("Accounts");

		Element e = (Element) nodeList.item(0);

		NodeList nodeList1 = e.getElementsByTagName("Account");

		for (int i = 0; i < nodeList1.getLength(); i++)
		{
			Element element = (Element) nodeList1.item(i);
			System.out.println("\r\n找到一篇账号. 所属区域: " + nodeList1.item(i).getAttributes().getNamedItem("type").getNodeValue() + ". ");

           /* System.out.println("姓名："+element.getElementsByTagName("name").item(0).getTextContent());
            System.out.println("卡号："+element.getElementsByTagName("code").item(0).getTextContent());
            System.out.println("密码："+element.getElementsByTagName("pass").item(0).getTextContent());
            System.out.println("余额："+element.getElementsByTagName("money").item(0).getTextContent());*/
		}
	}

	public void test1()
	{
		try
        {
	        Xmljava.parseXml();
        } catch (ParserConfigurationException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (SAXException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	
	public static void main(String args[])
	{
		Element element = null;
		File f = new File("src/a.xml");
		DocumentBuilder db = null; // documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilderFactory dbf = null;
		try
		{

			dbf = DocumentBuilderFactory.newInstance(); // 返回documentBuilderFactory对象
			db = dbf.newDocumentBuilder();// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象

			Document dt = db.parse(f); // 得到一个DOM并返回给document对象
			element = dt.getDocumentElement();// 得到一个elment根元素

			System.out.println("根元素：" + element.getNodeName()); // 获得根节点

			NodeList childNodes = element.getChildNodes(); // 获得根元素下的子节点

			for (int i = 0; i < childNodes.getLength(); i++) // 遍历这些子节点
			{

				Node node1 = childNodes.item(i); // childNodes.item(i); 获得每个对应位置i的结点

				if ("Account".equals(node1.getNodeName()))
				{
					// 如果节点的名称为"Account"，则输出Account元素属性type
					System.out.println("\r\n找到一篇账号. 所属区域: " + node1.getAttributes().getNamedItem("type").getNodeValue() + ". ");
					NodeList nodeDetail = node1.getChildNodes(); // 获得<Accounts>下的节点
					for (int j = 0; j < nodeDetail.getLength(); j++)
					{ // 遍历<Accounts>下的节点
						Node detail = nodeDetail.item(j); // 获得<Accounts>元素每一个节点
						if ("code".equals(detail.getNodeName())) // 输出code
							System.out.println("卡号: " + detail.getTextContent());
						else if ("pass".equals(detail.getNodeName())) // 输出pass
							System.out.println("密码: " + detail.getTextContent());
						else if ("name".equals(detail.getNodeName())) // 输出name
							System.out.println("姓名: " + detail.getTextContent());
						else if ("money".equals(detail.getNodeName())) // 输出money
							System.out.println("余额: " + detail.getTextContent());

					}
				}

			}
		}

		catch (Exception e)
		{
			System.out.println(e);
		}

	}
}