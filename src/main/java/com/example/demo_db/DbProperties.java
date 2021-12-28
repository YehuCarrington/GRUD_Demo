package com.example.demo_db;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DbProperties {
    private final String dbDriverClass;
    private final String dbConnUrl;
    private final String dbUserName;

    public String getDbDriverClass() {
        return dbDriverClass;
    }

    public String getDbConnUrl() {
        return dbConnUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String dbPassword;

    //Just to protect info, more of a test
    //Since this database does not leave the local
    //machine
    DbProperties() throws IOException {
        Properties props = new Properties();
        String dbSettingsPropertyFile = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.dbconfig.properties";

        FileReader fReader = new FileReader(dbSettingsPropertyFile);
        props.load(fReader);

        dbDriverClass = props.getProperty("db.driver.class");
        dbConnUrl = props.getProperty("db.conn.url");
        dbUserName = props.getProperty("db.username");
        dbPassword = props.getProperty("db.password");
    }
}
