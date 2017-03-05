package com.any.service;

import com.any.beans.Project;
import com.any.dao.ProjectDao;
import com.any.pub.AnWarn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by avaio on 2016/12/21.
 */
@Service
public class ProjectService implements BaseService<Project> {
    @Resource
    private ProjectDao projectDao;

    public List<Project> get(Project project) {
        return projectDao.get(project);
    }

    public Project post(Project project) {
        return projectDao.post(project);
    }

    public Project put(Project project) {
        return projectDao.put(project);
    }

    public void delete(Project project) {
        projectDao.delete(project);
    }

    public Project doTest() {
        Project project = this.post(new Project());
        int a = 1;
        if (a >= 1)
            throw new AnWarn();
        return project;

    }
}
