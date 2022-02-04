package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        UserDaoJDBCImpl usd=new UserDaoJDBCImpl();
       // usd.dropUsersTable();
        //usd.createUsersTable();
       // usd.saveUser("Carolina","Smith", (byte) 27);
       // usd.removeUserById(1116);
        //usd.getAllUsers();
       usd.cleanUsersTable();
    }
}
