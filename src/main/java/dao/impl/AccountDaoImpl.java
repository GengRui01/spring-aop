package dao.impl;

import dao.AccountDao;
import entity.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utils.ConnectionUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName AccountDaoImpl
 * @Description Account模块Dao层实现类
 * @Author GengRui
 * @Date 2021/3/3 15:49
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    // 数据库查询工具类
    @Autowired
    private QueryRunner runner;
    // 数据库连接工具类
    @Autowired
    private ConnectionUtils connectionUtils;

    /**
     * 更新
     *
     * @param account
     */
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),
                    "update account set accountNum=?,money=? where id=?",
                    account.getAccountNum(), account.getMoney(), account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据编号查询账户
     *
     * @param accountNum
     * @return 如果没有结果就返回null，如果结果集超过一个就抛异常，如果有唯一的一个结果就返回
     */
    public Account findAccountByNum(String accountNum) {
        List<Account> accounts = null;
        try {
            accounts = runner.query(connectionUtils.getThreadConnection(),
                    "select * from account where accountNum = ? ",
                    new BeanListHandler<Account>(Account.class),
                    accountNum);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (accounts == null || accounts.size() == 0) {
            // 如果没有结果就返回null
            return null;
        } else if (accounts.size() > 1) {
            // 如果结果集超过一个就抛异常
            throw new RuntimeException("结果集不唯一，数据有问题");
        } else {
            // 如果有唯一的一个结果就返回
            return accounts.get(0);
        }
    }
}
