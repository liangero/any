package com.any.pub;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avaio on 2016/12/23.
 */
public class Page {
    private int pageIndex;
    private int pageSize;
    private List<Order> orders =new ArrayList<Order>();
    private List<Condition> conditions = new ArrayList<Condition>();
    private List recordes;
    private long total;

    public void addCondition(String name, String type, String value){
        this.conditions.add(new Condition(name, type, value));
    }

    public ResponseEntity toResponseEntity(){
        return  new ResponseEntity(recordes,  total);
    }

    class Order {
        private String name;
        private int direct;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDirect() {
            return direct;
        }

        public void setDirect(int direct) {
            this.direct = direct;
        }
    }

    class Condition {
        private String name;
        private String type;
        private String value;

        public Condition(String name, String type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }

        public Condition() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List getRecordes() {
        return recordes;
    }

    public void setRecordes(List recordes) {
        this.recordes = recordes;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
