package own.hhw.example1;
/**
 * 
 * @author ����(trygf521@126.com)<br>
 *����������ϴ����ˣ���̫���ˣ��Ǹ����˶�����������
 *���������ǻ۾���ѽ������Ϊһ��Ů�˵Ĵ���
 */
public class WangPo implements KindWoman {
	
	private KindWoman kindWoman;
	
	public WangPo(){
		//Ĭ�ϵĻ����˽����Ĵ���
		this.kindWoman = new PanJinLian();
	}
	//��������KindWomam���κ�һ��Ů�˵Ĵ���ֻҪ������һ����
	public WangPo(KindWoman kindWoman){
		this.kindWoman = kindWoman;
	}

	@Override
	public void happyWithMan() {
		//�Լ����ˣ��ɲ����ˣ������Խ�����Ĵ��档
		this.kindWoman.happyWithMan();
		
	}

	@Override
	public void makeEyesWithMan() {
		//������ʹ��ˣ�˭���������۰�
		this.kindWoman.makeEyesWithMan();
		
	}

}
