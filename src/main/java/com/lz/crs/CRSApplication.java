package com.lz.crs;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.lz.crs.mapper")
public class CRSApplication {

    public static void main(String[] args) {
        SpringApplication.run(CRSApplication.class, args);
        log.info("---------------- Car Rent Service is started. ----------------");
    }

}
