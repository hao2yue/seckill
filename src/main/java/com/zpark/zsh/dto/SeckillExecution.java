package com.zpark.zsh.dto;

import com.zpark.zsh.entity.SuccessKilled;
import com.zpark.zsh.enums.SeckillStateEnum;

/**
 * 用来封装秒杀执行后的结果数据
 * Created by Administrator on 2016/6/17 0017.
 */
public class SeckillExecution {
    //Id
    private Integer seckillId;
    //秒杀结果执行状态
    private Integer state;
    //状态标示
    private String stateInfo;
    //秒杀成功对象
    private SuccessKilled successKilled;

    public SeckillExecution(Integer seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state =stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(Integer seckillId,SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
