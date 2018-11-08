package com.enlink.de.elasticsearch.dao;

import com.enlink.de.elasticsearch.entity.HotelCourseFile;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 10:47 2018/9/11
 */
public interface HotelCourseFileDao {
    List<HotelCourseFile> queryAllHotelCourseFile();
    
    List<HotelCourseFile> queryFT();
}
