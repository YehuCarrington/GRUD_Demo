package com.example.demo_db;

import javafx.fxml.FXML;

public class NotificationPageController extends SceneController {

    @FXML
    private void ManualNotification() {
        SendNotification(false);
    }
}
