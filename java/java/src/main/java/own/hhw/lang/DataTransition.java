package own.hhw.lang;

import java.lang.String;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 13-11-27
 * Time: ����3:05
 * To change this template use File | Settings | File Templates.
 */
public class DataTransition {
    public static void main(String[] args) {

        List list = new ArrayList();

        list.add("a");

        list.add("b");

        list.add("c");

        list.add("d");

        // list.add(1);//�����java.lang.ArrayStoreException�쳣

        // ��list�е��������Ͷ�һ��ʱ���Խ�listת��Ϊ����

        Object[] array = list.toArray();

        System.out.println("��listת���ɵĶ������鳤��Ϊ��" + array.length);

        // ��ת��Ϊ�������͵�����ʱ��Ҫǿ������ת�������ң�Ҫʹ�ô�������toArray����������Ϊ�������飬

        // ��list�е����ݷ�����������У�����������ĳ���С��list��Ԫ�ظ���ʱ�����Զ���������ĳ�������Ӧlist�ĳ���

        String[] array1 = (String[]) list.toArray(new String[0]);

        System.out.println("��listת���ɵ��ַ������鳤��Ϊ��" + array1.length);

        // ����һ��������list�ĳ�����ȵ��ַ�������

        String[] array2 = (String[]) list.toArray(new String[list.size()]);

        System.out.println("��listת���ɵ��ַ������鳤��Ϊ��" + array2.length);

        list.clear();

        // ������ת����list

        for (int i = 0; i < array.length; i++) {

            list.add(array[i]);

        }

        System.out.println("������ת����list��Ԫ�ظ���Ϊ��" + list.size());

        list.clear();

        // ֱ��ʹ��Arrays��asList����

        list = Arrays.asList(array);

        System.out.println("������ת����list��Ԫ�ظ���Ϊ��" + list.size());

        Set set = new HashSet();

        set.add("a");

        set.add("b");

        // ��setת��Ϊ����

        array = set.toArray();

        array1 = (String[]) set.toArray(new String[0]);

        array2 = (String[]) set.toArray(new String[set.size()]);

        System.out.println("��Setת���ɵĶ������鳤��Ϊ��" + array.length);

        System.out.println("��Setת���ɵ��ַ������鳤��Ϊ��" + array2.length);

        // ����ת����Set

        // ������ת����List������List����Set

        set = new HashSet(Arrays.asList(array));

        System.out.println("������ת����Set��Ԫ�ظ���Ϊ��" + list.size());

        // ��Set��գ�Ȼ�������ת���ɵ�listȫ��add

        set.clear();

        set.addAll(Arrays.asList(array1));

        System.out.println("������ת����Set��Ԫ�ظ���Ϊ��" + list.size());

    }

}
