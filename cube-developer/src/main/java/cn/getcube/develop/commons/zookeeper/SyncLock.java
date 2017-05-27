package cn.getcube.develop.commons.zookeeper;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

public class SyncLock {
	
	private InterProcessMutex lock;
		
	/**
	 * 获取资源锁（在finally中请释放锁）
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean acquire(String key) throws Exception {
		try {
			this.lock=new InterProcessMutex(CuratorClient.getInstance().getClient(),ZookeeperConfig.basePath+key);
			this.lock.acquire();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 释放锁
	 */
	public void release(){
		try {
			this.lock.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
