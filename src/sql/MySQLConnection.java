package sql;

import utils.u;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection connect(MySQLConfig c) {
        Connection con = null;
        try { // 加载数据库驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            u.log("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            u.err("数据库驱动加载失败");
            e.printStackTrace();
        }
        try { // 通过访问数据库的URL获取数据库连接对象
            con = DriverManager.getConnection("jdbc:mysql://" +
                    c.ip + ":" + c.port + "/" + c.database +
                    "?useSSL=false&serverTimezone=UTC", c.username, c.password);
            u.log("数据库连接成功");
        } catch (SQLException e) {
            u.err("数据库连接失败");
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con) {
        try {
            con.close();
            u.log("数据库连接关闭成功");
        } catch (SQLException e) {
            u.err("数据库连接关闭失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { //测试连接
        Connection c = MySQLConnection.connect(new MySQLConfig("rental"));
        u.log(String.valueOf(c));
    }
}

