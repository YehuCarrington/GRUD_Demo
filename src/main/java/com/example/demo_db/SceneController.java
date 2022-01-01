package com.example.demo_db;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SceneController implements Initializable {

    //This is used to switch the search function between two different implementations
    boolean MYWAY;
    boolean AUTONOTIF;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    CheckBox myWayCheckBox = new CheckBox();
    @FXML
    CheckBox autoNotificationCheckBox = new CheckBox();

    @FXML
    protected void SwitchToEntryPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("entry-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void SwitchToNotificationPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notification-page.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void SwitchToSearchPage(ActionEvent event) throws IOException {
        Properties props = new Properties();
        String stateProperties = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.state.properties";
        FileReader fReader = new FileReader(stateProperties);
        props.load(fReader);

        MYWAY = Boolean.parseBoolean(props.getProperty("state.MYWAY"));

        if (MYWAY) {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search-page.fxml")));
        } else {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("search-page2.fxml")));
        }
        fReader.close();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.show();
    }

    @FXML
    protected void SwitchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-page.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void MyWayConfig() throws IOException {
        Properties props = new Properties();
        String stateProperties = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.state.properties";
        FileWriter fWriter = new FileWriter(stateProperties, false);

        if (myWayCheckBox.isSelected()) {
            props.setProperty("state.MYWAY", "true");
        } else {
            props.setProperty("state.MYWAY", "false");
        }
        props.store(fWriter, "Program State");
        fWriter.close();
    }

    @FXML
    public void AutoNotification() throws IOException {
        Properties props = new Properties();
        String stateProperties = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.notification_settings.properties";
        FileWriter fWriter = new FileWriter(stateProperties, false);

        if (autoNotificationCheckBox.isSelected()) {
            props.setProperty("state.AUTOMAIL", "true");
        } else {
            props.setProperty("state.AUTOMAIL", "false");
        }
        props.store(fWriter, "Notification State");
        fWriter.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myWayCheckBox.setAllowIndeterminate(false);
        autoNotificationCheckBox.setAllowIndeterminate(false);

        Properties props1 = new Properties();
        Properties props2 = new Properties();
        String stateProperties = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.state.properties";
        String notifProperties = "C:\\Users\\Counter1\\Desktop\\demo_db\\src\\main\\resources\\.notification_settings.properties";
        try {
            FileReader fReader1 = new FileReader(stateProperties);
            FileReader fReader2 = new FileReader(notifProperties);
            props1.load(fReader1);
            props2.load(fReader2);

            MYWAY = Boolean.parseBoolean(props1.getProperty("state.MYWAY"));
            AUTONOTIF = Boolean.parseBoolean(props2.getProperty("state.AUTOMAIL"));
            myWayCheckBox.setSelected(MYWAY);
            autoNotificationCheckBox.setSelected(AUTONOTIF);
            fReader1.close();
            fReader2.close();

            if (autoNotificationCheckBox.isSelected()) {
                System.out.println("AutoNotify About to Run");
                AutoNotify();
            }
            System.out.println("skipped AutoNotify");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AutoNotify() {
        Timer timer = new Timer();

        TimerTask Autonotify = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Sending AutoNotification");
                SendNotification(true);
                System.out.println("AutoNotification Sent");
            }
        };

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0));
        Duration duration = Duration.between(now, end);

        long millis;
        if (duration.isNegative()) {
            Duration time1 = duration.abs();
            Duration time2 = time1.minusHours(24);
            Duration time3 = time2.abs();
            millis = time3.toMillis();
        } else {
            millis = duration.toMillis();
        }

        timer.scheduleAtFixedRate(Autonotify, millis, TimeUnit.DAYS.toSeconds(1));
    }

    public void SendNotification(boolean isAuto) {

        PreparedStatement pstmt;
        ResultSet rs;
        //Connect to the DB
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DbConnector.getConnection()) {
            //Query all items where status = open and due_date<current_date
            String SQL = "SELECT item_title, due_date, notif_email, item_resp FROM data_entry WHERE stat = ? AND due_date < ? ";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, 1); //Since 1 in the table denotes an open item
            pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String itemTitle = rs.getString("item_title");
                Date dueDate = rs.getDate("due_date");
                String notif_email = rs.getString("notif_email");
                String resp = rs.getString("item_resp");

                String[] emails = notif_email.split(";");
                EmailDetails project = new EmailDetails(itemTitle, dueDate, resp, emails);

                String notifMessage;
                if (isAuto) {
                    notifMessage = "Hello, Good day.\n" +
                            "The item " + project.item_title + " with due date: " + project.due_date + " is pending follow up and responsibly for this\n" +
                            "item is " + project.resp + ". Please follow up or update the due date in the item to stop\n" +
                            "receiving notification. The date today is: " + LocalDate.now() + "\n" +
                            "Thanks,\n" +
                            "Automated System CrimsonLogic.\n" + "This was sent as a reoccurring message";
                } else {
                    notifMessage = "Hello, Good day.\n" +
                            "The item " + project.item_title + " with due date: " + project.due_date + " is pending follow up and responsibly for this\n" +
                            "item is " + project.resp + ". Please follow up or update the due date in the item to stop\n" +
                            "receiving notification.\n" +
                            "Thanks,\n" +
                            "Automated System CrimsonLogic.";
                }

                EmailProperties emailProps = new EmailProperties();
                Properties props = new Properties();
                props.put("mail.smtp.auth", true);
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", 587);
                props.put("mail.smtp.starttls.enable", true);
                props.put("mail.transport.protocol", "smtp");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailProps.getEmailUserName(), emailProps.getEmailPassword());
                    }
                });

                Message message = new MimeMessage(session);
                message.setSubject("Fix Required: Item Pass Due");
                message.setText(notifMessage);

                for (int i = 0; i < emails.length; i++) {

                    Address addressTo = new InternetAddress(project.emails[i]);
                    message.setRecipient(Message.RecipientType.TO, addressTo);
                    Transport.send(message);
                }
            }

        } catch (SQLException | IOException | MessagingException e) {
            e.printStackTrace();
        }
    }
}