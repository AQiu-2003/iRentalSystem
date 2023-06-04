package sql;

import utils.u;

import java.sql.*;

public class RentalCRUD {
    Connection con;

    public static int getMaxId(Connection con, String table) {
        String sql = "SELECT MAX(id) FROM " + table;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            u.err("获取最大ID失败");
            throw new RuntimeException(e);
        }
        return 0;
    }

    RentalCRUD() {
        con = mySQLConnection.get("test");
    }
}

