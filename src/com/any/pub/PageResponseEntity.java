package com.any.pub;

import com.any.pub.Constant.GlobalValue;

/**
 * tyl
 * Created by avaio on 2017/3/5.
 */
public class PageResponseEntity extends ResponseEntity {
    private int total;

    protected PageResponseEntity(int result, Object data, int total) {
        super(result, data);
        this.total = total;
    }

    public static PageResponseEntity createOkResult(Page<?> page){
        return new PageResponseEntity(GlobalValue.RESULT_OK, page.getRecordList(), page.getTotal());
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
