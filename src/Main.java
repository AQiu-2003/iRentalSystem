import sql.ConnectionPool;
import sql.MySQLConfig;

public class Main {
    static MySQLConfig config = new MySQLConfig(
            "root",
            "",
            "localhost",
            3306,
            "rental"
    );

    public static void main(String[] args) {
        ConnectionPool.init(config);
    }
}
