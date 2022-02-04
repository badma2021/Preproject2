package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = getConnection();
    //static Long counter;

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
            preparedStatement.setLong(1, (name.length() + lastName.length() + age) * 31);
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
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM user WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            System.out.println("Remove given user from Table user");

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


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                userList.add(user);
                System.out.println(" to get all users from Table user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing the connection.");
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignore) {
                }
                if (connection != null) try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return userList;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement = null;
        String sql = "TRUNCATE TABLE user";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Table user has been cleaned Successfully");
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
}
