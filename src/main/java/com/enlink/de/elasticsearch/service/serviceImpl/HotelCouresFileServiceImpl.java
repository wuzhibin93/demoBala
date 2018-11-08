package com.enlink.de.elasticsearch.service.serviceImpl;

import com.enlink.de.elasticsearch.dao.HotelCourseFileDao;
import com.enlink.de.elasticsearch.entity.HotelCourseFile;
import com.enlink.de.elasticsearch.service.HotelCourseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:03 2018/9/11
 */
@Service
public class HotelCouresFileServiceImpl implements HotelCourseFileService {
    @Resource
    private HotelCourseFileDao hotelCourseFileDao;
    @Override
    public List<HotelCourseFile> queryAll() {
        return hotelCourseFileDao.queryAllHotelCourseFile();
    }
}
