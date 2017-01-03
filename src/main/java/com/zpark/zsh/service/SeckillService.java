package com.zpark.zsh.service;

import com.zpark.zsh.dto.Exposer;
import com.zpark.zsh.dto.SeckillExecution;
import com.zpark.zsh.entity.Seckill;
import com.zpark.zsh.exception.SeckillCloseException;
import com.zpark.zsh.exception.SeckillException;

import java.util.List;

/**
 * 站在"使用者"角度去设计Service接口
 * 1.方法定义粒度
 * 2.参数，越简练越直接越好
 * 3.返回类型友好（entity/dto/异常）
 * Created by Administrator on 2016/6/17 0017.
 */
public interface SeckillService {

    /**
     *查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询一条秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(Integer seckillId);

    /**
     * 秒杀开启时输出秒杀地址，否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(Integer seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(Integer seckillId,String userPhone, String md5) throws SeckillCloseException,SeckillException;
}
