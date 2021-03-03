package services.impl;

import dao.AccountDao;
import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.AccountService;

/**
 * @ClassName AccountServiceImpl
 * @Description Account模块Service层实现类
 * @Author GengRui
 * @Date 2021/3/3 16:25
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 转账
     *
     * @param sourceAccount 转出账户
     * @param targetAccount 转入账户
     * @param money         转账金额
     */
    public void transfer(String sourceAccount, String targetAccount, Integer money) {
        // 查询原始账户
        Account source = accountDao.findAccountByNum(sourceAccount);
        // 查询目标账户
        Account target = accountDao.findAccountByNum(targetAccount);
        // 原始账号减钱
        source.setMoney(source.getMoney() - money);
        // 目标账号加钱
        target.setMoney(target.getMoney() + money);
        // 更新原始账号
        accountDao.updateAccount(source);
//        // 造异常
//        int i = 1 / 0;
        // 更新目标账号
        accountDao.updateAccount(target);
        System.out.println("转账完毕");
    }
}
