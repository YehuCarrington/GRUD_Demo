package com.example.demo_db;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EntryPageController extends SceneController implements Initializable {

    @FXML private ChoiceBox<String> statusChoiceBox;
    private final String[] statusChoice = { "Open", "On Hold", "Completed", "Closed" };

    @FXML private Button clearButton;
    @FXML private Button submitButton;
    @FXML private Button backButton;

    @FXML
    protected void submitButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    protected void clearButtonClick(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusChoiceBox.getItems().addAll(statusChoice);
    }
}
