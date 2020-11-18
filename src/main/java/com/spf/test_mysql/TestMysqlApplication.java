package com.spf.test_mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spf.test_mysql.mapper")
public class TestMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestMysqlApplication.class, args);
    }

}
