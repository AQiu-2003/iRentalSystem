package sql;

public class MySQLConfig {
    public String username = "root";
    public String password = "";
    public String ip = "localhost";
    public String port = "3306";
    public String database = "rental";

    // 无参构造函数，自带默认值
    public MySQLConfig() {
    }

    public MySQLConfig(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = String.valueOf(port);
        this.database = database;
    }

    // 仅指定数据库名
    public MySQLConfig(String database) {
        this.database = database;
    }
}
