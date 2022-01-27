package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

import java.security.Provider;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserServiceImpl();
        while (true) {
            command();
            int commandNum = scanner.nextInt();
            if (commandNum == 1) {
                userService.createUsersTable();
            } else if (commandNum == 2) {
                userService.saveUser("Shattyk", "Sharsheeva", (byte) 42);
                userService.saveUser("Aijamal", "Asangazieva", (byte) 26);
                userService.saveUser("Nurisa", "Mamirayim kyzy", (byte) 19);
            } else if (commandNum == 3) {
                System.out.println(userService.getAllUsers());
            } else if (commandNum == 4) {
                System.out.println("Write id");
                userService.removeUserById(scanner.nextInt());
            } else if (commandNum == 5) {
                userService.cleanUsersTable();
            } else if (commandNum == 6) {
                userService.dropUsersTable();
            } else {
                System.out.println("Wrong enter, you have only 6 commands!");
            }
        }
    }
    public static void command() {
        System.out.println("Press 1 to create a table");
        System.out.println("Press 2 to save users");
        System.out.println("Press 3 to get all users");
        System.out.println("Press 4 to delete by id");
        System.out.println("Press 5 to clean a table");
        System.out.println("Press 6 to drop the table");
    }
}