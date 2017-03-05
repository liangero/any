package com.any.beans;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.List;

/**
 * tyl
 * Created by avaio on 2016/12/20.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    @JSONField(serialize = false)
    private List<Activity> activityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }
}
