package com.example.transaction.myTransactional;

import com.example.transaction.myTransactional.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TransactionalInterceptor implements InvocationHandler{

    private final MemberServiceImpl target;
    private final PlatformTransactionManager transactionManager;

    public TransactionalInterceptor(MemberServiceImpl target, PlatformTransactionManager transactionManager) {
        this.target = target;
        this.transactionManager = transactionManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (getMyTransactional(method) == null) return method.invoke(target, args);

        log.info("start transaction");
        TransactionStatus status = null;
        try {
            status = transactionManager.getTransaction(new DefaultTransactionDefinition());

            Object result = method.invoke(target, args);

            transactionManager.commit(status);
            log.info("commit transaction");
            return result;
        } catch (Throwable t) {
            log.info("rollback transaction");
            transactionManager.rollback(status);
            throw t;
        }
    }

    private MyTransactional getMyTransactional(Method method) {
        try {
            return AnnotationUtils.findAnnotation(target.getClass().getMethod(method.getName()), MyTransactional.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
