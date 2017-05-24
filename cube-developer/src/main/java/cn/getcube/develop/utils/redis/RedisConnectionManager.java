package cn.getcube.develop.utils.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * @author XiaoWei
 * @version V 1.0
 * @package cube.test.redis.cluster
 * Create by XiaoWei on 2017/1/19
 */
public class RedisConnectionManager {

    private static BinaryJedisCluster binaryJedisCluster;

    private final static RedisConnectionManager instance = new RedisConnectionManager();


    public void startup(String ipAndPort) {
        Properties properties = new Properties();
        try {
            properties.load(RedisConnectionManager.class.getClassLoader().getResourceAsStream("redis-config.properties"));
            Set<HostAndPort> jedisClusterNodes = new HashSet<>();
            String address = (!Objects.equals(ipAndPort, null) && !Objects.equals(ipAndPort, "")) ? ipAndPort : properties.getProperty("redis.cluster");
            if (address.indexOf(" ") > -1) {
                for (String str : address.split(" ")) {
                    String[] ipOrPort = str.split(":");
                    jedisClusterNodes.add(new HostAndPort(ipOrPort[0], Integer.parseInt(ipOrPort[1])));
                }
            } else {
                if (address.contains(":")) {
                    String[] ipOrPort = address.split(":");
                    jedisClusterNodes.add(new HostAndPort(ipOrPort[0], Integer.parseInt(ipOrPort[1])));
                }
            }
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            genericObjectPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));
            genericObjectPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));
            genericObjectPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxWait")));
            genericObjectPoolConfig.setTestOnBorrow(false);
            genericObjectPoolConfig.setTestWhileIdle(false);

            Integer MAX_TIMEOUT = Integer.parseInt(properties.getProperty("redis.timeOut"));
            Integer MAX_CONNECTIONS = Integer.parseInt(properties.getProperty("redis.maxConnctions"));

            this.binaryJedisCluster = new BinaryJedisCluster(jedisClusterNodes, MAX_TIMEOUT, MAX_CONNECTIONS, genericObjectPoolConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RedisConnectionManager getInstance() {
        return RedisConnectionManager.instance;
    }

    public void close() {
        try {
            binaryJedisCluster.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BinaryJedisCluster getBinaryJedisCluster() {
        return binaryJedisCluster;
    }
}
