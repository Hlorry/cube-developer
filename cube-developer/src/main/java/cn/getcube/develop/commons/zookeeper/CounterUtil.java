package cn.getcube.develop.commons.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.RetryNTimes;

/**
 *  计数器
 * @author haifeng
 *
 */
public class CounterUtil {

	private static CuratorFramework client = CuratorClient.getInstance().getClient();

	private static RetryPolicy retryPolicy = new RetryNTimes(3, 1000);

	/**
	 * 获取当前的数值（long型）
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static int currentIntValue(String path) throws Exception {
		DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client,ZookeeperConfig.basePath+path, retryPolicy);
		return atomicInteger.get().postValue();
	}

	/**
	 * 获取当前的数值（long型）
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static long currentLongValue(String path) throws Exception {
		DistributedAtomicLong atomicLong = new DistributedAtomicLong(client, ZookeeperConfig.basePath+path, retryPolicy);
		return atomicLong.get().postValue();
	}

	/**
	 * 进行int计数
	 * 
	 * @param path
	 *            路径
	 * @param delta
	 *            增加数值（负数为减）
	 * @return
	 * @throws Exception
	 */
	public static AtomicValue<Integer> intCount(String path, int delta) throws Exception {
		DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, ZookeeperConfig.basePath+path, retryPolicy);
		return atomicInteger.add(delta);
	}

	/**
	 * 进行long计数
	 * 
	 * @param path
	 *            路径
	 * @param delta
	 *            增加数值（负数为减）
	 * @return
	 * @throws Exception
	 */
	public static AtomicValue<Long> longCount(String path, long delta) throws Exception {
		DistributedAtomicLong atomicLong = new DistributedAtomicLong(client, ZookeeperConfig.basePath+path, retryPolicy);
		return atomicLong.add(delta);
	}
}
