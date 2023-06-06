package sql;

import utils.u;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static sql.ConnectionPool.getConnection;

public class ItemController {
    public static RentalItem search(int id) {
        String[] result = MySQLCMD.retrieve(
                getConnection(),
                "item",
                new String[]{"id", "type", "name", "numLeft", "numAll", "dayPrice", "addTime"},
                "id = " + id
        );
        if (result == null) return null;
        u.log("查询结果：" + Arrays.toString(result));
        return new RentalItem(
                Integer.parseInt(result[0]),
                result[1],
                result[2],
                Integer.parseInt(result[3]),
                Integer.parseInt(result[4]),
                Integer.parseInt(result[5]),
                Timestamp.valueOf(result[6])
        );
    }

    public static RentalItem search(String type, String name) {
        String[] result = MySQLCMD.retrieve(
                getConnection(),
                "item",
                new String[]{"id", "type", "name", "numLeft", "numAll", "dayPrice", "addTime"},
                "type = " + type + " AND name = " + name
        );
        if (result == null) return null;
        u.log("查询结果：" + Arrays.toString(result));
        return new RentalItem(
                Integer.parseInt(result[0]),
                result[1],
                result[2],
                Integer.parseInt(result[3]),
                Integer.parseInt(result[4]),
                Integer.parseInt(result[5]),
                Timestamp.valueOf(result[6])
        );
    }

    public static RentalItem[] getAll() {
        ArrayList<RentalItem> data = new ArrayList<>();
        int maxID = MySQLCMD.getMaxId(getConnection(), "item");
        for (int i = 1; i <= maxID; i++) {
            RentalItem item = search(i);
            if (item != null) data.add(item);
        }
        return data.toArray(new RentalItem[0]);
    }

    public static String create(RentalItem item) {
        String[] values = {
                String.valueOf(item.id),
                item.type,
                item.name,
                String.valueOf(item.numLeft),
                String.valueOf(item.numAll),
                String.valueOf(item.dayPrice),
                String.valueOf(item.addTime)
        };
        return MySQLCMD.insert(
                getConnection(),
                "item",
                new String[]{"id", "type", "name", "numLeft", "numAll", "dayPrice", "addTime"},
                values
        );
    }

    public static String update(RentalItem newItem, int id) {
        String[] values = {
                String.valueOf(newItem.id),
                newItem.type,
                newItem.name,
                String.valueOf(newItem.numLeft),
                String.valueOf(newItem.numAll),
                String.valueOf(newItem.dayPrice),
                String.valueOf(newItem.addTime)
        };
        return MySQLCMD.update(
                getConnection(),
                "item",
                new String[]{"id", "type", "name", "numLeft", "numAll", "dayPrice", "addTime"},
                values,
                "id = " + id
        );
    }

    public static String delete(int id) {
        return MySQLCMD.delete(
                getConnection(),
                "item",
                "id = " + id
        );
    }
}
