package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 下午4:25
 */
public abstract class AbstractSort {
    /**
     * 将数组array由小到大排序
     * @param array
     */
    protected abstract void sort(int[] array);

    protected abstract void print(int[] array);

    /**
     *这个方法定义了一个标准流程
     * 1.排序
     * 2.打印
     */
    public final void showSortResult(int[] array){
        this.sort(array);
        this.print(array);
    }
}
