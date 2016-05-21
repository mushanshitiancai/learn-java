import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by mazhibin on 16/5/21
 */
public class RedisTest {
    String host = "192.168.33.10";

    @Test
    public void helloTest(){
        Jedis jedis = new Jedis(host);
        jedis.set("foo","bar");
        String v = jedis.get("foo");
        System.out.println(v);
    }

// [Redis的Java客户端Jedis的八种调用方式(事务、管道、分布式)介绍 - OPEN 开发经验库](http://www.open-open.com/lib/view/open1410485827242.html)

// redis.clients.jedis.exceptions.JedisDataException:
// DENIED Redis is running in protected mode because protected mode is enabled,
// no bind address was specified, no authentication password is requested to clients.
// In this mode connections are only accepted from the loopback interface.
// If you want to connect from external computers to Redis you may adopt one of the following solutions:
// 1) Just disable protected mode sending the command 'CONFIG SET protected-mode no' from the loopback interface by connecting to Redis from the same host the server is running, however MAKE SURE Redis is not publicly accessible from internet if you do so. Use CONFIG REWRITE to make this change permanent.
// 2) Alternatively you can just disable the protected mode by editing the Redis configuration file, and setting the protected mode option to 'no', and then restarting the server.
// 3) If you started the server manually just for testing, restart it with the '--protected-mode no' option.
// 4) Setup a bind address or an authentication password.
// NOTE: You only need to do one of the above things in order for the server to start accepting connections from the outside.


}
