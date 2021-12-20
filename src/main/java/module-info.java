module com.example.demo_db {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo_db to javafx.fxml;
    exports com.example.demo_db;
}