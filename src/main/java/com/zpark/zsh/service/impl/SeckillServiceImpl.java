package com.zpark.zsh.service.impl;

import com.zpark.zsh.dao.SeckillDao;
import com.zpark.zsh.dao.SuccessKilledDao;
import com.zpark.zsh.dto.Exposer;
import com.zpark.zsh.dto.SeckillExecution;
import com.zpark.zsh.entity.Seckill;
import com.zpark.zsh.entity.SuccessKilled;
import com.zpark.zsh.enums.SeckillStateEnum;
import com.zpark.zsh.exception.SeckillCloseException;
import com.zpark.zsh.exception.SeckillException;
import com.zpark.zsh.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    //在Spring容器中获取Dao实例
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    //盐值，用于混淆md5,保护有效数据
    private final String slat="ahsiod9';[p23`;.jdkh#$%^&kaklsdj";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll();
    }

    @Override
    public Seckill getById(Integer seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(Integer seckillId) {
        Seckill seckill=seckillDao.queryById(seckillId);
        if (seckill==null){
            return new Exposer(false,seckillId);
        }
        //系统当前时间
        Date nowTime=new Date();

        Date startTime=  seckill.getStartTime();
        Date endTime=seckill.getEndTime();

        if(nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime()){
            return new Exposer(false,seckillId,
                    nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        //转换特定字符串的过程
        String md5=this.getMd5(seckillId);//TODO
        return new Exposer(true,md5,seckillId);
    }

    /**
     * 获取用户的md5
     * @param seckillId
     * @return
     */
    private String getMd5(Integer seckillId){
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    @Transactional
    /**
     * 使用注解控制事物的优点：
     * 1.开发团队达成一致约定，明确标注事物方法的编程风格
     * 2.保证事物方法执行时间尽可能短，
     *   不要穿插其他网络操作,RPC/HTTP请求或者剥离到事物方法外部
     * 3.不是所有方法都需要事物，如：只有一条修改操作/只读操作不需要事物控制
     */
    public SeckillExecution executeSeckill(Integer seckillId,String userPhone, String md5) throws SeckillCloseException, SeckillException{
        try {
            if (md5==null||!md5.equals(this.getMd5(seckillId))){
                throw new SeckillException("秒杀数据被重写！");
            }
            //执行秒杀逻辑：减库存+记录购买行为
            Date nowTime=new Date();
            Integer update=seckillDao.reduceNumber(seckillId,nowTime);
            if (update<=0){
                //没有更新记录，秒杀关闭
                throw new SeckillCloseException("秒杀关闭");
            }else {
                Integer insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                //秒杀成功
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
            }
        } catch (SeckillCloseException e){
            throw e;
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            //所有编译期异常转换为运行时异常
           throw new SeckillException("重复秒杀或数据写入错误");
        }
    }
}
