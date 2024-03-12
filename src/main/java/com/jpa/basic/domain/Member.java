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
    private Long id;
    private String name;
    private int age;

    public Member (String name, int age){
        this.name = name; this.age = age;
    }
}
