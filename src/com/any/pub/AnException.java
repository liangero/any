package com.any.pub;

/**
 * AnException An运行异常类
 * Created by avaio on 2016/12/21.
 */
public class AnException extends RuntimeException{
    private String log;

    public AnException(String msg, String log) {
        super(msg);
        this.log = log;
    }

    public AnException(String msg) {
        super(msg);
        this.log = msg;
    }

    public AnException(Exception e) {
        super(e.getMessage());
        this.log = this.getMessage();
        this.setStackTrace(e.getStackTrace());
    }

    public String getLog() {
        return log;
    }
}
