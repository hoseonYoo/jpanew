package com.jpa.basic.repository;

import com.jpa.basic.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TeamRepositoryTest {

    @Autowired
    TeamRepository repository;

    @Test
    public void teamCRUDTest(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        repository.save(teamA);
        repository.save(teamB);

        List<Team> all = repository.findAll();
        assertThat(all.size()).isEqualTo(2);





    }


}