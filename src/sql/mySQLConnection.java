package sql;

import utils.u;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionConfig {
    public String username = "root";
    public String password = "";
    public String ip = "localhost";
    public String port = "3306";
    public String database = "test";

    ConnectionConfig() {
    }

    ConnectionConfig(String user, String password, String ip, String port, String database) {
        this.username = user;
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.database = database;
    }

    ConnectionConfig(String database) {
        this.database = database;
    }
}

public class mySQLConnection {
    static Connection con = null;
    public static Connection get(ConnectionConfig c) {
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

    public static Connection get(String database) {
        return get(new ConnectionConfig(database));
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
        Connection c = mySQLConnection.get("test");
        u.log(String.valueOf(c));
    }
}

