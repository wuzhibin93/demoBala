/* 文件名: ApplicationRun.java
 *
 * 作者: 常官清 (changguanqing@enlink.cn)
 * 描述: 本文件为程序入口
 *
 * Copyright @2018 Enlink, All Rights Reserved.
 */
package com.enlink.de.src.main.java.com.enlink;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.enlink"})
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class, args);
    }
}
