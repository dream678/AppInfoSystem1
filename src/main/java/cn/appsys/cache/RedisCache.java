package cn.appsys.cache;

import cn.appsys.tools.SerializeUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created with IntelliJ IDEA.
 * User:admin
 * Date:2018/12/7
 * Time:11:44
 * Desc:
 */
public class RedisCache implements Cache {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Logger logger=LoggerFactory.getLogger(RedisCache.class);
    /**
     * Jedis客户端
     */

    @Autowired
    private Jedis redisClient = createClient();

    private String id;

    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("必须传入ID");
        }
        System.out.println("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public void clear() {
        logger.info("1=============================================================");
        redisClient.flushDB();
    }

    @Override
    public String getId() {
        logger.info("2=============================================================");
        return this.id;
    }

    @Override
    public Object getObject(Object key) {
        byte[] ob = redisClient.get(SerializeUtil.serialize(key.toString()));
        if (ob == null) {
            return null;
        }
        Object value = SerializeUtil.unSerialize(ob);
        logger.info("3=============================================================");
        return value;
    }

    @Override
    public ReadWriteLock getReadWriteLock()
    {
        logger.info("4=============================================================");
        return readWriteLock;
    }

    @Override
    public int getSize() {
        logger.info("5=============================================================");
        return Integer.valueOf(redisClient.dbSize().toString());
    }

    @Override
    public void putObject(Object key, Object value) {
        logger.info("6=============================================================");
        redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value));
    }

    @Override
    public Object removeObject(Object key) {
        return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);
    }

    protected static Jedis createClient() {

        try {
            @SuppressWarnings("resource")
            JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379);
            logger.info("添加成功=============================================================");
            return pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("初始化连接池错误");
    }

}