package com.example.transaction.myJsonserialize;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MyJsonServiceTest {
    @Autowired MyJson myJsonService;

    @Test
    void 객체1_serialize() throws Exception {
        MyJsonDomain myJsonDomain = new MyJsonDomain(2, "a", false);
        log.info("{}", myJsonService.writeAsString(myJsonDomain));
    }

    @Test
    void 객체2_serialize() throws Exception {
        log.info("{}", myJsonService.writeAsString(new MyJsonDomain(55, "99", false)));
    }

}