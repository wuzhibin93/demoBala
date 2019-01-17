package com.enlink.db;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(根据复杂需求来动态生成SQL)
 * @Date : Created in 13:28 2019/1/2
 */
public class MyUserSQLProvider {
    /**
     * 手动编写SQL
     * @return
     */
    public String list(){
        return "select * from myuser";
    }
    public String listBuId(String id){
        return "select * from myuser where id = #{id}";
    }

    public String getByUsername(@Param("username") String username, @Param("password")String password){
        return new SQL(){{
            SELECT("*");
            FROM("myuser");
            if (username != null && password != null){
                WHERE("username like #{username} and password like #{password}");
            }else {
                WHERE("1=2");
            }
        }}.toString();
    }
}
