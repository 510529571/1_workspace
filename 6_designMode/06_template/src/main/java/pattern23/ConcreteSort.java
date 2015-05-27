package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ����4:27
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
        System.out.print("��������");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%3s", array[i]);
        }
    }

    private void selectSort(int[] array, int index) {
        int MinValue = 32767; // ��Сֵ����
        int indexMin = 0; // ��Сֵ��������
        int Temp; // �ݴ����
        for (int i = index; i < array.length; i++) {
            if (array[i] < MinValue) { // �ҵ���Сֵ
                MinValue = array[i]; // ������Сֵ
                indexMin = i;
            }
        }
        Temp = array[index]; // ��������ֵ
        array[index] = array[indexMin];
        array[indexMin] = Temp;
    }
}