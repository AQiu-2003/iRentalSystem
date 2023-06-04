package sql;

import utils.u;

import java.sql.*;

public class User {
    Connection con;
    public int id;
    public String username = null, password;
    public Timestamp registerTime;
    public String comment = null;

    public User(Connection con, String username, String password) {
        this.con = con;
        this.username = username;
        this.password = password;
    }

    public User(Connection con, int id) {
        this.con = con;
        this.id = id;
    }

    public User(Connection con, String username) {
        this.con = con;
        this.username = username;
    }

    String add(String comment) {
        if (get(this.username)) return "用户已存在";
        registerTime = new Timestamp(System.currentTimeMillis());
        this.comment = comment;
        String sql = "INSERT INTO account (username, password, comment) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, this.username);
            pstm.setString(2, this.password);
            pstm.setString(3, this.comment);
            int row = pstm.executeUpdate();
            u.log("新增用户成功：" + row + "行受影响");
            //更新ID
            get(this.username);
            print();
            return "success";
        } catch (SQLException e) {
            u.err("新增用户失败");
            throw new RuntimeException(e);
        }
    }

    public String get(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, String.valueOf(id));
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.registerTime = rs.getTimestamp("registerTime");
                this.comment = rs.getString("comment");
                return "success";
            }
        } catch (Exception e) {
            u.err("获取用户信息失败");
            e.printStackTrace();
        }
        return "用户不存在";
    }

    boolean get(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, this.username);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.registerTime = rs.getTimestamp("registerTime");
                this.comment = rs.getString("comment");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String checkLogin() {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, this.username);
            pstm.setString(2, this.password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.registerTime = rs.getTimestamp("registerTime");
                this.comment = rs.getString("comment");
                u.log("登录成功：" + this.username);
                return "success";
            } else {
                u.log("登录失败");
                return "用户名或密码错误";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean changePasswd(String newPasswd) {
        String sql = "UPDATE account SET password = ? WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, newPasswd);
            pstm.setString(2, String.valueOf(id));
            int row = pstm.executeUpdate();
            if (row > 0) {
                u.log("修改密码成功");
                return true;
            } else {
                u.log("修改密码失败");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String delete() {
        String sql = "DELETE FROM account WHERE id = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, String.valueOf(id));
            int row = pstm.executeUpdate();
            if (row > 0) {
                u.log("删除用户成功");
                return "success";
            } else {
                u.log("删除用户失败");
                return "用户不存在";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void print() {
        u.log("id: " + id + "\n" +
                "username: " + username + "\n" +
                "password: " + password + "\n" +
                "registerTime: " + registerTime + "\n" +
                "comment: " + comment
        );
    }

    public static void main(String[] args) {
        Connection connection = mySQLConnection.get("rental");
//        User user1 = new User(1);
//        user1.get(mySQLConnection.get("rental"), 1);
//        user1.print();
//        User user2 = new User("user2", "password2");
//        user2.add(mySQLConnection.get("rental"), "user2");
        User user = new User(connection, "test", "114514");
        user.checkLogin();
    }
}
