package com.java.myspringbootdemo02.App.utils;

import com.java.myspringbootdemo02.Api.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);


     // 解锁Lua脚本
    private static final String LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return " +
             "redis.call('del', KEYS[1]) else return 0 end";

    private static RedisTemplate<String, Object> redisTemplate = SpringUtils.getBean(RedisConfig.REDIS_TEMPLATE_NAME,
            RedisTemplate.class);

    private static RedisTemplate<String, Object> getRedisTemplate() {
        if (redisTemplate == null) {
            throw new RuntimeException("请自行设置redisTemplate或扫描使用RedisUtilConfig配置后，才可正常使用");
        }
        return redisTemplate;
    }

    /**
     * 模糊查询key
     * @param key
     * @return
     */
    public Set<String> listKeys(final String key) {
        Set<String> keys = redisTemplate.keys(key);
        return keys;
    }

    /**
     * 重命名
     * @param oldKey
     * @param newKey
     */
    public void rename(final String oldKey, final String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 模糊获取
     * @param pattern
     * @return
     */
    public List<Object> listPattern(final String pattern) {
        List<Object> result = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(pattern);
        for (Serializable str : keys) {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            Object obj = operations.get(str.toString());
            if (!ObjectUtils.isEmpty(obj)) {
                result.add(obj);
            }
        }
        return result;
    }


    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("set fail ,key is:" + key, e);
        }
        return result;
    }

    /**
     * 批量写入缓存
     * @param map
     * @return
     */
    public boolean multiSet(Map<String, Object> map) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.multiSet(map);
            result = true;
        } catch (Exception e) {
            logger.error("multiSet fail ", e);
        }
        return result;
    }

    /**
     * 集合出栈
     * @param key
     */
    public Object leftPop(String key) {
        ListOperations list = redisTemplate.opsForList();
        return list.leftPop(key);
    }

    public Object llen(final String key) {
        final ListOperations list = this.redisTemplate.opsForList();
        return list.size((Object) key);
    }

    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("set fail ", e);
        }
        return result;
    }

    /**
     * 写入缓存设置时效时间
     * @param key
     * @param value
     * @return
     */
    public boolean setnx(final String key, Object value, Long expireTime) {
        boolean res = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            res = operations.setIfAbsent(key, value);
            if (res) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            logger.error("setnx fail ", e);
        }
        return res;
    }

    /**
     * 缓存设置时效时间
     * @param key
     * @param expireTime
     * @return
     */
    public Boolean expire(final String key, Long expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 自增操作
     * @param key
     * @return
     */
    public long incr(final String key) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return entityIdCounter.getAndIncrement();
    }


    /**
     * 批量删除
     * @param keys
     */
    public void removeKeys(final List<String> keys) {
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 删除单个key
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    public static boolean del(final String key) {
        Boolean ret = getRedisTemplate().delete(key);
        return ret != null && ret;
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 判断缓存中是否有对应的value(模糊匹配)
     * @param pattern
     * @return
     */
    public boolean existsPattern(final String pattern) {
        if (redisTemplate.keys(pattern).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 哈希 添加
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希 添加
     */
    public Boolean hmSet(String key, Object hashKey, Object value, Long expireTime, TimeUnit timeUnit) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
        return redisTemplate.expire(key, expireTime, timeUnit);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 哈希获取所有数据
     * @param key
     * @return
     */
    public Object hmGetValues(String key) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.values(key);
    }

    /**
     * 哈希获取所有键值
     * @param key
     * @return
     */
    public Object hmGetKeys(String key) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.keys(key);
    }

    /**
     * 哈希获取所有键值对
     * @param key
     * @return
     */
    public Object hmGetMap(String key) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.entries(key);
    }

    /**
     * 哈希 删除域
     * @param key
     * @param hashKey
     */
    public Long hdel(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.delete(key, hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void rPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 列表删除
     * @param k
     * @param v
     */
    public void listRemove(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.remove(k, 1, v);
    }

    public void rPushAll(String k, Collection var2) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPushAll(k, var2);
    }


    /**
     * 列表获取
     * @param k
     * @param begin
     * @param end
     * @return
     */
    public Object lRange(String k, long begin, long end) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k, begin, end);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }


    /**
     * 判断元素是否在集合中
     * @param key
     * @param value
     */
    public Boolean isMember(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.isMember(key, value);
    }


    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /**
     * 有序集合根据区间删除
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public void removeRangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.removeRangeByScore(key, scoure, scoure1);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }

    /**
     * 获取当前key的超时时间
     * @param key
     * @return
     */
    public Long getExpireTime(final String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.SECONDS);
    }

    public Long extendExpireTime(final String key, Long extendTime) {
        Long curTime = redisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.SECONDS);
        long total = curTime.longValue() + extendTime;
        redisTemplate.expire(key, total, TimeUnit.SECONDS);
        return total;
    }

    public Set getKeys(String k) {
        return redisTemplate.keys(k);
    }

    /**
     * 加锁，无阻塞
     * @param key        锁
     * @param value      请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean lock(String key, long expireTime, String value) {
        // 在一定时间内获取锁，超时则返回错误
        // Set命令返回OK，则证明获取锁成功
        Boolean ret = getRedisTemplate().opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(ret);
    }

    /**
     * 加锁，无阻塞
     * for循环重试，时间到了没获取到就报错
     * @param key        锁
     * @param value      请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean lockV2(String key, long expireTime, String value) {
        int tryCount = 3;
        while (tryCount > 0) {
            Boolean ret = getRedisTemplate().opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
            if (Boolean.TRUE.equals(ret)) {
                return true;
            }
            tryCount--;
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                logger.error("线程被中断" + Thread.currentThread().getId(), e);
            }
        }
        return false;
    }

    /**
     * 使用lua脚本解锁，不会解除别人锁
     */
    public static boolean unLock(String key, String value) {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(LOCK_LUA_SCRIPT);
        redisScript.setResultType(Boolean.class);
        // 没有指定序列化方式，默认使用上面配置的
        Object result = getRedisTemplate().execute(redisScript, Collections.singletonList(key), value);
        return Boolean.TRUE.equals(result);
    }

}
