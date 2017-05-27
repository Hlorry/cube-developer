package cn.getcube.develop.commons.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorClient {
	
	//private static String connectString="59.110.45.40:2181,59.110.45.40:2182,59.110.45.40:2183,59.110.45.40:2184,59.110.45.40:2185";
	
	private static CuratorFramework client=CuratorFrameworkFactory.builder()
			.connectString(ZookeeperConfig.connectString)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
	
	private static CuratorClient curatorClient;
	
	static{
		client.start();
	}
	
	private CuratorClient(){
		
	}
	
	public static CuratorClient getInstance(){
		if(curatorClient==null){
			synchronized(CuratorClient.class){
				if(curatorClient==null){
					curatorClient=new CuratorClient();
					
				}
			}
		}
		return curatorClient;
	}

	public CuratorFramework getClient() {
		return client;
	}
	
}
