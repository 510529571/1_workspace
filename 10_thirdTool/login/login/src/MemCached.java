import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: 下午5:26
 * To change this template use File | Settings | File Templates.
 */
public class MemCached extends MemCachedClient{

    // 创建全局的唯一实例
    protected static MemCachedClient mcc = new MemCachedClient();

    protected static MemCached memCached = new MemCached();

    // 设置与缓存服务器的连接池
    static {
        // 服务器列表和其权重
        String[] servers = { "127.0.0.1:11211" };
        Integer[] weights = { 3 };

        // 获取socke连接池的实例对象
        // 这个类用来创建管理客户端和服务器通讯连接池，
        // 客户端主要的工作（包括数据通讯、服务器定位、hash码生成等）都是由这个类完成的。
        SockIOPool pool = SockIOPool.getInstance();

        // 设置服务器信息
        pool.setServers(servers);

        // 设置Server权重
        pool.setWeights(weights);

        // 设置初始连接数、最小和最大连接数以及最大处理时间
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        // 设置主线程的睡眠时间
        pool.setMaintSleep(30);

        // 设置连接心跳监测开关
        // true:每次通信都要进行连接是否有效的监测，造成通信次数倍增，加大网络负载，
        // 因此在对HighAvailability要求比较高的场合应该设为true
        // 默认状态是false，建议保持默认。
        pool.setAliveCheck(false);

        // 设置连接失败恢复开关
        // 设置为true，当宕机的服务器启动或中断的网络连接后，这个socket连接还可继续使用，否则将不再使用.
        // 默认状态是true，建议保持默认。
        pool.setFailback(true);

        // 设置容错开关
        // true:当当前socket不可用时，程序会自动查找可用连接并返回，否则返回NULL
        // 默认状态是true，建议保持默认。
        pool.setFailover(true);

        // 设置hash算法
        // alg=0 使用String.hashCode()获得hash code,该方法依赖JDK，可能和其他客户端不兼容，建议不使用
        // alg=1 使用original 兼容hash算法，兼容其他客户端
        // alg=2 使用CRC32兼容hash算法，兼容其他客户端，性能优于original算法
        // alg=3 使用MD5 hash算法
        // 采用前三种hash算法的时候，查找cache服务器使用余数方法。采用最后一种hash算法查找cache服务时使用consistent方法。
        // 默认值为0
        pool.setHashingAlg(0);

        // 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，
        // 因此该值需要设置为false（默认是true）
        pool.setNagle(false);

        // 设置socket的读取等待超时值
        pool.setSocketTO(3000);

        // 设置socket的连接等待超时值
        pool.setSocketConnectTO(0);

        // 初始化连接池
        pool.initialize();

        // 压缩设置，超过指定大小（单位为K）的数据都会被压缩
        // mcc.setCompressEnable(true);    //UnsupportedOperation
        // mcc.setCompressThreshold(64 * 1024);
    }

    private MemCached() {

    }

    /**
     * 获取唯一实例.
     * singleton
     * @return
     */
    public static MemCached getInstance() {
        return memCached;
    }

    /**
     * 添加一个指定的键值对到缓存中.
     *
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, Object value) {
        return mcc.add(key, value);
    }

    /**
     * 添加一个指定的键值对到缓存中.
     *
     * @param key
     * @param value
     * @param expiry 多久之后过期
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
     * 根据指定的关键字获取对象.
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return mcc.get(key);
    }
}
