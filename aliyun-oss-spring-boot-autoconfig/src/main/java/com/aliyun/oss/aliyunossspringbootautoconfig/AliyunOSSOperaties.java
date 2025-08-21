package com.aliyun.oss.aliyunossspringbootautoconfig;//package org.wujunyang.Utils;

import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOSSOperaties {
    private String endpoint;
    private String bucketName;
    private String region;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
