package com.dkl.redis.Demo2;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisHelloWorldDemoApp {
	private static final Logger logger = LoggerFactory
			.getLogger(JedisHelloWorldDemoApp.class);

	public static void main(String[] args) {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.32.197", 8003));

		JedisCluster jc = new JedisCluster(jedisClusterNodes);

	}
}