package sql;

import utils.u;

import java.sql.*;

public class RentalItem {
    public int id;
    public String type;
    public String name;
    public int numLeft, numAll, dayPrice;
    public Timestamp addTime;

    RentalItem(int id, String type, String name, int numLeft, int numAll, int dayPrice, Timestamp addTime) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.numLeft = numLeft;
        this.numAll = numAll;
        this.dayPrice = dayPrice;
        this.addTime = addTime;
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

    }
}
