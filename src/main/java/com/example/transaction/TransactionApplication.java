package com.example.transaction;

import com.example.transaction.config.TransactionalConfig;
import com.example.transaction.myTransactional.MyTransactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.PageableDefault;

import java.util.Arrays;

@Import(TransactionalConfig.class)
@SpringBootApplication
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

}
