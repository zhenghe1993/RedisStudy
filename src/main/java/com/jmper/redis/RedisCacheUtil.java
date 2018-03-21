package com.jmper.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2017-12-26 22:29:03)
 */
@SuppressWarnings("unused")
@Service
public class RedisCacheUtil<K, V, T> {


    private final RedisTemplate<K, T> redisTemplate;

    @Autowired
    public RedisCacheUtil(RedisTemplate<K, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     */
    public ValueOperations<K, T> setCacheObject(K key, T value) {
        ValueOperations<K, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public T getCacheObject(K key) {
        ValueOperations<K, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public ListOperations<K, T> setCacheList(K key, List<T> dataList) {
        ListOperations<K, T> listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            for (T aDataList : dataList) {
                listOperation.rightPush(key, aDataList);
            }
        }
        return listOperation;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public List<T> getCacheList(K key) {
        List<T> dataList = new ArrayList<T>();
        ListOperations<K, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; size != null && i < size; i++) {
            dataList.add(listOperation.leftPop(key));
        }

        return dataList;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    @SuppressWarnings("unchecked")
    public BoundSetOperations<K, T> setCacheSet(K key, Set<T> dataSet) {
        BoundSetOperations<K, T> setOperation = redisTemplate.boundSetOps(key);

        for (T t : dataSet) {
            setOperation.add(t);
        }
        return setOperation;
    }

    /**
     * 获得缓存的set
     *
     * @param key 键
     * @return set集合
     */
    public Set<T> getCacheSet(K key) {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<K, T> operation = redisTemplate.boundSetOps(key);

        Long size = operation.size();
        for (int i = 0; size != null && i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    /**
     * 缓存Map
     *
     * @param key     键
     * @param dataMap 集合
     * @return 缓存时据的对象
     */
    @SuppressWarnings("unchecked")
    public HashOperations<K, V, T> setCacheMap(K key, Map<V, T> dataMap) {

        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {

            for (Map.Entry<V, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * 获得缓存的Map
     *
     * @param key 键
     * @return 缓存时据的对象
     */
    public Map<V, T> getCacheMap(K key) {
        return redisTemplate.<V, T>opsForHash().entries(key);
    }
}