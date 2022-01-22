package peaksoft;

import peaksoft.dao.UserDaoJdbcImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);


        // реализуйте алгоритм здесь
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
//          userDaoJdbc.createUsersTable();
//           userDaoJdbc.saveUser("Shaku","Sharsheva",(byte) 42);
        //  userDaoJdbc.dropUsersTable();
        //userDaoJdbc.removeUserById(scanner.nextInt());
          userDaoJdbc.cleanUsersTable();
//        userDaoJdbc.getAllUsers();
    }
}
