package com.chenx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenx.pojo.CalcFailInfo;
import org.springframework.amqp.core.Message;

public interface CalcFailService extends IService<CalcFailInfo> {

    void saveFailMessageInfo(Message message, String traceId);
}
