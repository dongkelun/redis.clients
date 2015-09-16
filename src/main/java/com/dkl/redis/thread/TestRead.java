package com.dkl.redis.thread;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestRead {

	// 读写同时
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		JedisCluster jc = null;
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		// Jedis Cluster will attempt to discover cluster nodes automatically
		jedisClusterNodes.add(new HostAndPort("192.168.32.196", 9020));
		jc = new JedisCluster(jedisClusterNodes);
//		jc.flushAll();
//		Map<String, JedisPool> nodes = jc.getClusterNodes();
//        Iterator<Map.Entry<String, JedisPool>> iterNodes = nodes.entrySet().iterator();
//        int i = 0;
//        while (iterNodes.hasNext()) {
//        	
//        }
		for (int i = 0; i < Math.pow(10, 8); i++) {
			System.out.println(jc.get("key" + i));
		}
	}

	public static void set(Map<String, String> entries, JedisCluster jc) {
		for (Map.Entry<String, String> entry : entries.entrySet()) {
			jc.set(entry.getKey(), entry.getValue());
		}
	}
}  
