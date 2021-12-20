package com.example.demo_db;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-page.fxml")));
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("file:C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\com\\example\\demo_db\\logo.png"));
        stage.setTitle("PPINS");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
        Application.launch(args);
    }

}
