package cn.springboot.wechat.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dfly
 * Date: 2017-08-18
 * Time: 10:25
 */
public class TestConnSQLServer {

    public static void main(String[] args) {
        String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://127.0.0.1;databaseName=lhshanhong";
        String username = "sa";
        String password = "sa2017";
        Connection connection = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(connection);
    }
}
