package top.auzero.autumn.utils;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
*
* @author Auzero
* @since 2021/9/24
*/
@Component
@AllArgsConstructor
public class RedisUtils {

    private RedisTemplate<String, String> redisTemplate;

    /**
     * 设置键值对
     * @param key 键
     * @param value 值
     * @return boolean
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 设置带过期时间的键值对
     * @param key 键
     * @param value 值
     * @param time 过期时间 秒
     * @return boolean
     */
    public boolean set(String key, String value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取值
     * @param key 键
     * @return String
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置过期时间
     * @param key 键
     * @param time 过期时间 秒
     * @return Boolean
     */
    public Boolean exp(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 删除
     * @param key 键
     * @return Boolean
     */
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

}
