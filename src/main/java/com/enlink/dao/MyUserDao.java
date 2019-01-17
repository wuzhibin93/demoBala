package com.enlink.dao;

import com.enlink.db.MyUserSQLProvider;
import com.enlink.entity.MyUser;
import com.enlink.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:16 2019/1/2
 */
@Mapper
public interface MyUserDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "pass_word"),
            @Result(property = "sex" , column = "sex")
    })
    @Select("select * from myuser")
    List<MyUser> list();

    //MyBatis3高级注解
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "pass_word"),
            @Result(property = "sex" , column = "sex")
    })
    @SelectProvider(type = MyUserSQLProvider.class, method = "listBuId")
    List<MyUser> listById(String id);


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "user_name"),
            @Result(property = "password", column = "pass_word"),
            @Result(property = "sex" , column = "sex")
    })
    @SelectProvider(type = MyUserSQLProvider.class, method = "getByUsername")
    List<MyUser> listByUsername(@Param("username") String username,@Param("password") String password);

    @Insert({"insert into user(user_id,user_name,user_host,user_age,user_area) values (#{user_id},#{user_name},#{user_host},#{user_age},#{user_area})"})
    void insertUser(User user);
}
