//package com.enlink.de.util;
//import org.frameworkset.elasticsearch.ElasticSearchHelper;
//import org.frameworkset.elasticsearch.client.ClientInterface;
//import org.springframework.stereotype.Service;
//
///**
// * @ClassName :
// * @Author Aprwu
// * @Description : TODO(管理 es rest client组件实例)
// * @Date : Created in 17:05 2018/11/13
// */
//@Service
//public class ServiceApiUtil {
//    /**
//     * 获取操作默认的ES集群的客户端的工作组件
//     * @return
//     */
//    public static ClientInterface restClient(){
//        return ElasticSearchHelper.getRestClientUtil();
//    }
//
//    /**
//     * 获取操作默认ES集群的加载dsl配置文件的客户端工具组件
//     * @return
//     */
//    public ClientInterface restDemoConfigClient(){
//        return ElasticSearchHelper.getConfigRestClientUtil("esmapper/demo.xml");
//    }
//
//    /**
//     * 获取操作log的ES集群客户端工具组件
//     * @return
//     */
//    public ClientInterface restClientLogs(){
//        return ElasticSearchHelper.getRestClientUtil("logs");
//    }
//
//    /**
//     * 获取操作logs的ES集群加载dsl配置文件的客户端工具组件
//     * @return
//     */
//    public ClientInterface restConfigClientLogs(){
//        return ElasticSearchHelper.getConfigRestClientUtil("logs","esmapper/demo.xml");
//    }
//
//}
