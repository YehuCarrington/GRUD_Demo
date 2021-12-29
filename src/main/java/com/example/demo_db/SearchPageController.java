package com.example.demo_db;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SearchPageController extends SceneController implements Initializable {

    @FXML private TextField inputTextField;
    @FXML private ChoiceBox<String> searchChoiceBox;
    private final String[] searchChoice = {"Project Name", "Item Title", "Item Responsibility", "Due Date", "Status"};

    @FXML
    private void SearchDB() throws IOException, SQLException {
        String choice = searchChoiceBox.getValue();

        PreparedStatement pstmt;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try (Connection conn = DbConnector.getConnection()){

            String SQL1 = "SELECT project_name, item_title, item_sum, item_des, item_resp, created_by, created_date, due_date, notif_email, stat FROM data_entry";
            String SQL2 = "SELECT project_name, item_title, item_resp, due_date, stat FROM data_entry WHERE item_title=?";
            String SQL3 = "SELECT project_name, item_title, item_resp, due_date, stat FROM data_entry WHERE item_resp=?";
            String SQL4 = "SELECT project_name, item_title, item_resp, due_date, stat FROM data_entry WHERE due_date=?";
            String SQL5 = "SELECT project_name, item_title, item_resp, due_date, stat FROM data_entry WHERE stat=?";

            switch(choice){
                case "Project Name" -> {
                    if (inputTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        pstmt = conn.prepareStatement(SQL1);
                        pstmt.setString(1, inputTextField.getText());
                        rs = pstmt.executeQuery();
                    }
                }
                case "Item Title" -> {
                    if (inputTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        pstmt = conn.prepareStatement(SQL2);
                        pstmt.setString(1, inputTextField.getText());
                        rs = pstmt.executeQuery();
                    }
                }
                case "Item Responsibility" -> {
                    if (inputTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        pstmt = conn.prepareStatement(SQL3);
                        pstmt.setString(1, inputTextField.getText());
                        rs = pstmt.executeQuery();
                    };
                }
                case "Due Date" -> {
                    if (inputTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        pstmt = conn.prepareStatement(SQL4);
                        pstmt.setString(1, inputTextField.getText());
                        rs = pstmt.executeQuery();
                    }
                }
                case "Status" -> {
                    if (inputTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        pstmt = conn.prepareStatement(SQL5);
                        pstmt.setString(1, inputTextField.getText());
                        rs = pstmt.executeQuery();
                    }
                }
            }


        }
    }

    private void ErrorMsgEmptyTxt (){
        Alert err = new Alert(Alert.AlertType.ERROR);
        err.setContentText("Enter a value to search");
        err.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchChoiceBox.getItems().addAll(searchChoice);
    }
}
