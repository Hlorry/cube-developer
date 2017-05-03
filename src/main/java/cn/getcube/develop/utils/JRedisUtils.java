package cn.getcube.develop.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.util.ResourceBundle;

/**
 * Created by SubDong on 2016/3/8.
 */
public class JRedisUtils {

    private static JedisPool pool;

    static {
        if (pool == null) {
            synchronized (JRedisUtils.class) {
                if (pool == null) {
                    ResourceBundle bundle = ResourceBundle.getBundle("redis");
                    if (bundle == null) {
                        throw new IllegalArgumentException(
                                "[redis.properties] is not found!");
                    }
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(Integer.valueOf(bundle
                            .getString("redis.pool.maxActive")));
                    config.setMaxIdle(Integer.valueOf(bundle
                            .getString("redis.pool.maxIdle")));
                    config.setTestOnBorrow(Boolean.valueOf(bundle
                            .getString("redis.pool.testOnBorrow")));
                    config.setTestOnReturn(Boolean.valueOf(bundle
                            .getString("redis.pool.testOnReturn")));
                    pool = new JedisPool(config, bundle.getString("redis.ip"),
                            Integer.valueOf(bundle.getString("redis.port")), Protocol.DEFAULT_TIMEOUT,
                            bundle.getString("redis.password"));
                }
            }
        }
    }

    public static Jedis get() {
        return pool.getResource();
    }

    public static JedisPool getPool() {
        return pool;
    }

    public static void returnJedis(Jedis jedis) {
        closeRedis(jedis);
    }

    public static void returnBrokenJedis(Jedis jedis) {
        closeRedis(jedis);
    }

    private static void closeRedis(Jedis jedis) {
        if (jedis != null && pool.getNumActive() > 0) {
            jedis.close();
        }
    }
}
