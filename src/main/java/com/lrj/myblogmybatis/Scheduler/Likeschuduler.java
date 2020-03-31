package com.lrj.myblogmybatis.Scheduler;

import com.lrj.myblogmybatis.common.LikeUtils;
import com.lrj.myblogmybatis.dto.BlogQuery;
import com.lrj.myblogmybatis.dto.LikeData;
import com.lrj.myblogmybatis.enums.BType;
import com.lrj.myblogmybatis.service.AdminBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/22 22:55
 */

@Component
public class Likeschuduler {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AdminBlogService adminBlogService;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 一天拉取数据一次
     */
    @Scheduled(cron = "0/1 0 0-23 * * ? ")
    public void LikePersistenceMysql(){
        List<BlogQuery> allBlogs = adminBlogService.getAllBlogs();
        for (BlogQuery blogQuery : allBlogs){
            long id = blogQuery.getId();
            String key = LikeUtils.getLikeKey(BType.LIKED_ARTICLE, id);
            if (redisTemplate.hasKey(key)){
                Set keys = redisTemplate.opsForHash().keys(key);
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()){
                    Object next = iterator.next();
                    Object flag = redisTemplate.opsForHash().get(key, next);
                    if (flag.equals(0)){ //没有拉取到mysql
                        @NotNull Long userId = (Long) next;
                        LikeData likeData = new LikeData();
                        likeData.setBlogId(id);
                        likeData.setUserId(userId);
                        try {
                            logger.info("开始持久化");
                            adminBlogService.saveLike(likeData);
                            redisTemplate.opsForHash().delete(key, next);
                            redisTemplate.opsForHash().put(key, next, 1);
                        }
                        catch (Exception e){
                            logger.error("持久化过程出错");
                        }finally {
                            logger.info("持久化一条数据完成");
                        }
                    }
                    else {
                        continue;
                    }

                }
            }
        }


    }
}
