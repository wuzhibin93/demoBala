package com.enlink.base;


import com.enlink.elasticsearch.entity.GeneralModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据
 *
 * @author changgq
 */
@Data
@NoArgsConstructor
public class PageInfo<T extends GeneralModel> {
    private List<T> data;
    private int pageIndex;
    private int pageSize;
    private long total;
    private String scrollId;
}
