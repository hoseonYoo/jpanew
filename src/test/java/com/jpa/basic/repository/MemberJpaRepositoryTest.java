package com.jpa.basic.repository;

import com.jpa.basic.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository repository;

    @Test
    public void basicTest() {
        // 회원 entity 한개 저장
        Member member = new Member("member1", 10);
        Member savedMember = repository.save(member);
        System.out.println("savedMember = " + savedMember);

        Member findMember = repository.findById(savedMember.getId()).orElse(null);
        assertThat(findMember).isEqualTo(savedMember); // 같은 영속성컨텍스트라 주소 같음

        List<Member> all = repository.findAll();
        assertThat(all).containsExactly(findMember); // 전체 리스트안에 findMember가 있는지 체크

        List<Member> memberByName = repository.findByName("member1");
        assertThat(memberByName).containsExactly(findMember);

    }


}