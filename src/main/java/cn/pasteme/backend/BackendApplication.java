package cn.pasteme.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 白振宇 2019/9/30 00:42
 */
@SpringBootApplication
@ComponentScan({"cn.pasteme.backend", "cn.pasteme.common"})
@MapperScan({"cn.pasteme.common"})
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
