package com.enlink.elasticsearch.controller;

import org.apache.http.HttpHost;
import org.elasticsearch.Build;
import org.elasticsearch.Version;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.ClusterName;

import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 13:18 2018/9/20
 */
public class ESInfo {
    public static void main(String[] args) throws IOException {
        info();
    }
    public static void info() throws IOException {
        RestHighLevelClient client = getElastic();
        MainResponse info = client.info();
        ClusterName clusterName = info.getClusterName();
        String clusterUuid = info.getClusterUuid();
        String nodeName = info.getNodeName();
        Version version = info.getVersion();
        Build build = info.getBuild();
        System.out.println("cluster name:"+clusterName);
        System.out.println("cluster uuid:"+clusterUuid);
        System.out.println("node name:"+nodeName);
        System.out.println("node version:"+version);
        System.out.println("node name:"+nodeName);
        System.out.println("build info:"+build);
        client.close();

    }
    private static RestHighLevelClient getElastic() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.63.179", 9200, "http")
                        //,new HttpHost("localhost", 9200, "http")
                )
        );
    }
}
