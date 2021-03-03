package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.AccountService;
import transaction.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName TransactionProxyUtils
 * @Description 事务代理工具类
 * @Author GengRui
 * @Date 2021/3/3 22:53
 */
@Component
public class TransactionProxyUtils {
    //被代理的业务类接口
    @Autowired
    private AccountService accountService;
    //提供事务管理的工具类
    @Autowired
    private TransactionManager transactionManager;

    /**
     * 获取AccountService代理对象
     *
     * @return
     */
    public AccountService getAccountService() {
        return (AccountService) Proxy.newProxyInstance(
                accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     *
                     * @param proxy     被代理的对象实例本身
                     * @param method    被代理对象正在执行的方法对象
                     * @param args      正在访问的方法参数对象
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        //
                        Object rtValue = null;
                        try {
                            // 执行操作前开启事务
                            transactionManager.beginTransaction();
                            // 执行操作
                            rtValue = method.invoke(accountService, args);
                            // 执行操作后提交事务
                            transactionManager.commit();
                            // 返回结果
                            return rtValue;
                        } catch (Exception e) {
                            // 捕捉到异常执行回滚操作
                            transactionManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            // 最终释放连接
                            transactionManager.release();
                        }
                    }
                });

    }
}
