package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ����10:25
 */
public class Failed implements Action {
    @Override
    public void getManConclusion(Man man) {
        System.out.println(man.getGender()+"ʧ��ʱ����ͷ�Ⱦƣ�˭Ҳ����Ȱ");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println(woman.getGender()+"ʧ��ʱ������������˭ҲȰ����");
    }
}
