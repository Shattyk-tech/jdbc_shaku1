package peaksoft.dao;


import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {
    Util util = new Util();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            String SQL = "CREATE TABLE if not exists users(" +
                    "id SERIAL  PRIMARY KEY NOT NULL ," +
                    "firstname VARCHAR(20)," +
                    "lastname VARCHAR(20)," +
                    "age int NOT NULL);";
            statement.execute(SQL);

            System.out.println("creat table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String SQl = "drop table users;";
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.execute(SQl);
            System.out.println("drop table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(firstname,lastname, age) VALUES(?,?,?)";
        try (Connection conn = util.connect();
             PreparedStatement prstmt = conn.prepareStatement(sql)) {
            prstmt.setString(1, name);
            prstmt.setString(2, lastName);
            prstmt.setInt(3, age);
            prstmt.executeUpdate();
            System.out.println(name + "  базага кашулду");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void removeUserById(long id) {
        String SQL = "delete  from users where  id = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, (int) id);
            statement.executeUpdate();
            System.out.println("User with id:" + id + "  deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> getAllUsers() throws SQLException {

        ArrayList<User> list = new ArrayList<>();
        String SQL = "select * from users";
        try (Connection connect = connect();
             Statement statement = connect.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
            list.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("There is no table");
        }

        return list;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        try (Connection connection = Util.connect();
             Statement statement = connection.createStatement()) {
            int i = statement.executeUpdate(SQL);
            if (i > 0) {
                System.out.println("Delete All Rows In The Table Succesfully ");
            } else {
                System.out.println(" Table alreafy empty");
            }
        } catch (SQLException e) {
            System.out.println(" there is no table");
        }


    }
}