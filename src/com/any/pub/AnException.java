package com.any.pub;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AnException An运行异常类
 * Created by avaio on 2016/12/21.
 */
public class AnException extends RuntimeException {
    Logger logger = Logger.getLogger(AnException.class.getName());
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

    public void handleException() {
        logger.log(Level.WARNING, this.getLog(), this);
    }
}
