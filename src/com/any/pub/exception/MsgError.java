package com.any.pub.exception;

/**
 * tyl 返回前端错误报告
 * Created by avaio on 2017/3/4.
 */
public class MsgError extends RuntimeException{
    private String info;

    public MsgError(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
