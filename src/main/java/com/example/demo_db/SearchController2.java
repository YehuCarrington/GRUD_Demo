package com.example.demo_db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SearchController2 implements Initializable {
    @FXML private TableView<ProjectSearchModel> dbTableView;
    @FXML private TableColumn<ProjectSearchModel, String> projectColumn;
    @FXML private TableColumn<ProjectSearchModel, String> itemTitleColumn;
    @FXML private TableColumn<ProjectSearchModel, String> summaryColumn;
    @FXML private TableColumn<ProjectSearchModel, String> descriptionColumn;
    @FXML private TableColumn<ProjectSearchModel, String> responsibilityColumn;
    @FXML private TableColumn<ProjectSearchModel, String> createdByColumn;
    @FXML private TableColumn<ProjectSearchModel, Date> createdDateColumn;
    @FXML private TableColumn<ProjectSearchModel, Date> dueDateColumn;
    @FXML private TableColumn<ProjectSearchModel, String> emailColumn;
    @FXML private TableColumn<ProjectSearchModel, String> statusColumn;

    ObservableList<ProjectSearchModel> projectSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try (Connection conn = DbConnector.getConnection()) {
            String SQL = "SELECT project_name, item_title, item_sum, item_des, item_resp, created_by, created_date, due_date, notif_email, stat FROM data_entry";

            Statement stmt = conn.createStatement();
            ResultSet queryOutput = stmt.executeQuery(SQL);

            while(queryOutput.next()){

                String queryProjectName = queryOutput.getString("project_name");
                String queryItemTitle = queryOutput.getString("item_title");
                String querySummary = queryOutput.getString("item_sum");
                String queryDescription = queryOutput.getString("item_des");
                String queryResponsibility = queryOutput.getString("item_resp");
                String queryCreatedBy = queryOutput.getString("created_by");
                Date queryCreatedDate = queryOutput.getDate("created_date");
                Date queryDueDate = queryOutput.getDate("due_date");
                String queryEmail = queryOutput.getString("notif_email");
                String queryStatus = queryOutput.getString("stat");

                projectSearchModelObservableList.add(new ProjectSearchModel(queryProjectName, queryItemTitle,
                                                                            querySummary, queryDescription, queryResponsibility,
                                                                            queryCreatedBy, queryCreatedDate, queryDueDate, queryEmail,
                                                                            queryStatus));
            }

            projectColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            itemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("itemTitle"));
            summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            responsibilityColumn.setCellValueFactory(new PropertyValueFactory<>("responsibility"));
            createdByColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
            dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("emails"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

            dbTableView.setItems(projectSearchModelObservableList);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
}
