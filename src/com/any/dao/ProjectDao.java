package com.any.dao;

import com.any.beans.Project;
import com.any.dao.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by avaio on 2016/12/21.
 */
@Repository
public class ProjectDao extends BaseDao<Project> {
    @Override
    protected Class<Project> getEntityClass() {
        return Project.class;
    }
}
