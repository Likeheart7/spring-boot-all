package com.chenx.controller;

import com.chenx.ws.WebSocketController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WebSocketTriggerController {

    private WebSocketController webSocketController;

    @GetMapping("/ws/trigger")
    public String wsTrigger() {
        webSocketController.sendMessage();
        return "已发送";
    }
}
