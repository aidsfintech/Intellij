package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @RequestMapping(path="/hi", method = RequestMethod.GET)
    public String hi(){
        return "hi based on RequsestMapping with Get";
    }
    //above annotation can choose the kinds of requestmethod like get,post blabla
    // if you want certain, you can also annoate like below

    @GetMapping(path="/hello")  //http://localhost:8080/api/get/hello
    public  String hello(){
        return  "Hello based on GetMapping";
    }

    // features of method's', not certain method; path variable, query parameter

    // path variable of methods(all)
    @GetMapping(path = "path-variable/{id}")
    public String pathVariable(@PathVariable(name = "id") String pathName){
        //by above annotation, we can make coorespond of uri and resources like 1-1 function of math
        // ~/resource/1 -> resource1, not ~/resource1
        // ~/resource/2 -> resource2
        return  pathName;
    }

    // query parametr of methods(at least get, delete)
    // the below are based on '?key1=value1&key2=value2 ~~' except front part
    // https://www.google.com/
    // search
    // ---
    // ?q=intellij
    // &ei=NNGTYMG8CYWHoAS957XADQ
    // &oq=intellij
    // &gs_lcp=Cgdnd3Mtd2l6EAMyBwgAELEDEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyBAgAEEMyAggAMgQIABBDMgQIABBDMgQIABBDOggIABCxAxCDAToFCAAQsQNQ7vIBWPn-AWCUgAJoAHACeACAAckCiAHUCZIBBzAuNi4xLjGYAQCgAQGqAQdnd3Mtd2l6wAEB
    // &sclient=gws-wiz
    // &ved=0ahUKEwjBpsiX-LTwAhWFA4gKHb1zDdgQ4dUDCA4
    // &uact=5

    // spring이 알아서 앞부분은 떼고 ? 이후 키밸류만 전달해주고,
    // 아래의 경우 대응하는 uri는 ?user=steve&email=steve@gmail.com&age=30 이다
    // 참고로 dto에 없는 변수는 그냥 날라간다고 한다.
    // 수업 중 3가지 방법이 소개되지만 제일 자주쓴다는 것만 방식만 남기고, 롬복미설치로, 주석처리
    @GetMapping("query-parma03")
    public String queryParam03(UserRequest userRequest){
//        System.out.println(userRequest.getName());
//        System.out.println(userRequest.getEmail());
//        System.out.println(userRequest.getAge());

        return  userRequest.toString();
    }
}
