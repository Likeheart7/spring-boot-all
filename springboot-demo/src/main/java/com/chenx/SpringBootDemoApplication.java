package com.chenx;

import com.chenx.config.CycleConfiguration;
import com.chenx.config.OtherCycleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemoApplication.class, args);
        testConfigurationClassCycle(run);
//        MyConfig config = run.getBean(MyConfig.class);
//        Pet pp1 = config.pp();
//        Pet pp2 = config.pp();
//        System.out.println(pp2 == pp1);
//        DemoProperties bean = run.getBean(DemoProperties.class);
//        System.out.println(bean);
    }

    private static void testConfigurationClassCycle(ApplicationContext ac) {
        CycleConfiguration cycleConfiguration = ac.getBean(CycleConfiguration.class);
        OtherCycleConfiguration otherCycleConfiguration = ac.getBean(OtherCycleConfiguration.class);
        System.out.println(cycleConfiguration);
        System.out.println(otherCycleConfiguration);
        System.out.println(cycleConfiguration.otherCycleConfiguration);
    }

}
