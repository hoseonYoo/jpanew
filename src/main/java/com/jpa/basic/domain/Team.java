package com.jpa.basic.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("team_id") // DB 컬럼명만 team_id로 설정
    private Long id;

    private String name;

}
