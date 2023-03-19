package com.example.transaction.member;

import com.example.transaction.myTransactional.MyTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @MyTransactional
    @Override
    public void save() {
        String save = memberRepository.save(new Member("name", "addr"));
        log.info("the result is {}", save);
    }

    @Override
    public void non() {
        log.info("non");
    }
}
