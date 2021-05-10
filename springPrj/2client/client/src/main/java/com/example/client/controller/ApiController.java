package com.example.client.controller;

import com.example.client.dto.Req;
import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/client")
public class ApiController {

    @Autowired
    private RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    // resTemplateService part
    @GetMapping("/hello")
    public UserResponse getHello(){
        return restTemplateService.hello();
    }

    @GetMapping("/user")
    public UserResponse post(){
        return restTemplateService.post();
    }

    @GetMapping("/exchange")
    public UserResponse exchange(){
        return restTemplateService.exchange();
    }

    @GetMapping("/genericExchange")
    public Req<UserResponse> genericExchange(){
        return restTemplateService.genericExchange();
    }

}
