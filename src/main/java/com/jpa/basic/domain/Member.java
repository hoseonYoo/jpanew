package com.jpa.basic.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // JPA가 관리할 객체 ( DB 테이블과 동일 )
@Getter @Setter // Entitiy에는 ToStirng 같이 쓰면 오류가 날수도 있어 가급적 사용X (@Date도 안씀)
@ToString(of = {"id","name","age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table("myMember") <- 클래스명과 동일하면 작성 안해도됨
public class Member {
    @Id // primary key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값 자동 생성
    @Column("member_id") // DB에 컬럼명을 변수명이 아닌, member_id로 지정
    private Long id;

    private String name;
    private int age;

    @ManyToOne
    @JoinColumn("id") // 조인하고자 하는 DB 컬럼명 작성
    private Team team;

    public Member (String name, int age){
        this.name = name; this.age = age;
    }
}
