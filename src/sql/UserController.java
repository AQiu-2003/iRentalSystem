package sql;

import utils.u;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static sql.ConnectionPool.getConnection;

public class UserController {
    public static UserItem search(int id) {
        String[] result = MySQLCMD.retrieve(
                getConnection(),
                "user",
                new String[]{"id", "username", "password", "registerTime", "comment"},
                "id = " + id
        );
        if (result == null) return null;
        u.log("查询结果：" + Arrays.toString(result));
        return new UserItem(
                Integer.parseInt(result[0]),
                result[1],
                result[2],
                Timestamp.valueOf(result[3]),
                result[4]
        );
    }

    public static UserItem search(String username) {
        String[] result = MySQLCMD.retrieve(
                getConnection(),
                "user",
                new String[]{"id", "username", "password", "registerTime", "comment"},
                "username = " + username
        );
        if (result == null) return null;
        u.log("查询结果：" + Arrays.toString(result));
        return new UserItem(
                Integer.parseInt(result[0]),
                result[1],
                result[2],
                Timestamp.valueOf(result[3]),
                result[4]
        );
    }

    public static boolean check(String username, String password) {
        String[] result = MySQLCMD.retrieve(
                getConnection(),
                "user",
                new String[]{"id", "username", "password", "registerTime", "comment"},
                "username = '" + username + "' AND password = '" + password + "'"
        );
        if (result == null) return false;
        u.log("查询结果：" + Arrays.toString(result));
        return true;
    }

    public static UserItem[] getAll() {
        ArrayList<UserItem> data = new ArrayList<>();
        int maxID = MySQLCMD.getMaxId(getConnection(), "user");
        for (int i = 1; i <= maxID; i++) {
            UserItem item = search(i);
            if (item != null) data.add(item);
        }
        return data.toArray(new UserItem[0]);
    }

    public static String create(UserItem item) {
        String[] values = {
                item.username,
                item.password,
                item.comment
        };
        return MySQLCMD.insert(
                getConnection(),
                "user",
                new String[]{"username", "password", "comment"},
                values
        );
    }

    public static String update(UserItem newItem, int id) {
        String[] values = {
                String.valueOf(newItem.id),
                newItem.username,
                newItem.password,
                String.valueOf(newItem.registerTime),
                newItem.comment
        };
        return MySQLCMD.update(
                getConnection(),
                "user",
                new String[]{"id", "username", "password", "registerTime", "comment"},
                values,
                "id = " + id
        );
    }

    public static String changePassword(String newPassword, int id) {
        return MySQLCMD.update(
                getConnection(),
                "user",
                new String[]{"password"},
                new String[]{newPassword},
                "id = " + id
        );
    }

    public static String delete(int id) {
        return MySQLCMD.delete(
                getConnection(),
                "user",
                "id = " + id
        );
    }
}
