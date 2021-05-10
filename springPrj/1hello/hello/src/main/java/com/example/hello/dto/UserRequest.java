package com.example.hello.dto;

//lombok 설치 후 build.gradle에서 의존성 추가 후 아래와같이 하면 getter,setter 자동생성
//@Getter
//@Setter
//@toString
public class UserRequest {
    private String name;
    private String email;
    private int age;
}
