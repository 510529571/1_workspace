package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: 上午10:25
 */
public class Failed implements Action {
    @Override
    public void getManConclusion(Man man) {
        System.out.println(man.getGender()+"失败时，闷头喝酒，谁也不用劝");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println(woman.getGender()+"失败时，眼泪汪汪，谁也劝不了");
    }
}
