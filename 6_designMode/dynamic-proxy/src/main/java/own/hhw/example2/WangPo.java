package own.hhw.example2;

/**
 * 
 * @author ����(trygf521@126.com)<br>
 *����������ϴ����ˣ���̫���ˣ��Ǹ����˶�����������
 *���������ǻ۾���ѽ������Ϊһ��Ů�˵Ĵ���
 */
public class WangPo implements KindWoman {

	private KindWoman kindWoman;
    private int money=0;

	public WangPo(){
		//Ĭ�ϵĻ����˽����Ĵ���
		this.kindWoman = new PanJinLian();
	}
	//��������KindWomam���κ�һ��Ů�˵Ĵ���ֻҪ������һ����
	public WangPo(KindWoman kindWoman){
		this.kindWoman = kindWoman;
	}

    public void getMoney(int money){
        this.money=money;
    }

	@Override
	public void happyWithMan() {
        if(kindWoman instanceof PanJinLian){
            if(money<600){
                System.out.println("���ˣ�������Ǯ̫����");
                return;
            }
        }else if(kindWoman instanceof JiaShi){
            if(money<400){
                System.out.println("���ˣ�������Ǯ̫����");
                return;
            }
        }
		//�Լ����ˣ��ɲ����ˣ������Խ�����Ĵ��档
		this.kindWoman.happyWithMan();
		
	}

	@Override
	public void makeEyesWithMan() {
		//������ʹ��ˣ�˭���������۰�
		this.kindWoman.makeEyesWithMan();
		
	}

}
