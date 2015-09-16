package com.dkl.redis.demo;

/*
 * 测试端口 9020 单线程写一千万条数据  没有丢失数据
 */
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class Test1 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
			jedisClusterNodes.add(new HostAndPort("192.168.32.196", 9020));
			JedisCluster jc = new JedisCluster(jedisClusterNodes);
			for (int i = 0; i < Math.pow(10, 7); i++) {
				jc.append("key" + i, "value" + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
