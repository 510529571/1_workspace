package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 下午4:27
 */
public class ConcreteSort extends AbstractSort {

    @Override
    protected void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            selectSort(array, i);
        }
    }

    @Override
    protected void print(int[] array) {
        System.out.print("排序结果：");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%3s", array[i]);
        }
    }

    private void selectSort(int[] array, int index) {
        int MinValue = 32767; // 最小值变量
        int indexMin = 0; // 最小值索引变量
        int Temp; // 暂存变量
        for (int i = index; i < array.length; i++) {
            if (array[i] < MinValue) { // 找到最小值
                MinValue = array[i]; // 储存最小值
                indexMin = i;
            }
        }
        Temp = array[index]; // 交换两数值
        array[index] = array[indexMin];
        array[indexMin] = Temp;
    }
}