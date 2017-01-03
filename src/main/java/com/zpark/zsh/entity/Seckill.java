package com.zpark.zsh.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class Seckill implements Serializable{
    private Integer seckillId;
    private String name;
    private Integer seckillNum;
    private Date startTime;
    private Date endTime;
    private Date createTime;



    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeckillNum() {
        return seckillNum;
    }

    public void setSeckillNum(Integer seckillNum) {
        this.seckillNum = seckillNum;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", seckillNum=" + seckillNum +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
