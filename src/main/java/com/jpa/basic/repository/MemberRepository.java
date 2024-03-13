package com.jpa.basic.repository;

import com.jpa.basic.domain.Member;
import com.jpa.basic.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // name 값으로 회원들 조회
    List<Member> findByName(String name);
    List<Member> findByNameAndAgeGreaterThan(String name, int age);

    @Query("select m from Member m where m.name = :name")
    List<Member> findUser(@Param("name") String name);

    //단순 값 조회
    @Query("select m.name from Member m")
    List<String> findNameList();

    //DTO로 조회
    // SQL : select m.id, m.name, t.name from Member m, Team t where m.team_id=t.id
    // ANSI : select m.id, m.name, t.name from Member m join Team t on m.team_id = t.id;
    // JPQL
    @Query("select new com.jpa.basic.dto.MemberDTO(m.id, m.name, t.name) from Member m join m.team t")
    List<MemberDTO> findMemberDTO();


}
