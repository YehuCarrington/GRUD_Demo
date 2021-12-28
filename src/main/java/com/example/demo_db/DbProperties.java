package com.example.demo_db;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DbProperties {
    public String dbDriverClass;
    public String dbConnUrl;
    public String dbUserName;
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
