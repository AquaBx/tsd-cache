package put.poznan.tsdcache.authentication;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RedisBadLoginAttemptsStorage implements BadLoginAttemptsStorage {

    private final ValueOperations<String, Integer> redisOperations;
    // INFO: TTL is set in configuration class
    private final long ttl;

    public RedisBadLoginAttemptsStorage(RedisTemplate<String, Integer> redis, long ttl) {
        this.ttl = ttl;
        this.redisOperations = redis.opsForValue();
    }

    // TODO 2.1 - Finish this method. Use redisOperations
    @Override
    public Integer get(String key) {
        Integer v = redisOperations.get(getKey(key));
        if (v == null) {
            return 0;
        }
        return v;
    }

    // TODO 2.1 - Finish this method. Use redisOperations
    // TODO - Remember that you need to set TTL
    @Override
    public void put(String key, Integer value) {
        redisOperations.set(getKey(key), value, ttl, TimeUnit.SECONDS);
    }

    // TODO 2.1 - Finish this method. Use redisOperations
    // TODO - Remember that you need to set a new TTL
    @Override
    public void increment(String key) {
        Integer current = redisOperations.get(getKey(key));
        if (current == null) {
            current = 0;
        }
        redisOperations.set(getKey(key), current+1, ttl, TimeUnit.SECONDS);
    }

    // TODO 2.1 - Finish this method. Use redisOperations
    @Override
    public void remove(String key) {
        redisOperations.getAndDelete(getKey(key));
    }

    // TODO 2.1 - Finish this method. Use redisOperations
    // TODO - TIP: You can use getAndExpire() method
    public void setTtl(String key) {
        redisOperations.getAndExpire(getKey(key), ttl, TimeUnit.SECONDS);
    }

    // --- NOTICE ---
    // Use this method whenever you perform any operation
    private static String getKey(String key) {
        return "bad_login:" + key;
    }
}
