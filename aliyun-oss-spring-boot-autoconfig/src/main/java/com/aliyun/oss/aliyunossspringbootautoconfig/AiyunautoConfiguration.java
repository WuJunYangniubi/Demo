package com.aliyun.oss.aliyunossspringbootautoconfig;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@EnableConfigurationProperties(AliyunOSSOperaties.class)
@Configuration
public class AiyunautoConfiguration {
    @Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSOperaties aliyunOSSOperaties)
    {
        return new AliyunOSSOperator(aliyunOSSOperaties);
    }

}
