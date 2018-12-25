package com.enlink.elasticsearch.controller;

import com.enlink.config.ElasticsearchConfig;
import com.enlink.elasticsearch.entity.Ids;
import com.enlink.response.AjaxResults;
import com.enlink.response.ResultCode;
import com.enlink.response.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:52 2018/8/31
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ElasticsearchConfig esConfig;

//    @RequestMapping(value = "/add",method = RequestMethod.POST, consumes = {"application/json"})
//    public AjaxResults add(@RequestBody User users) throws IllegalAccessException {
//        if (null == users) {
//            return Results.errorOf(ResultCode.THE_REQUIRED_PARAMETERS_ARE_MISSING, "");
//        }
//
//
//        return Results.resultOf(ResultCode.OK, "");
//    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST, consumes = {"application/json"})
    public AjaxResults delete(@RequestBody Ids ids){
        System.out.println(ids);
        return Results.resultOf(ResultCode.OK,"");
    }

    /**
     * 有效的Api
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete2/{id}",method = RequestMethod.GET)
    public AjaxResults delete2(@PathVariable String id) throws Exception {
        List<HttpHost> httpHosts = new ArrayList<>();
        System.out.println(esConfig.genHosts().length);
        for (String host : esConfig.genHosts()) {
            httpHosts.add(HttpHost.create(host));
        }

        for (HttpHost httpHost : httpHosts) {
            System.out.println(httpHost);
        }

        System.out.println(id);
        return Results.resultOf(ResultCode.OK,id);
    }
}
