package transaction;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.ConnectionUtils;

import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description 事务管理器
 * @Author GengRui
 * @Date 2021/3/3 22:40
 */
@Component
@Aspect
public class TransactionManager {
    // 数据库连接工具类
    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* services.*.*(..))")
    private void transactionPointcut() {
    }

    /**
     * 开启事务
     */
    @Before("transactionPointcut()")
    public void beginTransaction() {
        try {
            System.out.println("开启事务");
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    @AfterReturning("transactionPointcut()")
    public void commit() {
        try {
            System.out.println("提交事务");
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    @AfterThrowing("transactionPointcut()")
    public void rollback() {
        try {
            System.out.println("回滚事务");
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    @After("transactionPointcut()")
    public void release() {
        try {
            System.out.println("释放连接");
            connectionUtils.getThreadConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionUtils.removeConnection();
    }
}
