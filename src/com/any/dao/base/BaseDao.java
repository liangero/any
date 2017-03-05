package com.any.dao.base;

import com.any.beans.Activity;
import com.any.beans.Project;
import com.any.pub.Page;
import com.any.pub.PageOrder;
import com.any.pub.exception.MsgError;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by avaio on 2016/12/21.
 */

public abstract class BaseDao<T> {
    @Resource
    protected HibernateTemplate hibernateTemplate;

    public List<T> get(T t) {
        return hibernateTemplate.findByExample(t);
    }

    public T post(T t) {
        hibernateTemplate.save(t);
        return t;
    }

    public T put(T t) {
        hibernateTemplate.update(t);
        return t;
    }

    public void delete(T t) {
        hibernateTemplate.delete(t);
    }

    public T getById(int id){
       return hibernateTemplate.get(getEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    public Page<T> getWhenForeignerInParent(T t, Class parentClass, String joinField, Page<T> page, int parentId) {
        DetachedCriteria parentCriteria = DetachedCriteria.forClass(parentClass, "parent");
        parentCriteria.add(Restrictions.eq("parent.id", parentId));
        DetachedCriteria childCriteria = parentCriteria.createCriteria(joinField, "child");
        Example example = Example.create(t);
        childCriteria.add(example);
        parentCriteria.setProjection(Projections.rowCount());
        long total = (Long) hibernateTemplate.findByCriteria(parentCriteria).get(0);
        parentCriteria.setProjection(null);
        if (page.getPageOrder() != null) {
            for (PageOrder pageOrder : page.getPageOrder()) {
                Order order = "desc".equals(pageOrder.getDirect().toLowerCase()) ? Order.desc(pageOrder.getName())
                        : Order.asc(pageOrder.getName());
                childCriteria.addOrder(order);
            }
        }
        parentCriteria.setResultTransformer(new AliasResultTransformer(childCriteria.getAlias()));
        page.setRecordList((List<T>) hibernateTemplate.findByCriteria(parentCriteria, page.getStart(), page.getPageSize()));
        page.setTotal((int) total);
        return page;
    }


    protected Session getCurrentSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    protected abstract Class<T> getEntityClass();
}
