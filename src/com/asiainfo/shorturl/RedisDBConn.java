package com.asiainfo.shorturl;


import redis.clients.jedis.Jedis;

public class RedisDBConn {
	 public static void main(String[] args) {
		 setString("yangsy", "cool");
		 System.out.println(getString("yangsy"));
	 }


	 public static Jedis connRedisDB(){
		return RedisPool.getPool().getResource();
	 }

	 /**
     * ��ȡ����
     * @param key
     * @return
     */
    public static String getString(String key){
        String value = null;
        Jedis jedis = null;
        try {
        	jedis = RedisPool.getPool().getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //�ͷ�redis����
            RedisPool.getPool().returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //���������ӳ�
        	RedisPool.returnResource(RedisPool.getPool(), jedis);
        }
        return value;
    }

    /**
     * ��ȡ����
     * @param key
     * @return
     */
    public static String setString(String key,String value){
        String result="";
        Jedis jedis = null;
    	try {
    		jedis = RedisPool.getPool().getResource();
    		result=jedis.set(key,value);
    		System.out.println("result:"+result);
        } catch (Exception e) {
            //�ͷ�redis����
            RedisPool.getPool().returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //���������ӳ�
        	RedisPool.returnResource(RedisPool.getPool(), jedis);
        }
        return result;
    }

}
