package own.hhw.example1;
/**
 * 
 * @author 阿福(trygf521@126.com)<br>
 *水浒传是这样写的：西门庆被潘金莲用竹竿敲了一下，西门庆看痴迷了，被王婆看到了，就开始撮合两人好事，王婆作为潘金莲的代理人收了不少好处费，那我们假设一下：
 *如果没有王婆在中间牵线，这两个不要脸的能成事吗？难说得很！
 */
public class XiMenQiang {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WangPo wangPo;
		//把王婆叫出来
		 wangPo = new WangPo();
		//然后西门庆说，我要和潘金莲Happy,然后王婆就安排了西门庆丢筷子哪出戏：
		wangPo.makeEyesWithMan();
		//看到没有表面是王婆在做，其实爽的是潘金莲
		wangPo.happyWithMan();
		
		
		
		//西门庆勾引贾氏
		JiaShi jiaShi = new JiaShi();
		wangPo = new WangPo(jiaShi);
		wangPo.makeEyesWithMan();
		wangPo.happyWithMan();

	}

}
