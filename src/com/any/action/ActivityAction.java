package com.any.action;

import com.any.action.base.BaseAction;
import com.any.beans.Activity;
import com.any.pub.Page;
import com.any.pub.PageResponseEntity;
import com.any.pub.ResponseEntity;
import com.any.service.ActivityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ActivityAction
 * Created by avaio on 2017/1/12.
 */
@Controller
@RequestMapping("project/{parentId}/activity")
public class ActivityAction extends BaseAction<Activity> {
    @Resource
    private ActivityManager activityManager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get(Activity activity, Page<Activity> page, @PathVariable Integer parentId) {
        if (isPageAble(page)){
            return PageResponseEntity.createOkResult(activityManager.get(activity, page, parentId));
        }
        return ResponseEntity.createOkResult(activityManager.get(activity, parentId));
    }

    private boolean isPageAble(Page<Activity> page) {
        return page != null && page.getPageIndex() != null && page.getPageSize() != null;
    }

}
