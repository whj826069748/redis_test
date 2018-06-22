package com.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisJava {

	private String redisip = "192.168.5.128";

	/**
	 * 连接到 redis 服务
	 */
	@Test
	public void test1() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());

	}

	/**
	 * Redis Java String(字符串) 实例
	 */
	@Test
	public void test2() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");
		// 设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
	}

	/**
	 * Redis Java List(列表) 实例
	 */
	@Test
	public void test3() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");
		Long delLong = jedis.del("site_list");
		System.out.println("删除" + delLong);
		// 存储数据到列表中
		jedis.lpush("site_list", "Runoob");
		jedis.lpush("site_list", "Google");
		jedis.lpush("site_list", "Taobao");
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("site_list", 0, 2);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("列表项为: " + list.get(i));
		}
		System.out.println("判断site_list键是否存在：" + jedis.exists("site_list"));

	}

	/**
	 * Redis Java Set(集合) 实例
	 */
	@Test
	public void test4() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");
		Long delLong = jedis.del("set_test");
		System.out.println("删除" + delLong);
		// 存储数据到列表中
		jedis.sadd("set_test", "aa");
		jedis.sadd("set_test", "bb");
		jedis.sadd("set_test", "cc");
		jedis.sadd("set_test", "cc");
		// 获取存储的数据并输出
		Set<String> set = jedis.smembers("set_test");
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String value = it.next();
			System.out.println("集合项为: " + value);
		}
	}

	/**
	 * Redis Java Sorted Set(有序集合) 实例
	 */
	@Test
	public void test5() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");
		Long delLong = jedis.del("sorted_set_test");
		System.out.println("删除" + delLong);
		// 存储数据到列表中
		jedis.zadd("sorted_set_test", 1, "222");
		jedis.zadd("sorted_set_test", 2, "111");
		jedis.zadd("sorted_set_test", 3, "333");
		jedis.zadd("sorted_set_test", 4, "444");
		// 获取存储的数据并输出
		Set<String> set = jedis.zrange("sorted_set_test", 0, 3);
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String value = it.next();
			System.out.println("集合项为: " + value);
		}
	}

	/**
	 * Redis 哈希(Hash)
	 */
	@Test
	public void test6() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");
		Long delLong = jedis.del("hash_test");
		System.out.println("删除" + delLong);

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "张三");
		map.put("age", "12");
		// 存储数据到列表中
		jedis.hmset("hash_test", map);
		// 获取存储的数据并输出
		Map<String, String> retMap = jedis.hgetAll("hash_test");
		System.out.println(retMap.toString());
	}

	/**
	 * Redis Java Keys 实例
	 */
	@Test
	public void test7() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis(redisip);
		System.out.println("连接成功");

		// System.out.println("清空库中所有数据："+jedis.flushDB());

		System.out.println("判断site_list键是否存在：" + jedis.exists("site_list"));

		// 获取数据并输出
		Set<String> keys = jedis.keys("*");
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			// System.out.println(key);
			System.out.println("key:" + key + "   value:" + jedis.get(key));
		}
	}

}