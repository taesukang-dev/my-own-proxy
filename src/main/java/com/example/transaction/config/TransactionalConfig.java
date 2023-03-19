package com.example.transaction.config;

import com.example.transaction.member.MemberRepository;
import com.example.transaction.member.MemberService;
import com.example.transaction.member.MemberServiceImpl;
import com.example.transaction.myTransactional.TransactionalInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;

@RequiredArgsConstructor
@Configuration
public class TransactionalConfig {

    private final MemberRepository memberRepository;
    private final PlatformTransactionManager transactionManager;


    @Bean
    public MemberService memberService() {
        MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);
        return (MemberService) Proxy.newProxyInstance(MemberService.class.getClassLoader(),
                new Class[]{MemberService.class},
                new TransactionalInterceptor(memberService, transactionManager));
    }

}
