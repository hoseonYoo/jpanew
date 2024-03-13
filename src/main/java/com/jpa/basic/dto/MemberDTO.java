package com.jpa.basic.dto;

import lombok.Data;
// SQL : select m.id, m.name, t.name from Member m, Team t where m.team_id=t.id
// ANSI : select m.id, m.name, t.name from Member m join Team t on m.team_id = t.id;
@Data
public class MemberDTO {
    private Long id;
    private String name;
    private String teamName;
    // 생성자
    public MemberDTO(Long id, String name, String teamName){
        this.id = id; this.name = name; this.teamName = teamName;
    }
}
