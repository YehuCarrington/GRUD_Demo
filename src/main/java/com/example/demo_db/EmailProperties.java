package com.example.demo_db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailProperties {
    private final String emailUserName;
    private final String emailPassword;

    EmailProperties() throws IOException {
        Properties props = new Properties();
        String emailPropertyFile = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.dbconfig.properties";

        FileReader fReader = new FileReader(emailPropertyFile);
        props.load(fReader);

        emailUserName = props.getProperty("email.username");
        emailPassword = props.getProperty("email.password");
    }

}
