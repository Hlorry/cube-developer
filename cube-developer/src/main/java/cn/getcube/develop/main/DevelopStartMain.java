package cn.getcube.develop.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/3
 */
@Configuration
@ComponentScan("com.getcube.develop.*")
@ImportResource(locations = {"classpath:applicationContext.xml", "classpath:spring-mvc.xml"})
@SpringBootApplication
public class DevelopStartMain {

    public static void main(String[] args) {
        SpringApplication.run(DevelopStartMain.class, args);
    }

}
