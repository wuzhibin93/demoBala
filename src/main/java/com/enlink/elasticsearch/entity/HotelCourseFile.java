package com.enlink.elasticsearch.entity;

import lombok.Data;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 10:43 2018/9/11
 */
@Data
public class HotelCourseFile extends GeneralModel{
    private String attach_id;
    private String class_id;
    private String file_name;
    private String file_type;
    private String file_path;
    private String file_size;
    private String status;
    private String create_time;
    private String create_by;

    @Override
    public String toString() {
        return "HotelCourseFile{" +
                "attach_id='" + attach_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", file_name='" + file_name + '\'' +
                ", file_type='" + file_type + '\'' +
                ", file_path='" + file_path + '\'' +
                ", file_size='" + file_size + '\'' +
                ", status='" + status + '\'' +
                ", create_time='" + create_time + '\'' +
                ", create_by='" + create_by + '\'' +
                '}';
    }
}
