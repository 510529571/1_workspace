package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ����4:25
 */
public abstract class AbstractSort {
    /**
     * ������array��С��������
     * @param array
     */
    protected abstract void sort(int[] array);

    protected abstract void print(int[] array);

    /**
     *�������������һ����׼����
     * 1.����
     * 2.��ӡ
     */
    public final void showSortResult(int[] array){
        this.sort(array);
        this.print(array);
    }
}
