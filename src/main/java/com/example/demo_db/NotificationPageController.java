package com.example.demo_db;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NotificationPageController extends SceneController {
    @FXML private Button sendNotificationButton;
    @FXML private Button backButton;

    @FXML
    private void SendNotification(ActionEvent event) {
        System.out.println("event = " + event);
    }

}
