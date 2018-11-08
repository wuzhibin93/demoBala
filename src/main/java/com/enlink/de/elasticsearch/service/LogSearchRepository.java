package com.enlink.de.elasticsearch.service;

import com.enlink.de.elasticsearch.entity.GeneralModel;
import com.enlink.de.base.LogSearchRequest;
import com.enlink.de.base.PageInfo;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * 日志查询业务层接口
 *
 * @author changgq
 */
public interface LogSearchRepository<T extends GeneralModel> {
    /**
     * 分页查询日志
     *
     * @param request
     * @return
     * @throws Exception
     */
    PageInfo<T> findByPaging(LogSearchRequest request, String indexPrefix, QueryBuilder queryBuilder) throws Exception;

    /**
     * 生成查询条件
     *
     * @param request
     * @param indexPrefix
     * @return
     * @throws Exception
     */
    QueryBuilder createQueryBuilder(LogSearchRequest request, String indexPrefix) throws Exception;
}
