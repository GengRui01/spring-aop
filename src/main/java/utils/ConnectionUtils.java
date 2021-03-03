package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName ConnectionUtils
 * @Description 数据库连接工具类
 * @Author GengRui
 * @Date 2021/3/3 16:05
 */
@Component
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    @Autowired
    private ComboPooledDataSource dataSource;

    /**
     * 获得当前线程绑定的连接
     *
     * @return
     */
    public Connection getThreadConnection() {
        try {
            // 看线程是否绑了连接
            Connection conn = tl.get();
            if (conn == null) {
                // 从数据源获取一个连接
                conn = dataSource.getConnection();
                // 和线程局部变量  绑定
                tl.set(conn);
            }
            // 返回线程连接
            return tl.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和当前线程进行解绑
     */
    public void removeConnection() {
        tl.remove();
    }
}
