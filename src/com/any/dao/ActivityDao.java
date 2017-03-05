package com.any.dao;

import com.any.beans.Activity;
import com.any.beans.Project;
import com.any.dao.base.AliasResultTransformer;
import com.any.dao.base.BaseDao;
import com.any.dao.base.Example;
import com.any.pub.Page;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * tyl
 * Created by avaio on 2016/12/21.
 */
@Repository
public class ActivityDao extends BaseDao<Activity> {
    @SuppressWarnings("unchecked")
    public Page<Activity> get(Activity activity, Page<Activity> page, int parentId) {
        return getWhenForeignerInParent(activity, Project.class, "activityList", page, parentId);

    }


    @SuppressWarnings("unchecked")
    public List<Activity> get(Activity activity, int parentId) {
        DetachedCriteria parentCriteria = DetachedCriteria.forClass(Project.class, "parent");
        parentCriteria.add(Restrictions.eq("parent.id", (long) parentId));
        DetachedCriteria childCriteria = parentCriteria.createCriteria("activityList", "child");
        childCriteria.addOrder(Order.desc("child.name"));
        Example example = Example.create(activity);
        childCriteria.add(example);
        parentCriteria.setResultTransformer(new AliasResultTransformer(childCriteria.getAlias()));
        return (List<Activity>) hibernateTemplate.findByCriteria(parentCriteria);
    }

    @Override
    protected Class<Activity> getEntityClass() {
        return Activity.class;
    }
}
