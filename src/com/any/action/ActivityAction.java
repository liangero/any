package com.any.action;

import com.any.action.base.BaseAction;
import com.any.beans.Activity;
import com.any.pub.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ActivityAction
 * Created by avaio on 2017/1/12.
 */
@RequestMapping({"project/activity", "project/{parentId}/activity"})
@Controller
public class ActivityAction implements BaseAction<Activity> {


    @RequestMapping(method = RequestMethod.GET, path = "project/{parentId}/activity")
    @ResponseBody
    public ResponseEntity get(Activity activity, Integer parentId) {
        Assert.notNull(parentId, "parentId should be null");
        Assert.isTrue(activity.getId() > 0, "activity id should bigger than 0");
        return new ResponseEntity(activity);
    }

    @RequestMapping(method = RequestMethod.GET, path = "project/activity")
    @ResponseBody
    public ResponseEntity get(Activity activity) {
//        Assert.notNull(parentId, "parentId should be null");
        Assert.isTrue(activity.getId() > 0, "activity id should bigger than 0");
        return new ResponseEntity(activity);
    }

    public ResponseEntity post(Activity activity) {
        return null;
    }

    public ResponseEntity put(Activity activity) {
        return null;
    }

    public ResponseEntity delete(Activity activity) {
        return null;
    }
}
