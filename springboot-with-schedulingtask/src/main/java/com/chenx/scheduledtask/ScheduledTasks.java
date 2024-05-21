package com.chenx.scheduledtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author chenx
 * @description
 * @create 2022-12-08 10:10
 */
@Slf4j
@Component
public class ScheduledTasks {
    @Scheduled(fixedDelay = 5000)
    public void printLog(){
        log.info("The time is now {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
