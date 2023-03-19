package com.example.transaction.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class MemberServiceImplTest {
    @Autowired MemberService memberService;
    @MockBean MemberRepository memberRepository;

    @Test
    void 트랜잭션_커밋() throws Exception {
        when(memberRepository.save(any()))
                .thenReturn("ok");
        memberService.save();
    }

    @Test
    void 트랜잭션_롤백() throws Exception {
        when(memberRepository.save(any()))
                .thenThrow(new RuntimeException());

        Assertions.assertThatThrownBy(() -> memberService.save())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 트랜잭션_없는_경우() throws Exception {
        memberService.non();
    }
}