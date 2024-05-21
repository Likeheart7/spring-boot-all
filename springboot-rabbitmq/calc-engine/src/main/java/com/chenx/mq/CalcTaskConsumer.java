package com.chenx.mq;

import cn.hutool.core.util.IdUtil;
import com.chenx.mapper.CalcFailMapper;
import com.chenx.pojo.CalcFailInfo;
import com.chenx.pojo.CalcTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CalcTaskConsumer {
    @Autowired
    private CalcFailMapper failMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "rorm.calc.queue"),
            exchange = @Exchange("rorm.calc.direct.exchange"),
            key = "rorm.calc"))
    public void calcListener(CalcTask calcTask) {
        log.info("接收到计算任务: {}, 即将开始计算", calcTask);
        Object proxy = AopContext.currentProxy();
        CalcTaskConsumer calcTaskConsumer = (CalcTaskConsumer) proxy;
        calcTaskConsumer.calcLogic(calcTask);
        log.info("计算任务已提交给异步线程");
//        throw new IllegalArgumentException("参数异常"); // 模拟计算异常问题
    }

    @Async("calcThreadPool")
    public void calcLogic(CalcTask calcTask) {
        try {
            TimeUnit.SECONDS.sleep(5);
            log.info("{}: 任务计算完成", Thread.currentThread().getName());
            throw new RuntimeException("异步产出一个异常");
        } catch (Exception e) {
//            出现异常，入库记录
            CalcFailInfo failInfo = new CalcFailInfo();
            failInfo.setId(IdUtil.getSnowflakeNextIdStr());
            failInfo.setTaskId(calcTask.getTaskId());
            failInfo.setStatusChange(calcTask.getStatusChange());
            failInfo.setExceptionInfo(e.toString() + Arrays.toString(e.getStackTrace()));
            failMapper.insert(failInfo);
        }
    }

}
