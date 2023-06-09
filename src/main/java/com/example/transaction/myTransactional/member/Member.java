package com.example.transaction.myTransactional.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String addr;

    public Member(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
}
