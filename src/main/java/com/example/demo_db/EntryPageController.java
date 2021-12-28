package com.example.demo_db;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EntryPageController extends SceneController implements Initializable {


    @FXML private ChoiceBox<String> statusChoiceBox;
    private final String[] statusChoice = {"Open", "On Hold", "Completed", "Closed"};

    @FXML private TextField projectNameTextField;
    @FXML private TextField itemTitleTextField;
    @FXML private TextField responsibilityTextField;
    @FXML private TextField createdByTextField;
    @FXML private TextArea summaryTextArea;
    @FXML private TextArea descriptionTextArea;
    @FXML private DatePicker createdDatePicker;
    @FXML private DatePicker dueDatePicker;
    @FXML private TextArea emailTextArea;

    //When the submit button is clicked
    // (1) a connection to the db is created
    // (2) the values written to the fields are stored into the DB
    // (3) the connection is closed
    // I don't know much about how to pool connections *
    @FXML
    protected void submitButtonClick() throws IOException {
        DbProperties dbProps = new DbProperties();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            //(1) a connection to the db is created
            Connection conn = DriverManager.getConnection(dbProps.getDbConnUrl(), dbProps.getDbUserName(), dbProps.getDbPassword());
            String SQL = "INSERT INTO data_entry (project_name, item_title, item_sum, item_des, item_resp, created_by" +
                    ", created_date, due_date, notif_email, stat) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(SQL);

            //(2) the values written to the Fields are stored into the DB
            pstmt.setString(1,projectNameTextField.getText());
            pstmt.setString(2,itemTitleTextField.getText());
            pstmt.setString(3,summaryTextArea.getText());
            pstmt.setString(4,descriptionTextArea.getText());
            pstmt.setString(5,responsibilityTextField.getText());
            pstmt.setString(6,createdByTextField.getText());
            pstmt.setDate(7, Date.valueOf(createdDatePicker.getValue()));
            pstmt.setDate(8, Date.valueOf(dueDatePicker.getValue()));
            pstmt.setString(9,emailTextArea.getText());
            pstmt.setInt(10, convertChoice(statusChoiceBox.getValue()));

            if(pstmt.executeUpdate()>0){
                System.out.println("Records Updated Successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


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

    private int convertChoice(String choice) throws NumberFormatException {
        switch(choice){
            case "Open" -> {
                return 1;
            }
            case "On Hold" -> {
                return 2;
            }
            case "Completed" -> {
                return 3;
            }
            case "Closed" -> {
                return 4;
            }

        }
        return 100;
    }
}
