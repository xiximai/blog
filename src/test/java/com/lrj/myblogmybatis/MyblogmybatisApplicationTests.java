package com.lrj.myblogmybatis;

import com.lrj.myblogmybatis.common.LikeUtils;
import com.lrj.myblogmybatis.dto.BlogQuery;
import com.lrj.myblogmybatis.dto.LikeData;
import com.lrj.myblogmybatis.enums.BType;
import com.lrj.myblogmybatis.pojo.User;
import com.lrj.myblogmybatis.service.AdminBlogService;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class MyblogmybatisApplicationTests {

    @Autowired
    StringEncryptor encryptor;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        String url = encryptor.encrypt("jdbc:mysql://:3306/mybatisblog?useUnicode=true&serverTimezone=UTC&characterEncoding=utf-8");
        /*String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("888888");*/
        System.out.println(url);
       /* System.out.println(name);
        System.out.println(password);*/
        /*Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);*/
    }

    @Test
    void redisTest() {
        redisTemplate.opsForHash().put("test", "12", 33);
        Long size = redisTemplate.opsForHash().size("test");
        System.out.println(size);
        User user = new User();
        user.setId((long) 11111111);
        user.setNickname("111");
        redisTemplate.opsForHash().put("test", "23", user);
        Object test = redisTemplate.opsForHash().get("test", "23");
        System.out.println(test);
        System.out.println(test.toString());
        redisTemplate.opsForHash().put(LikeUtils.getLikeKey(BType.LIKED_ARTICLE, 2), 3, 0);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminBlogService adminBlogService;


    @Test
    void Test2() {
        List<BlogQuery> allBlogs = adminBlogService.getAllBlogs();
        for (BlogQuery blogQuery : allBlogs) {
            long id = blogQuery.getId();
            String key = LikeUtils.getLikeKey(BType.LIKED_ARTICLE, id);
            if (redisTemplate.hasKey(key)) {
                Set keys = redisTemplate.opsForHash().keys(key);
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    Object flag = redisTemplate.opsForHash().get(key, next);
                    if (flag.equals(0)) { //没有拉取到mysql
                        @NotNull Long userId = (Long) next;
                        LikeData likeData = new LikeData();
                        likeData.setBlogId(id);
                        likeData.setUserId(userId);
                        try {
                            logger.info("开始持久化");
                            adminBlogService.saveLike(likeData);
                            redisTemplate.opsForHash().delete(key, next);
                            redisTemplate.opsForHash().put(key, next, 1);
                        } catch (Exception e) {
                            logger.error("持久化过程出错");
                        } finally {
                            logger.info("持久化一条数据完成");
                        }
                    } else {
                        continue;
                    }

                }
            }
        }

    }

    @Test
    void Test3(){
        redisTemplate.opsForValue().set("11","22",60, TimeUnit.SECONDS);
    }

    @Test
    void Test4(){

            Random random = new Random();
            int x = random.nextInt(42);
            System.out.println(x);
            String s ="https://allpassaway.oss-cn-shenzhen.aliyuncs.com/images/tx";
            String s1 = x + ".jpg";
            System.out.println(s1);
            String s2 = s.concat(s1);
            System.out.println(s2);
    }
}
