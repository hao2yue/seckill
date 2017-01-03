package com.zpark.zsh.dao;

import com.zpark.zsh.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配置Spring和junit整合，junit启动时加载spring容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {
    //注入Dao依赖
    @Resource(name ="" )
    private SeckillDao seckillDao;


    @Test
    public void testQueryById() throws Exception {
        Integer id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
         List<Seckill> list=seckillDao.queryAll();
        System.out.println(list);
    }

    @Test
    public void testReduceNumber() throws Exception {
        Integer a=seckillDao.reduceNumber(1001,new Date());
        System.out.println(a);
    }

}