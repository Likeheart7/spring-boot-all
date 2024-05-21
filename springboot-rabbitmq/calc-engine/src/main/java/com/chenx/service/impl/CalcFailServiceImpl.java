package com.chenx.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenx.mapper.CalcFailMapper;
import com.chenx.pojo.CalcFailInfo;
import com.chenx.pojo.CalcTask;
import com.chenx.service.CalcFailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class CalcFailServiceImpl extends ServiceImpl<CalcFailMapper, CalcFailInfo> implements CalcFailService {

    /**
     * 异常消息入库信息
     * @param message 消息具体内容
     * @param traceId 日志对应traceId
     */
    @SneakyThrows
    @Override
    public void saveFailMessageInfo(Message message, String traceId) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        ObjectMapper objectMapper = new ObjectMapper();
        CalcTask calcTask = objectMapper.readValue(new String(message.getBody(), StandardCharsets.UTF_8), CalcTask.class);
//        设置异常追踪信息
        CalcFailInfo failInfo = new CalcFailInfo();
        failInfo.setId(IdUtil.getSnowflakeNextIdStr());
        failInfo.setTaskId(calcTask.getTaskId());
        failInfo.setTraceId(traceId);
        failInfo.setStatusChange(calcTask.getStatusChange());
        failInfo.setExceptionInfo(headers.get("x-exception-message").toString());
        failInfo.setFromExchange(headers.get("x-original-exchange").toString());
        failInfo.setFromBindingKey(headers.get("x-original-routingKey").toString());
//        入库
        this.save(failInfo);
    }
}
