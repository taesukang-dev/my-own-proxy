package com.example.transaction.myTransactional.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    public String save(Member member) {
        return "ok";
    }
}
