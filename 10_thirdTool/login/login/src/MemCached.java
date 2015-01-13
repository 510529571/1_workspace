import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: ����5:26
 * To change this template use File | Settings | File Templates.
 */
public class MemCached extends MemCachedClient{

    // ����ȫ�ֵ�Ψһʵ��
    protected static MemCachedClient mcc = new MemCachedClient();

    protected static MemCached memCached = new MemCached();

    // �����뻺������������ӳ�
    static {
        // �������б����Ȩ��
        String[] servers = { "127.0.0.1:11211" };
        Integer[] weights = { 3 };

        // ��ȡsocke���ӳص�ʵ������
        // �����������������ͻ��˺ͷ�����ͨѶ���ӳأ�
        // �ͻ�����Ҫ�Ĺ�������������ͨѶ����������λ��hash�����ɵȣ��������������ɵġ�
        SockIOPool pool = SockIOPool.getInstance();

        // ���÷�������Ϣ
        pool.setServers(servers);

        // ����ServerȨ��
        pool.setWeights(weights);

        // ���ó�ʼ����������С������������Լ������ʱ��
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        // �������̵߳�˯��ʱ��
        pool.setMaintSleep(30);

        // ��������������⿪��
        // true:ÿ��ͨ�Ŷ�Ҫ���������Ƿ���Ч�ļ�⣬���ͨ�Ŵ����������Ӵ����縺�أ�
        // ����ڶ�HighAvailabilityҪ��Ƚϸߵĳ���Ӧ����Ϊtrue
        // Ĭ��״̬��false�����鱣��Ĭ�ϡ�
        pool.setAliveCheck(false);

        // ��������ʧ�ָܻ�����
        // ����Ϊtrue����崻��ķ������������жϵ��������Ӻ����socket���ӻ��ɼ���ʹ�ã����򽫲���ʹ��.
        // Ĭ��״̬��true�����鱣��Ĭ�ϡ�
        pool.setFailback(true);

        // �����ݴ���
        // true:����ǰsocket������ʱ��������Զ����ҿ������Ӳ����أ����򷵻�NULL
        // Ĭ��״̬��true�����鱣��Ĭ�ϡ�
        pool.setFailover(true);

        // ����hash�㷨
        // alg=0 ʹ��String.hashCode()���hash code,�÷�������JDK�����ܺ������ͻ��˲����ݣ����鲻ʹ��
        // alg=1 ʹ��original ����hash�㷨�����������ͻ���
        // alg=2 ʹ��CRC32����hash�㷨�����������ͻ��ˣ���������original�㷨
        // alg=3 ʹ��MD5 hash�㷨
        // ����ǰ����hash�㷨��ʱ�򣬲���cache������ʹ�������������������һ��hash�㷨����cache����ʱʹ��consistent������
        // Ĭ��ֵΪ0
        pool.setHashingAlg(0);

        // �����Ƿ�ʹ��Nagle�㷨����Ϊ���ǵ�ͨѶ������ͨ�����Ƚϴ����TCP�������ݣ�����Ҫ����Ӧ��ʱ��
        // ��˸�ֵ��Ҫ����Ϊfalse��Ĭ����true��
        pool.setNagle(false);

        // ����socket�Ķ�ȡ�ȴ���ʱֵ
        pool.setSocketTO(3000);

        // ����socket�����ӵȴ���ʱֵ
        pool.setSocketConnectTO(0);

        // ��ʼ�����ӳ�
        pool.initialize();

        // ѹ�����ã�����ָ����С����λΪK�������ݶ��ᱻѹ��
        // mcc.setCompressEnable(true);    //UnsupportedOperation
        // mcc.setCompressThreshold(64 * 1024);
    }

    private MemCached() {

    }

    /**
     * ��ȡΨһʵ��.
     * singleton
     * @return
     */
    public static MemCached getInstance() {
        return memCached;
    }

    /**
     * ���һ��ָ���ļ�ֵ�Ե�������.
     *
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value) {
        return mcc.add(key, value);
    }

    /**
     * ���һ��ָ���ļ�ֵ�Ե�������.
     *
     * @param key
     * @param value
     * @param expiry ���֮�����
     * @return
     */
    public boolean add(String key, Object value, Date expiry) {
        return mcc.add(key, value, expiry);
    }


    public boolean set(String key, Object value) {
        return mcc.set(key, value);
    }

    public boolean set(String key, Object value, Date expiry) {
        return mcc.set(key, value, expiry);
    }

    public boolean replace(String key, Object value) {
        return mcc.replace(key, value);
    }

    public boolean replace(String key, Object value, Date expiry) {
        return mcc.replace(key, value, expiry);
    }

    /**
     * ����ָ���Ĺؼ��ֻ�ȡ����.
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return mcc.get(key);
    }
}
