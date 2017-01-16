package com.any.pub;

/**
 * Created by avaio on 2016/12/21.
 */
public class ResponseEntity {
    private boolean success;
    private Object data;
    private Long total = null;
    public ResponseEntity(Object data) {
        this.success = true;
        this.data = data;
    }

    public ResponseEntity(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public ResponseEntity(Object data, Long total) {
        this.data = data;
        this.total = total;
    }

    public ResponseEntity() {
        this("");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
