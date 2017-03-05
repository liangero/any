package com.any.dao;

import com.any.beans.File;
import com.any.beans.Snap;
import com.any.dao.base.BaseDao;
import com.any.pub.Page;
import org.springframework.stereotype.Repository;

/**
 * tyl
 * Created by avaio on 2017/3/5.
 */
@Repository
public class FileDao extends BaseDao<File> {
    @Override
    protected Class<File> getEntityClass() {
        return File.class;
    }

    public Page<File> get(File file, Page<File> page, int parentId) {
        return getWhenForeignerInParent(file, Snap.class, "fileList", page, parentId);
    }
}
