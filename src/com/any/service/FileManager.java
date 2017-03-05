package com.any.service;

import com.any.beans.File;
import com.any.dao.FileDao;
import com.any.pub.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * tyl
 * Created by avaio on 2017/3/5.
 */
@Service
public class FileManager implements BaseService<File>{
    @Resource
    private FileDao fileDao;
    @Override
    public List<File> get(File file) {
        return fileDao.get(file);
    }

    @Override
    public File post(File file) {
        return fileDao.put(file);
    }

    @Override
    public File put(File file) {
        return fileDao.put(file);
    }

    @Override
    public void delete(File file) {
        fileDao.delete(file);
    }

    public Page<File> get(File file, Page<File> page, int parentId) {
        return fileDao.get(file, page, parentId);
    }
}
