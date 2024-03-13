package com.jpa.basic.repository;

import com.jpa.basic.domain.Member;
import com.jpa.basic.domain.Team;
import com.jpa.basic.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void basicTest(){
        Member member = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        repository.save(member);

        Member findMember = repository.findById(member.getId()).orElse(null);
        assertThat(findMember).isNotNull();
        assertThat(findMember).isEqualTo(member);

        List<Member> all = repository.findAll();
        assertThat(all).containsExactly(member2);
    }

    @Test
    public void queryMethodTest(){
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);
        Member member3 = new Member("member3", 30);

        repository.save(member1);
        repository.save(member2);
        repository.save(member3);

        List<String> nameList = repository.findNameList();
        nameList.forEach(n -> System.out.println("n = " + n));



//        List<Member> result = repository.findUser("member1");
//        assertThat(result.size()).isEqualTo(3);


//        List<Member> result = repository.findByNameAndAgeGreaterThan("member1", 15);
//        assertThat(result.size()).isEqualTo(2);
//        assertThat(result).contains(member2);
//        assertThat(result).contains(member3);

//        List<Member> result = repository.findByName("member1");
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0).getName()).isEqualTo("member1");
//        assertThat(result.get(0).getAge()).isEqualTo(10);

    }
    @Test
    public void memberDTOTest(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamB);

        repository.save(member1);
        repository.save(member2);

        //---------------------------------------------------

        List<MemberDTO> dtolist = repository.findMemberDTO();
        dtolist.forEach(dto -> System.out.println("dto = " + dto));


    }
}