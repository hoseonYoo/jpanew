package com.jpa.basic.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id") // DB 컬럼명만 team_id로 설정
    private Long id;
    private String name;

    // 양방향
    @OneToMany(mappedBy = "team") // 반대쪽 객체 변수명
    private List<Member> members = new ArrayList<>(); // 초기화
    // 테스트시 편의를 위해 생성
    public Team(String name){
        this.name = name;
    }
}
