package com.any.action;

import com.any.action.base.BaseAction;
import com.any.beans.Project;
import com.any.pub.ResponseEntity;
import com.any.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProjectAction
 * Created by avaio on 2016/12/20.
 */
@Controller
@RequestMapping("project")
public class ProjectAction extends BaseAction<Project> {
    @Resource
    private ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(Project project) {
        List<Project> projects = projectService.get(project);
        return ResponseEntity.createOkResult(projects);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity post(Project project) {
        return ResponseEntity.createOkResult(projectService.post(project));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity put(Project project) {
        project = projectService.put(project);
        return ResponseEntity.createOkResult(project);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(Project project) {
        projectService.delete(project);
        return ResponseEntity.createOkResult(null);
    }


}
