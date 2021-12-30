package com.example.demo_db;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SearchPageController extends SceneController implements Initializable {

    @FXML
    private ChoiceBox<String> searchChoiceBox;
    private final String[] searchChoice = {"Project Name", "Item Title", "Item Responsibility", "Due Date", "Status"};

    @FXML
    private TableView<ProjectSearchModel> projectTableView;
    @FXML
    private TableColumn<ProjectSearchModel, String> projectColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> itemTitleColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> summaryColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> descriptionColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> responsibilityColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> createdByColumn;
    @FXML
    private TableColumn<ProjectSearchModel, Date> createdDateColumn;
    @FXML
    private TableColumn<ProjectSearchModel, Date> dueDateColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> emailColumn;
    @FXML
    private TableColumn<ProjectSearchModel, String> statusColumn;
    @FXML
    private TextField keywordTextField;

    ObservableList<ProjectSearchModel> projectSearchModelObservableList = FXCollections.observableArrayList();

    @FXML
    private void SearchDB() throws IOException, SQLException {
        String choice = searchChoiceBox.getValue();

        PreparedStatement pstmt;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DbConnector.getConnection()) {

            String SQL1 = "SELECT * FROM data_entry WHERE project_name LIKE ?";
            String SQL2 = "SELECT * FROM data_entry WHERE item_title LIKE ?";
            String SQL3 = "SELECT * FROM data_entry WHERE item_resp LIKE ?";
            String SQL4 = "SELECT * FROM data_entry WHERE due_date LIKE ?";
            String SQL5 = "SELECT * FROM data_entry WHERE stat LIKE ?";

            switch (choice) {
                case "Project Name" -> {
                    if (keywordTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        projectTableView.getItems().clear();
                        pstmt = conn.prepareStatement(SQL1);
                        pstmt.setString(1, keywordTextField.getText());
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            String queryProjectName = rs.getString("project_name");
                            String queryItemTitle = rs.getString("item_title");
                            String querySummary = rs.getString("item_sum");
                            String queryDescription = rs.getString("item_des");
                            String queryResponsibility = rs.getString("item_resp");
                            String queryCreatedBy = rs.getString("created_by");
                            Date queryCreatedDate = rs.getDate("created_date");
                            Date queryDueDate = rs.getDate("due_date");
                            String queryEmail = rs.getString("notif_email");
                            String queryStatus = rs.getString("stat");

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

                        projectTableView.setItems(projectSearchModelObservableList);

                    }
                }
                case "Item Title" -> {
                    if (keywordTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        projectTableView.getItems().clear();
                        pstmt = conn.prepareStatement(SQL2);
                        pstmt.setString(1, keywordTextField.getText());
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            String queryProjectName = rs.getString("project_name");
                            String queryItemTitle = rs.getString("item_title");
                            String querySummary = rs.getString("item_sum");
                            String queryDescription = rs.getString("item_des");
                            String queryResponsibility = rs.getString("item_resp");
                            String queryCreatedBy = rs.getString("created_by");
                            Date queryCreatedDate = rs.getDate("created_date");
                            Date queryDueDate = rs.getDate("due_date");
                            String queryEmail = rs.getString("notif_email");
                            String queryStatus = rs.getString("stat");

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

                        projectTableView.setItems(projectSearchModelObservableList);
                    }
                }
                case "Item Responsibility" -> {
                    if (keywordTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        projectTableView.getItems().clear();
                        pstmt = conn.prepareStatement(SQL3);
                        pstmt.setString(1, keywordTextField.getText());
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            String queryProjectName = rs.getString("project_name");
                            String queryItemTitle = rs.getString("item_title");
                            String querySummary = rs.getString("item_sum");
                            String queryDescription = rs.getString("item_des");
                            String queryResponsibility = rs.getString("item_resp");
                            String queryCreatedBy = rs.getString("created_by");
                            Date queryCreatedDate = rs.getDate("created_date");
                            Date queryDueDate = rs.getDate("due_date");
                            String queryEmail = rs.getString("notif_email");
                            String queryStatus = rs.getString("stat");

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

                        projectTableView.setItems(projectSearchModelObservableList);

                    }
                }
                case "Due Date" -> {
                    if (keywordTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        projectTableView.getItems().clear();
                        pstmt = conn.prepareStatement(SQL4);
                        pstmt.setDate(1, Date.valueOf(keywordTextField.getText()));
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            String queryProjectName = rs.getString("project_name");
                            String queryItemTitle = rs.getString("item_title");
                            String querySummary = rs.getString("item_sum");
                            String queryDescription = rs.getString("item_des");
                            String queryResponsibility = rs.getString("item_resp");
                            String queryCreatedBy = rs.getString("created_by");
                            Date queryCreatedDate = rs.getDate("created_date");
                            Date queryDueDate = rs.getDate("due_date");
                            String queryEmail = rs.getString("notif_email");
                            String queryStatus = rs.getString("stat");

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

                        projectTableView.setItems(projectSearchModelObservableList);
                    }
                }
                case "Status" -> {
                    if (keywordTextField.getText().isEmpty()) {
                        ErrorMsgEmptyTxt();
                    } else {
                        projectTableView.getItems().clear();
                        pstmt = conn.prepareStatement(SQL5);
                        pstmt.setString(1, keywordTextField.getText());
                        rs = pstmt.executeQuery();

                        while (rs.next()) {
                            String queryProjectName = rs.getString("project_name");
                            String queryItemTitle = rs.getString("item_title");
                            String querySummary = rs.getString("item_sum");
                            String queryDescription = rs.getString("item_des");
                            String queryResponsibility = rs.getString("item_resp");
                            String queryCreatedBy = rs.getString("created_by");
                            Date queryCreatedDate = rs.getDate("created_date");
                            Date queryDueDate = rs.getDate("due_date");
                            String queryEmail = rs.getString("notif_email");
                            String queryStatus = rs.getString("stat");

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

                        projectTableView.setItems(projectSearchModelObservableList);
                    }
                }
            }


        }
    }

    private void ErrorMsgEmptyTxt() {
        Alert err = new Alert(Alert.AlertType.ERROR);
        err.setContentText("Enter a value to search");
        err.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchChoiceBox.getItems().addAll(searchChoice);
    }
}
