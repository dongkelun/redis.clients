package com.dkl.redis.Demo2;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
//
public class Test {
	JedisCluster jc = null;
	@Before
	    public void before(){
	        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
	        //Jedis Cluster will attempt to discover cluster nodes automatically
	        jedisClusterNodes.add(new HostAndPort("192.168.32.198", 8003));
	        jc = new JedisCluster(jedisClusterNodes);
	    }
	    /**
	     * 在一个无限循环中不停的读写
	     * @throws InterruptedException
	     */
	    @org.junit.Test
	    public void setAndWriteStringValueRepeatedly() throws InterruptedException{
	        String key = "test_oper_during_failover";
	        jc.del(key);
	        long failureTime = 0;
	        long recoveryTime = 0;
	        while(true){
	            try {
	                String result = jc.get(key);
	                if(failureTime != 0 && recoveryTime==0){
	                    recoveryTime =  System.currentTimeMillis();
	                    System.out.println("Cluster is recovered! Downtime lasted "+(recoveryTime-failureTime)+" ms");
	                }
	                      
	                System.out.println(result);
	                jc.set(key, System.currentTimeMillis()+"");
	                  
	            } catch (Exception e) {
	                if(failureTime==0)
	                    failureTime=System.currentTimeMillis();
	                e.printStackTrace();
	            }
	            Thread.sleep(1000);
	        }
	    }
}
