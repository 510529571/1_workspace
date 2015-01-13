package own.hhw.example2;

/**
 * 
 * @author 阿福(trygf521@126.com)<br>
 *王婆这个人老聪明了，她太老了，是个男人都看不上她，
 *但是她有智慧经验呀，他作为一类女人的代理！
 */
public class WangPo implements KindWoman {

	private KindWoman kindWoman;
    private int money=0;

	public WangPo(){
		//默认的话是潘金莲的代理
		this.kindWoman = new PanJinLian();
	}
	//她可以是KindWomam的任何一个女人的代理，只要你是这一类型
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
                System.out.println("官人，您给的钱太少了");
                return;
            }
        }else if(kindWoman instanceof JiaShi){
            if(money<400){
                System.out.println("官人，您给的钱太少了");
                return;
            }
        }
		//自己老了，干不了了，但可以叫年轻的代替。
		this.kindWoman.happyWithMan();
		
	}

	@Override
	public void makeEyesWithMan() {
		//王婆年纪大了，谁看她抛媚眼啊
		this.kindWoman.makeEyesWithMan();
		
	}

}
