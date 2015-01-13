package own.hhw.lang.singleton;

public class Singleton1
{
    //TODO:karl 在自己内部定义自己一个实例，是不是很奇怪？
    private static Singleton1 singleton1 = new Singleton1();

    //如果不写下面的这个构造方法，覆盖了没人的pulbic的构造类
    private Singleton1()
    {
    }

    public static Singleton1 getInstance()
    {
        if(singleton1==null)
            singleton1 = new Singleton1();
        return singleton1;
    }

}


