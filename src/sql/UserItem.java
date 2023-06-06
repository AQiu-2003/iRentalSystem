package sql;

import utils.u;

import java.sql.*;

public class UserItem {
    public int id;
    public String username = null, password;
    public Timestamp registerTime;
    public String comment = null;


    public UserItem(int id, String username, String password, Timestamp registerTime, String comment) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.registerTime = registerTime;
        this.comment = comment;
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

    }
}
