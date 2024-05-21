package com.chenx.springcloudwithnacos.com.chenx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RefreshScope
public class DemoController {
    @Value("${cust.path}")
    private String path;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/getFile")
    public String getName() throws IOException {
        List<String> content = Files.readAllLines(Paths.get(path + "node.txt"));
        return content.toString();
    }

    @GetMapping("/getService")
    public String getService() {
        String result = restTemplate.getForObject("http://service/getFile", String.class);
        return result;
    }
}
