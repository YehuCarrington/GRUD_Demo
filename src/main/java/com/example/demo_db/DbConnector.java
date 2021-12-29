package com.example.demo_db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection getConnection() throws IOException, SQLException {
        DbProperties dbProps = new DbProperties();
        return DriverManager.getConnection(dbProps.getDbConnUrl(), dbProps.getDbUserName(), dbProps.getDbPassword());
    }
}
