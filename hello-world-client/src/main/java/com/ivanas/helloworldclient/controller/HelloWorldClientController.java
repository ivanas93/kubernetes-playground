package com.ivanas.helloworldclient.controller;

import com.ivanas.helloworldclient.service.HelloWorldClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class HelloWorldClientController {
    private final HelloWorldClientServiceImpl helloWorldClientServiceImpl;

    @GetMapping("/message")
    public String getMessage(final @RequestParam Map<String, String> params) {
        var name = params.getOrDefault("name", "Undefined");
        return helloWorldClientServiceImpl.sendMessage(name);
    }

}
