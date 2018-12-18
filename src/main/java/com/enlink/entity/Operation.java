package com.enlink.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:28 2018/12/7
 */
@Data
public class Operation {
    private String id;                      //id
    private String car;                     //
    private String track;                   //
    private String challenge;               //
    private String esVersion;               //es版本
    private String pipeline;                //
    private String rally_version;           //esrally版本
    private String isCluster;               //是否是集群。用0和1表示，0表示不是集群(单节点)，1表示集群
    private String node_count;              //几个节点
    private String operation;               //测试类型
    private String error_rate;              //错误率
    private String unit;                    //单位
    private String min;                     //最小
    private String median;                  //中等
    private String max;                     //最大
    private String flush_time;              //刷新时间
    private String flush_time_shard;        //分片刷新时间
    private String memory_doc_value;        //文档所占内存
    private String memory_norms;            //内存规范
    private String memory_segments;         //内存分割
    private String memory_stored_field;     //存储区域
    private String memory_terms;            //存储术语
    private String old_gc_time;             //老年代GC时间
    private String young_gc_time;           //新生代GC时间
    private String refresh_time;            //刷新时间
    private String segment_count;           //合并数
    private String total_time;              //测试所花总时间
    private String total_time_per_shard;    //每个分片测试总时间
    private String translog_size;           //translog大小
    private String merge_throttle_time;
    private String merge_throttle_time_per_shard_max;
    private String merge_time;
    private String merge_time_per_shard_max;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp trial_timestamp;      //测试时间
    private Throughput throughput;

    public Operation(String id, String car, String track, String challenge, String esVersion,
                     String pipeline, String rally_version, String isCluster, String node_count,
                     String operation, String error_rate, String unit, String min, String median,
                     String max, String flush_time, String flush_time_shard, String memory_doc_value,
                     String memory_norms, String memory_segments, String memory_stored_field, String memory_terms,
                     String old_gc_time, String young_gc_time, String refresh_time, String segment_count,
                     String total_time, String total_time_per_shard, String translog_size,
                     String merge_throttle_time, String merge_throttle_time_per_shard_max,
                     String merge_time, String merge_time_per_shard_max, Timestamp trial_timestamp,
                     Throughput throughput) {
        this.id = id;
        this.car = car;
        this.track = track;
        this.challenge = challenge;
        this.esVersion = esVersion;
        this.pipeline = pipeline;
        this.rally_version = rally_version;
        this.isCluster = isCluster;
        this.node_count = node_count;
        this.operation = operation;
        this.error_rate = error_rate;
        this.unit = unit;
        this.min = min;
        this.median = median;
        this.max = max;
        this.flush_time = flush_time;
        this.flush_time_shard = flush_time_shard;
        this.memory_doc_value = memory_doc_value;
        this.memory_norms = memory_norms;
        this.memory_segments = memory_segments;
        this.memory_stored_field = memory_stored_field;
        this.memory_terms = memory_terms;
        this.old_gc_time = old_gc_time;
        this.young_gc_time = young_gc_time;
        this.refresh_time = refresh_time;
        this.segment_count = segment_count;
        this.total_time = total_time;
        this.total_time_per_shard = total_time_per_shard;
        this.translog_size = translog_size;
        this.merge_throttle_time = merge_throttle_time;
        this.merge_throttle_time_per_shard_max = merge_throttle_time_per_shard_max;
        this.merge_time = merge_time;
        this.merge_time_per_shard_max = merge_time_per_shard_max;
        this.trial_timestamp = trial_timestamp;
        this.throughput = throughput;
    }
}
