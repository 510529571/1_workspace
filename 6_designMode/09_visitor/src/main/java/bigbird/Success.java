package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: 上午10:21
 */
public class Success implements Action {
    @Override
    public void getManConclusion(Man man) {
        System.out.println(man.getGender()+"成功的时候，背后多半有一个伟大的女人");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println(woman.getGender()+"成功的时候，背后大多有一个不成功的男人");
    }
}
