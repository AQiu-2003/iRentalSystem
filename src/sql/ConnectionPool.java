package sql;

import java.sql.Connection;

public class ConnectionPool {
    static Connection con = null;
    static MySQLConfig c;

    public static void init(MySQLConfig c) {
        ConnectionPool.c = c;
    }

    public static Connection getConnection() {
        if (con == null) con = MySQLConnection.connect(c);
        return con;
    }
}
