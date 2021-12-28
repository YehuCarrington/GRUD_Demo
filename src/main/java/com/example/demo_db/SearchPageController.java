package com.example.demo_db;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SearchPageController extends SceneController {
    @FXML private Button searchButton;
    @FXML private Button backButton;

    @FXML
    private void SearchDB(ActionEvent event) throws IOException {
        System.out.println("event = " + event);
    }
}
