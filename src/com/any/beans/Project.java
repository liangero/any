package com.any.beans;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.List;

/**
 * Created by avaio on 2016/12/20.
 */
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    @JSONField(serialize = false)
    private List<Activity> activities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
