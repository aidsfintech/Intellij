package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // Read - hello(), Create post()

    // Read - hello()
    // this class will sent httprequest to server by using path below
    // http://localhost:9090/api/server/hello
    // and get response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        //to check
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    // Create post()
    public UserResponse post(){
        // making uri like below,(아래 uri는 예시용일분, 적절한 uri는 아니라함)
        // http://localhost:9090/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        System.out.println(uri);

        // create 하기 위해서는 http body가 필요
        // 이을 object로 생성 후, object mapper -> json -> rest templete -> http body json으로 완성된다함
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response= restTemplate.postForEntity(uri,req,UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return  response.getBody();
    }

    // exchange method in post
    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        System.out.println(uri);

        // create 하기 위해서는 http body가 필요
        // 이을 object로 생성 후, object mapper -> json -> rest templete -> http body json으로 완성된다함
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        // plus header to above body of http
        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity,UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        System.out.println(uri);

        // 기존 UserRequest를 포괄하는 Req 형식에 맞춰서 내용물 생성, DTO에서 UserRequest가 Req 안에
        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req();
        req.setHeader(
        new Req.Header()
        );
        req.setResBody(
            userRequest
        );


        // plus header to above body of http
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "fffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        // 다만 자바 문법상 제네릭 변수에서는 Req<UserResponse>.class가 불가해서
        // 아래와같은 일종의 전처리를 통해서 아규먼트로 전달
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        // first get header and response
        // second get response's body; rbody
        //return response.getBody().getrBody();
        // 근데 단순히 내용물 확인을 위해 아래로 간소화
        return response.getBody();
    }
}
