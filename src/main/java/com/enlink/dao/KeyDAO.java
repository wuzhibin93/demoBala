package com.enlink.dao;

import com.enlink.entity.Key;
import org.springframework.stereotype.Repository;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 11:19 2018/12/25
 */
@Repository
public interface KeyDAO {
    void insertKey(Key key);

}
