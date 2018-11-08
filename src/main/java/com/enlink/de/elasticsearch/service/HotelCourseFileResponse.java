package com.enlink.de.elasticsearch.service;

import com.enlink.de.elasticsearch.entity.HotelCourseFile;
import com.enlink.de.elasticsearch.entity.SearchCond;

import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:06 2018/9/11
 */
public interface HotelCourseFileResponse {
    List<HotelCourseFile> findByCondition(SearchCond searchCond);
}
