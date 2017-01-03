package com.zpark.zsh.service.impl;

import com.zpark.zsh.dto.Exposer;
import com.zpark.zsh.dto.SeckillExecution;
import com.zpark.zsh.entity.Seckill;
import com.zpark.zsh.exception.SeckillCloseException;
import com.zpark.zsh.exception.SeckillException;
import com.zpark.zsh.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() throws Exception {
        Seckill seckill = seckillService.getById(1001);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        Integer id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        /*
        exposed=true,
        md5='cd8e58c685888317104c85235078330c',
        seckillId=1001,
        now=null,
        start=null,
        end=null
         */

    }

    @Test
    public void testExecuteSeckill() throws Exception {
        Integer id = 1001;
        String phone = "13838384382";
        String md5 = "cd8e58c685888317104c85235078330c";
        SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
        logger.info("execution={}", execution);
    }

    //集成测试代码完整逻辑，注意可重复执行
    @Test
    public void testSeckill() {
        Integer id = 1002;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.getExposed()) {
            String phone = "13838384382";
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
                logger.info("execution={}", execution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (SeckillException e) {
                logger.error(e.getMessage());
            }
        } else {
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }
}