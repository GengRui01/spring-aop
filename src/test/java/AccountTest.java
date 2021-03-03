import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.AccountService;

/**
 * @ClassName AccountTest
 * @Description Account模块测试类
 * @Author GengRui
 * @Date 2021/3/3 16:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AccountTest {

    @Autowired
    @Qualifier("transactionProxyAccountService")
    private AccountService accountService;

    @Test
    public void testTransfer() {
        accountService.transfer("622200001", "622200002", 100);
    }
}
