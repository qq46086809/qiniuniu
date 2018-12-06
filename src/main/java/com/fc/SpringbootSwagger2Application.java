package com.fc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.fc.test.mapper")
public class SpringbootSwagger2Application {
    public static void main(String[] args) {
        SpringApplication newRun= new SpringApplication(SpringbootSwagger2Application.class);
        newRun.setBannerMode(Banner.Mode.CONSOLE);
        newRun.run(args);
    }
}
