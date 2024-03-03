import java.util.ArrayList;

public class UserManager {
    private static ArrayList<User> users = new ArrayList<User>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void addUserByFields(String userName, String PassWord) {
        User user = new User(userName, PassWord);
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void removeUserByFields(String userName, String PassWord) {
        User user = new User(userName, PassWord);
        users.remove(user);
    }
}
