package entity;

/**
 * @ClassName Account
 * @Description Account模块实体类
 * @Author GengRui
 * @Date 2021/3/3 15:41
 */
public class Account {
    private Integer id;
    private String accountNum;
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
