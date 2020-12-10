package com.ivanas.helloworldclient.controller;

import com.ivanas.helloworldclient.service.HelloWorldClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
public class HelloWorldClientController {

    private final HelloWorldClientService helloWorldClientService;

    @GetMapping("/message")
    public String getMessage(final @RequestParam Map<String, String> params) {
        var name = params.getOrDefault("name", "Undefined");
        return helloWorldClientService.sendMessage(name);
    }

}
