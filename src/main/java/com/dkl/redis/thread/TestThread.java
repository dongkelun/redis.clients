package com.dkl.redis.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestThread {

	public static void main(String[] args) {
		Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
		clusterNodes.add(new HostAndPort("192.168.32.196", 9030));
		final JedisCluster cluster = new JedisCluster(clusterNodes);
		// System.out.println("------- cluster nodes --------");
		// Map<String, JedisPool> nodes = cluster.getClusterNodes();
		// Iterator<Map.Entry<String, JedisPool>> iterNodes = nodes.entrySet()
		// .iterator();
		// while (iterNodes.hasNext()) {
		// Map.Entry<String, JedisPool> entry = iterNodes.next();
		// Jedis jedis = entry.getValue().getResource();
		// System.out.println("============");
		// System.out.println(entry.getKey() + "\n" + jedis.info());
		// }
		ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 100,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(100), new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 36914046; i < Math.pow(10, 8); i++) {
			final String key = "key" + i;
			final String value = "value" + i;
			executor.execute(new Runnable() {
				public void run() {
					cluster.append(key,value);
				}
			});
			System.err.println(i);
		}
		executor.shutdown();
	}

}
