package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = getConnection();
static Long counter;
    public UserDaoJDBCImpl() throws SQLException, IOException {

    }

    @Override
    public void createUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "CREATE TABLE user " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR (50), " +
                " age INTEGER not NULL, " +
                " PRIMARY KEY (id))";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Table user created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing the connection.");
            if (connection != null) try {
                connection.close();
            } catch (SQLException ignore) {
            }
        }
    }

    @Override
    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "DROP TABLE user";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Table user deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing the connection.");
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
                if (connection != null) try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO user (id, name, lastName, age) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, (name.length()+lastName.length()+age)*31);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();
            System.out.println("Save new user to Table user");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing the connection.");
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ignore) {
                }
                if (connection != null) try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
