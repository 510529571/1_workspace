package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ����4:28
 */
public class Client {
    public static int[] a = { 10, 32, 1, 9, 5, 7, 12, 0, 4, 3 }; // Ԥ����������
    public static void main(String[] args){
        AbstractSort s = new ConcreteSort();
        s.showSortResult(a);
    }
}