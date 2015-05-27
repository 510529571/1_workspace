package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ионГ10:40
 */
class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    public void execute(){
        //do something
        strategy.doSomething();
        //do something
    }
}