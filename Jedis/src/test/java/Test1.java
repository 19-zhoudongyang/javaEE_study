import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class Test1 {
    static Jedis jedis = new Jedis("192.168.201.100", 6379);

    @Test
    public void demo01() {
        jedis.set("name", "lucy");
        String name = jedis.get("name");
        System.out.println(name);

        jedis.mset("k1", "v1", "k2", "v2");
        List<String> mget = jedis.mget("k1", "k2");
        for (String s : mget) {
            System.out.println(s);
        }
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
