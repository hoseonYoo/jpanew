package com.jpa.basic.repository;

import com.jpa.basic.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 순수 JPA 방식
// DB 처리 : insert, update, delete ...
@Repository
public class MemberJpaRepository {

    @PersistenceContext // Persistence 티어의 컨텍스트에서 em 자동 주입
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    // 삭제
    public void delete(Member member){
        em.remove(member);
    }

    // 전체 조회
    public List<Member> findAll(){
        List<Member> list = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return list;
    }

    // id로 회원 한명 조회 : 회원을 못 찾을 수 있기 때문에 Optional로 리턴
    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // member의 name으로 조회
    public List<Member> findByName(String name){
        List<Member> list = em.createQuery(
                "select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // :name (placeholder), .setParameter("placeholder명", 값)
                .getResultList();
        return list;
    }







}
