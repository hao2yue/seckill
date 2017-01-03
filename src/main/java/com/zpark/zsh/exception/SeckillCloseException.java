package com.zpark.zsh.exception;

/**
 * 秒杀关闭异常
 * Created by Administrator on 2016/6/17 0017.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
