package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ����10:21
 */
public class Success implements Action {
    @Override
    public void getManConclusion(Man man) {
        System.out.println(man.getGender()+"�ɹ���ʱ�򣬱�������һ��ΰ���Ů��");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println(woman.getGender()+"�ɹ���ʱ�򣬱�������һ�����ɹ�������");
    }
}
