package own.hhw.example1;
/**
 * 
 * @author ����(trygf521@126.com)<br>
 *ˮ䰴�������д�ģ������챻�˽������������һ�£������쿴�����ˣ������ſ����ˣ��Ϳ�ʼ������˺��£�������Ϊ�˽����Ĵ��������˲��ٺô��ѣ������Ǽ���һ�£�
 *���û���������м�ǣ�ߣ���������Ҫ�����ܳ�������˵�úܣ�
 */
public class XiMenQiang {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WangPo wangPo;
		//�����Žг���
		 wangPo = new WangPo();
		//Ȼ��������˵����Ҫ���˽���Happy,Ȼ�����žͰ����������춪�����ĳ�Ϸ��
		wangPo.makeEyesWithMan();
		//����û�б�����������������ʵˬ�����˽���
		wangPo.happyWithMan();
		
		
		
		//�����카������
		JiaShi jiaShi = new JiaShi();
		wangPo = new WangPo(jiaShi);
		wangPo.makeEyesWithMan();
		wangPo.happyWithMan();

	}

}
