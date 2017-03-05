package com.any.pub;

import com.any.pub.Constant.GlobalValue;

/**
 * tyl
 * Created by avaio on 2016/12/21.
 */
public class ResponseEntity {
    protected int result;
    protected Object data;

    protected ResponseEntity(int result, Object data) {
        this.result = result;
        this.data = data;
    }

    public static ResponseEntity createOkResult(Object data) {
        return new ResponseEntity(GlobalValue.RESULT_OK, data);
    }
    public static ResponseEntity createErrResult(String message) {
        return new ResponseEntity(GlobalValue.RESULT_ERR, message);
    }
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
