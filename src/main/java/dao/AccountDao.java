package dao;

import entity.Account;

/**
 * @InterfaceName AccountDao
 * @Description Account模块Dao层
 * @Author GengRui
 * @Date 2021/3/3 15:48
 */
public interface AccountDao {
    /**
     * 更新
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 根据编号查询账户
     *
     * @param accountNum
     * @return 如果没有结果就返回null，如果结果集超过一个就抛异常，如果有唯一的一个结果就返回
     */
    Account findAccountByNum(String accountNum);
}
