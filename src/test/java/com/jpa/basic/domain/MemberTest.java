package com.jpa.basic.domain;

import com.jpa.basic.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링부트 기반 테스트를 실행할 클래스임을 명시
@Transactional // 트렌젝션 안에서 실행되어라 하는 것
@Commit // test에서는 자동 롤백이라 Commit어노테이션 추가
class MemberTest {
    // 순수 JPA 사용해서 쿼리문을 날리는 과정을 할 것임.

    // EntityManager 주입받기
    @Autowired
    EntityManager em;

    @Test
    public void testEntity() {
        // DB에 저장할 엔티티 객체 한개 생성
//        Member member = new Member();
//        member.setName("member1");
//        member.setAge(10);
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        // DB에 저장 -> 영속 상태 -> 1차 캐시에 저장
        em.persist(member1); //반드시 안에 엔티티 오브잭트 있어야함, insert 문 바로 실행 x
        em.persist(member2);

        em.flush(); // insert문 실행! Transaction 끝날 때 자동 Flush 일어남.
        em.clear(); // 영속성 컨텍스트를 비우기 (초기화 : 1차 캐시도 비워짐)


        // 조회 : find(entity 클래스(테이블), pk값)
        Member findMember1 = em.find(Member.class, 1L); // select문 실행
        Member findMember2 = em.find(Member.class, 1L); // DB까지 가지 않고 그냥 findMember1에서 불러온거에서 가져옴. 같은 객체라는걸 인식 -> JSP 라서 동일성을 인식함

        // find를 한번만 한 이유? 처음에는 1차 캐쉬에 없어서 select문으로 조회를 해왔고,
        // 두번째는 영속성 컨텍스트에 1차 캐시에 저장되어 있기 때문에 함수 자체는 한번만 호출되고 바로 1차 캐시에서 가져오는 것
        // 고로 공유하고 있음
        System.out.println("findMember1" + findMember1);
        System.out.println("findMember2" + findMember2);
        System.out.println(findMember1 == findMember2); // 주소값 동일

        // 수정 : update라고 쓰지 않았는데 update문이 나감 -> 변경 감지 dirty checking // update() 같은게 없음
        findMember1.setName("modifiedName");
        findMember1.setAge(50);

        Member updateMember = em.find(Member.class, 1L);
        System.out.println("updateMember = " + updateMember); // DB에 아직 영구반영은 아직 안되었다.

//        em.flush();
//        em.clear();

        // 삭제 : remove(entity);
        em.remove(findMember2);

        // JPQL : 일반적으로 이전에 있던 데이터를 flush로 날리고 JPQL을 실행함 왜? 이미 있던 요청이랑 같이 보내지면 꼬일수도..
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);// 이렇게 하면 m을 * 처럼 사용함
//        em.createQuery("select m.name from Member m", Member.class); // 자바의 객체처럼 쓸 수 있음
        List<Member> result = query.getResultList();

        result.forEach(m -> System.out.println(m));
//        result.forEach(System.out::println); // 위와 동일 이게 더 맞는 표기인듯


//        em.flush(); // insert문 실행! Transaction 끝날 때 자동 Flush 일어남.
    } // 이때 update 쿼리문 실행

}