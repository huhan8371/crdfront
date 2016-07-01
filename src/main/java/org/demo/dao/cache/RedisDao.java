package org.demo.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.demo.entity.eps.OptInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisDao {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<OptInfo> schema = RuntimeSchema.createFrom(OptInfo.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);

    }

    public OptInfo getOptInfo(String tid) {
        //redis逻辑操作
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "demo:" + tid;
                //并没有实现内部序列化操作
                //get:byte[]->反序列化->Object(OptInfo)
                //采用自定义序列化

                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    //空对象
                    OptInfo opt = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, opt, schema);
                    //demo 被反序列化
                    return opt;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * OptInfo 对象传递到redis中
     *
     * @param opt
     * @return
     */
    public String putOptInfo(OptInfo opt) {
        //set:Object(OptInfo)->序列化->byte[] ->发送给redis
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "demo:" + opt.getTid();
                byte[] bytes = ProtostuffIOUtil.toByteArray(opt, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeOut = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeOut, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;


    }

}
