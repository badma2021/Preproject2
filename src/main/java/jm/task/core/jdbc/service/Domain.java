package jm.task.core.jdbc.service;

import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.SQLException;

public class Domain {

        public static void main(String[] args) throws SQLException, IOException {
            Util util= new Util();
            util.getConnection();
            //System.out.println(util.getConnection());

    }
}
