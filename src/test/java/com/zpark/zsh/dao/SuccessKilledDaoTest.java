package com.zpark.zsh.dao;

import com.zpark.zsh.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {
    @Resource(name = "")
    SuccessKilledDao successKilledDao;
    @Test
    public void testInsertSuccessKilled() throws Exception {
        int a =  successKilledDao.insertSuccessKilled(1001,"13838384380");
        System.out.println("插入了"+a+"条数据");
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(1001,"13838384380");
        System.out.println(successKilled);
    }
}