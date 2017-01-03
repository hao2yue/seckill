package com.zpark.zsh.enums;

/**
 * 使用枚举来表述常量数据字段
 * Created by Administrator on 2016/6/17 0017.
 */
public enum SeckillStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀关闭"),
    INNER_ERROR(-1,"重复秒杀或系统异常"),
    DATA_REWRITE(-2,"数据篡改");
    private Integer state;
    private String stateInfo;

    SeckillStateEnum(Integer state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public Integer getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateof(int index) {
        for (SeckillStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
