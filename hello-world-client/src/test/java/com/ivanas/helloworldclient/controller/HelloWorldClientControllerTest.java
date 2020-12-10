package com.ivanas.helloworldclient.controller;

import com.ivanas.helloworldclient.service.HelloWorldClientService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloWorldClientController.class)
@ContextConfiguration(classes = {
        HelloWorldClientService.class,
        HelloWorldClientController.class
})
class HelloWorldClientControllerTest {

    @MockBean
    private HelloWorldClientService helloWorldClientService;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void shouldReturnMessage() {
        //Given-When
        when(helloWorldClientService.sendMessage("ivanas93")).thenReturn("Hello -> ivanas93");

        //Then
        mockMvc.perform(MockMvcRequestBuilders.get("/message?name=ivanas93"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Hello -> ivanas93"));

    }

}
