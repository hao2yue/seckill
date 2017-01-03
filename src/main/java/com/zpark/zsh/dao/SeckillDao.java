package com.zpark.zsh.dao;

import com.zpark.zsh.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface SeckillDao {
    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return 如果影响的行数>1,表示成功
     */
    Integer reduceNumber(@Param("seckillId") Integer seckillId, @Param("killTime") Date killTime);

    /**
     * 查询秒杀明细
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(Integer seckillId);

    /**
     * 查询商品列表
     *
     * @return
     */
    List<Seckill> queryAll();
}
