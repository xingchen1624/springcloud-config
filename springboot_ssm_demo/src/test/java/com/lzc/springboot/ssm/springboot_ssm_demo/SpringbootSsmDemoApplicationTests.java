package com.lzc.springboot.ssm.springboot_ssm_demo;

import com.lzc.springboot.ssm.domain.CurrentUser;
import com.lzc.springboot.ssm.service.IShiroUserService;
import com.lzc.springboot.ssm.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class SpringbootSsmDemoApplicationTests {
    /**
     * 在配置文件中配置了数据库连接信息后，springboot会自动配置一个数据源，我们在代码中够可以
     * 直接如下注入数据源使用
     **/
    @Autowired
    DataSource dataSource;
    
    @Autowired
    IShiroUserService userService;
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    RedisUtil redisUtil;
    
    @Test
    void contextLoads() {
        //显示数据源
        System.out.println(dataSource);
        //获取数据库连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
        
        //springboot中提供了很多 xxxTemplate类，可以直接使用
        

        String studentString = "{\"id\":null,\"age\":'',\"name\":\"null\"}";
        //JSON字符串转换成JSON对象
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(studentString);
        Object id = jsonObject.get("id");
        Object age = jsonObject.get("age");
        Object name = jsonObject.get("name");

    }

    /**
     * 功能描述: <br>
     * @Author lzc
     * @Description //TODO 测试shiro是否能链接mybatis
     * @Date 21:47 2021/1/18
     * @Param []
     * @return void
     **/
    @Test
    void queryUserName() {
        List<CurrentUser> users = userService.queryUserByName("张三");
        System.out.println(users);
    }
    
    /**
     * redis测试 
     * 操作不同的数据类型  api和指令一样
     * opsForValue 操作字符串，类似于String
     * opsForList 操作list，类似list
     * ...
     * opsForHash:对应hash
     * opsForGeo:地图用
     * 
     * 实际开发中，一般不会使用原生的api操作redis，而是使用一个Redis工具类
     * 
     **/
    /*@Test
    public void testRedis() throws JsonProcessingException {
        //获取链接
        //RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //connection.flushDb();

        //简单存取字符串
        redisTemplate.opsForValue().set("key","value1" );
        Object val = redisTemplate.opsForValue().get("key");
        System.out.println("value="+val);
        
        //存取对象
        Redis chen = new Redis("chen", 18);
        //使用jackson的ObjectMapper序列化对象，如果我们的pojo类不实现Serializable接口
        //可以通过如下方法进行序列化，不推荐使用
        //String jsonUser = new ObjectMapper().writeValueAsString(chen);
        //将json串存到redis中
        redisTemplate.opsForValue().set("user", chen);

        Object user = redisTemplate.opsForValue().get("user");
        System.out.println(user);

    }*/

    /**
     * 测试redis工具类
     **/
    @Test
    public void testUtil() {
        Object user = redisUtil.get("user");
        System.out.println(user);
    }

}
