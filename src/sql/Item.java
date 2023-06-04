package sql;

import utils.u;

import java.sql.*;

public class Item {
    Connection con;
    public int id;
    public String type;
    public String name;
    public int numLeft, numAll, dayPrice;
    public Timestamp addTime;

    public Item(Connection con, int id) {
        this.con = con;
        this.id = id;
    }

    Item(String type, String name, int numAll, int dayPrice) {
        this.type = type;
        this.name = name;
        this.numLeft = numAll;
        this.numAll = numAll;
        this.dayPrice = dayPrice;
    }

    public String get(int id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, String.valueOf(this.id));
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                this.type = rs.getString("type");
                this.name = rs.getString("name");
                this.numLeft = rs.getInt("numLeft");
                this.numAll = rs.getInt("numAll");
                this.dayPrice = rs.getInt("dayPrice");
                this.addTime = rs.getTimestamp("addTime");
                return "success";
            } else {
                u.err("没有找到id为" + id + "的物品");
                return "没有找到id为" + id + "的物品";
            }
        } catch (SQLException e) {
            u.err("获取物品信息失败");
            throw new RuntimeException(e);
        }
    }

    boolean get(String type, String name) {
        String sql = "SELECT * FROM item WHERE type = ? AND name = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, this.type);
            pstm.setString(2, this.name);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.numLeft = rs.getInt("numLeft");
                this.numAll = rs.getInt("numAll");
                this.dayPrice = rs.getInt("dayPrice");
                this.addTime = rs.getTimestamp("addTime");
                print();
                return true;
            } else {
                u.err("没有找到type为" + type + "name为" + name + "的物品");
                return false;
            }
        } catch (SQLException e) {
            u.err("获取物品信息失败");
            throw new RuntimeException(e);
        }
    }

    String add() {
        if (get(this.type, this.name) == true) return "此项目已存在";
        String sql = "INSERT INTO item (type, name, numLeft, numAll, dayPrice) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, this.type);
            pstm.setString(2, this.name);
            pstm.setInt(3, this.numLeft);
            pstm.setInt(4, this.numAll);
            pstm.setInt(5, this.dayPrice);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.addTime = rs.getTimestamp("registerTime");
                print();
                return "success";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "添加项目失败";
    }

    String edit() {
        String sql = "UPDATE item SET type = ?, name = ?, numLeft = ?, numAll = ?, dayPrice = ? WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, this.type);
            pstm.setString(2, this.name);
            pstm.setInt(3, this.numLeft);
            pstm.setInt(4, this.numAll);
            pstm.setInt(5, this.dayPrice);
            pstm.setInt(6, this.id);
            int row = pstm.executeUpdate();
            if (row > 0) {
                print();
                return "success";
            } else {
                u.err("修改项目失败");
                return "修改项目失败";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String delete() {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, this.id);
            int row = pstm.executeUpdate();
            if (row > 0) {
                print();
                return "success";
            } else {
                u.err("删除项目失败");
                return "删除项目失败";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void print() {
        u.log("id: " + id + "\n" +
                "type: " + type + "\n" +
                "name: " + name + "\n" +
                "numLeft: " + numLeft + "\n" +
                "numAll: " + numAll + "\n" +
                "dayPrice: " + dayPrice + "\n" +
                "addTime: " + addTime
        );
    }

    public static void main(String[] args) {
        Connection connection = mySQLConnection.get("rental");
        Item item = new Item(connection, 1);
        item.print();
    }
}
