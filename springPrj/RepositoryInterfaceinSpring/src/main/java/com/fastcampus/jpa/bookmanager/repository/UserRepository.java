package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpaRepository를 상속받은 인터페이스가 있는그대로이고
 * long은 User의 id에 대응하는 변수
 *
 * 참고로, jpaRepository를 통해
 * findall() ; read table
 * 같은 메소드를 지원하고, 필요 시 상속된 객체를 들어가봄으로서 그때그때 확인
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
