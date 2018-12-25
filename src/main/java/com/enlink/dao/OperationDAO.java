package com.enlink.dao;

import com.enlink.entity.Operation;

import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:31 2018/12/7
 */
public interface OperationDAO {
    /**
     * 根据id找到Operation内容
     * @param id
     * @return
     */
    List<Operation> findById(String id);

    /**
     * 添加数据到数据库
     * @param operation
     */
    void insertOperation(Operation operation);
    void updateOperation(Operation operation);
    void deleteFromOpertaion();
}
