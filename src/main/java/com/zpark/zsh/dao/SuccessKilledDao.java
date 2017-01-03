package com.zpark.zsh.dao;

import com.zpark.zsh.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细记录，可过滤重复
     *
     * @param seckillId
     * @param userPhone
     * @return
     */
    Integer insertSuccessKilled(@Param("seckillId") Integer seckillId,
                                @Param("userPhone") String userPhone);

    /**
     * 根据ID和手机号查询SuccessSeckilled,并携带秒杀对象产品对象实体
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Integer seckillId,
                                       @Param("userPhone") String userPhone);
}
