package sql;

import utils.u;

import java.sql.*;

public class MySQLCMD {
    public static String[] retrieve(Connection con, String table, String[] columns, String where) {
        StringBuilder sql = new StringBuilder("SELECT ");
        for (String column : columns) {
            sql.append(column).append(",");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1) + " FROM " + table);
        if (where != null) sql.append(" WHERE ").append(where);
        try {
            PreparedStatement pstm = con.prepareStatement(sql.toString());
            ResultSet rs = pstm.executeQuery();
            String[] result = new String[columns.length];
            while (rs.next()) {
                for (int i = 0; i < columns.length; i++) {
                    result[i] = rs.getString(columns[i]);
                }
            }
            if (result[0] == null) return null;
            return result;
        } catch (SQLException e) {
            u.err("查询数据失败");
            throw new RuntimeException(e);
        }
    }

    public static String insert(Connection con, String table, String[] columns, String[] values) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + table + "(");
        for (String column : columns) {
            sql.append(column).append(",");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1) + ") VALUES(");
        for (String value : values) {
            sql.append("'").append(value).append("',");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1) + ")");
        try {
            PreparedStatement pstm = con.prepareStatement(sql.toString());
            pstm.execute();
            return "success";
        } catch (SQLException e) {
            u.err("插入数据失败");
            throw new RuntimeException(e);
        }
    }

    public static String update(Connection con, String table, String[] columns, String[] values, String where) {
        StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");
        for (int i = 0; i < columns.length; i++) {
            sql.append(columns[i]).append(" = '").append(values[i]).append("',");
        }
        sql = new StringBuilder(sql.substring(0, sql.length() - 1) + " WHERE " + where);
        try {
            PreparedStatement pstm = con.prepareStatement(sql.toString());
            pstm.execute();
            return "success";
        } catch (SQLException e) {
            u.err("更新数据失败");
            throw new RuntimeException(e);
        }
    }

    public static String delete(Connection con, String table, String where) {
        String sql = "DELETE FROM " + table + " WHERE " + where;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();
            return "success";
        } catch (SQLException e) {
            u.err("删除数据失败");
            throw new RuntimeException(e);
        }
    }

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
}

