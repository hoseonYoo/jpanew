package com.jpa.basic.domain;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링부트 기반 테스트를 실행할 클래스임을 명시
class MemberTest {
    // EntityManager 주입받기
    @Autowired
    EntityManager em;

    @Test
    public void testEntity(){
        // DB에 저장할 엔티티 객체 한개 생성
        Member member = new Member();
        member.setName("member1");
        member.setAge(10);

        // DB에 저장
        em.persist(member);

    }


}