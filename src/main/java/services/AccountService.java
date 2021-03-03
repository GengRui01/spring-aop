package services;

/**
 * @InterfaceName AccountService
 * @Description Account模块Service层
 * @Author GengRui
 * @Date 2021/3/3 16:24
 */
public interface AccountService {
    /**
     * 转账
     *
     * @param sourceAccount 转出账户
     * @param targetAccount 转入账户
     * @param money         转账金额
     */
    void transfer(String sourceAccount, String targetAccount, Integer money);
}
