package com.zpark.zsh.dto;

/**
 * 暴露秒杀地址DTO
 * Created by Administrator on 2016/6/17 0017.
 */
public class Exposer {
    //秒杀是否开启
    private Boolean exposed;
    //一种加密算法
    private String md5;
    //id
    private Integer seckillId;
    //系统当前时间
    private Long now;
    //秒杀开启时间
    private Long start;
    //秒杀结束时间
    private Long end;

    public Exposer(Boolean exposed, String md5, Integer seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(Boolean exposed, Integer seckillId, Long now, Long start, Long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(Boolean exposed, Integer seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Boolean getExposed() {
        return exposed;
    }

    public void setExposed(Boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
