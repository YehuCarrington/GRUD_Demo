package com.example.demo_db;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EntryPageController extends SceneController implements Initializable {


    @FXML private ChoiceBox<String> statusChoiceBox;
    private final String[] statusChoice = { "Open", "On Hold", "Completed", "Closed" };

    @FXML private Button clearButton;
    @FXML private Button submitButton;
    @FXML private Button backButton;
    @FXML private TextField projectNameTextField;
    @FXML private TextField itemTitleTextField;
    @FXML private TextField responsibilityTextField;
    @FXML private TextField createdByTextField;
    @FXML private TextArea summaryTextArea;
    @FXML private TextArea descriptionTextArea;
    @FXML private DatePicker createdDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextArea emailTextArea;

    @FXML
    protected void submitButtonClick(ActionEvent actionEvent) {
        System.out.println("actionEvent = " + actionEvent);
    }

    @FXML
    protected void clearButtonClick() {
        projectNameTextField.clear();
        itemTitleTextField.clear();
        createdByTextField.clear();
        responsibilityTextField.clear();
        summaryTextArea.clear();
        emailTextArea.clear();
        descriptionTextArea.clear();
        createdDatePicker.getEditor().clear();
        createdDatePicker.setValue(null);
        dueDatePicker.getEditor().clear();
        dueDatePicker.setValue(null);
        statusChoiceBox.setValue("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusChoiceBox.getItems().addAll(statusChoice);
    }
}
