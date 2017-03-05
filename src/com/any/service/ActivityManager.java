package com.any.service;

import com.any.beans.Activity;
import com.any.dao.ActivityDao;
import com.any.pub.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * tyl
 * Created by avaio on 2017/3/4.
 */
@Service
public class ActivityManager implements BaseService<Activity> {
    @Resource
    private ActivityDao activityDao;

    @Override
    public List<Activity> get(Activity activity) {
        return activityDao.get(activity);
    }

    @Override
    public Activity post(Activity activity) {
        return activityDao.post(activity);
    }

    @Override
    public Activity put(Activity activity) {
        return activityDao.put(activity);
    }

    @Override
    public void delete(Activity activity) {
        activityDao.delete(activity);
    }

    public Page<Activity> get(Activity activity, Page<Activity> page, int parentId) {
        return activityDao.get(activity, page, parentId);
    }

    public List<Activity> get(Activity activity, int parentId) {
        return activityDao.get(activity, parentId);
    }
}
