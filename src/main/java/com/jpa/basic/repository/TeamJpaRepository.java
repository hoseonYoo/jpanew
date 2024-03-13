package com.jpa.basic.repository;

import com.jpa.basic.domain.Member;
import com.jpa.basic.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {
    @PersistenceContext
    private EntityManager em;

    // 저장 : 다시 리턴해주는 이유는, 팀 저장하고 다른 곳에 저장된 팀의 정보를 가져다 쓸 경우를 위해
    public Team save(Team team){
        em.persist(team);
        return team;
    }

    // 삭제
    public void delete(Team team){
        em.remove(team);
    }

    // 전체 조회
    public List<Team> findAll(){
        return em.createQuery("select t from Team t", Team.class)
                .getResultList();
    }

    // id 로 팀 1개 조회
    public Optional<Team> findById(Long id){
        Team team = em.find(Team.class, id);
        return Optional.ofNullable(team);
    }
}
