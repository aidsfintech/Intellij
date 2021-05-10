package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

        @GetMapping("/hello")
        public User hello(){
            User user = new User();
            user.setName("steve");
            user.setAge(10);

            return user;
        }

        // queryParam 받는 api
//        @GetMapping("/hello")
//        public User hello(@RequestParam String name, @RequestParam int age){
//            User user = new User();
//            // 여기선 간단하게 찾는 val들 되돌려주기, 만약 DB가 있다면 전달받은 key로 속성찾고, val로 인스턴스 찾아서 리턴
//            user.setName(name);
//            user.setAge(age);
//
//            return user;
//        }

        @PostMapping("/user/{userId}/name/{userName}")
        public Req<User> post(@RequestBody Req<User> user,
                         @PathVariable int userId,
                         @PathVariable String userName,
                         @RequestHeader("x-authorization") String authorization,
                         @RequestHeader("custom-header") String customHeader
        ){
            log.info("userId : {}, userName : {}", userId,userName);
            log.info("authorization : {}, customHeader : {}", authorization,customHeader);
            log.info("client req : {}", user);

            Req<User> response = new Req<>();
            response.setHeader(
                    new Req.Header()
            );
            response.setResBody(user.getResBody());

            return response;
        }

        @GetMapping("/naver")
        public String naver(){
            String query = "중국집";

            URI uri = UriComponentsBuilder
                    .fromUriString("https://openapi.naver.com")
                    .path("/v1/search/local.json")
                    .queryParam("query","중국집")
                    .queryParam("display","10")
                    .queryParam("start","1")
                    .queryParam("sort","random")
                    .encode(Charset.forName("UTF-8"))
                    .build()
                    .toUri();
            log.info("uri : {}", uri);

            RequestEntity<Void> req = RequestEntity
                    .get(uri)
                    .header("X-Naver-Client-Id","d0kVY1DZ44rdk2XeOiYt")
                    .header("X-Naver-Client-Secret","PX7t4Mlm4m")
                    .build();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(req, String.class);
            log.info("{}",response.getStatusCode());
            log.info("{}",response.getHeaders());
            log.info("{}",response.getBody());

            return response.getBody();
        }
}
