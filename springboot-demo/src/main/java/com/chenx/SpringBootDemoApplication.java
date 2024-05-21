package com.chenx;

import com.chenx.config.MyConfig;
import com.chenx.pojo.Pet;
import com.chenx.properties.DemoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author chenx
 * @description
 * @create 2022-12-02 9:31
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootDemoApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemoApplication.class, args);
//        MyConfig config = run.getBean(MyConfig.class);
//        Pet pp1 = config.pp();
//        Pet pp2 = config.pp();
//        System.out.println(pp2 == pp1);
//        DemoProperties bean = run.getBean(DemoProperties.class);
//        System.out.println(bean);
    }
}
