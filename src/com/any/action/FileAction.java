package com.any.action;

import com.any.action.base.BaseAction;
import com.any.beans.File;
import com.any.pub.Page;
import com.any.pub.PageResponseEntity;
import com.any.pub.ResponseEntity;
import com.any.service.FileManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * tyl
 * Created by avaio on 2017/3/5.
 */
@Controller
@RequestMapping("snap/{parentId}/file")
public class FileAction extends BaseAction<File> {
    @Resource
    private FileManager fileManager;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(File file, Page<File> page, @PathVariable Integer parentId) {
        return PageResponseEntity.createOkResult(fileManager.get(file, page, parentId));
    }
}
