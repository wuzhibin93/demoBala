package com.enlink.service;

import com.enlink.entity.Key;
import org.springframework.stereotype.Service;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:20 2018/12/25
 */
@Service
public interface KeyService {
    void insertKey(Key key);
    void testGlobal();

}
