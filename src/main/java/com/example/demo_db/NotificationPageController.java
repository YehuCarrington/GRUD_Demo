package com.example.demo_db;

import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.*;

public class NotificationPageController extends SceneController {

    //Implementation Task
    /*

    Notification:
        a. From the landing page user can click on notification page. (Completed)
        b. The page can be empty with just a button which says send notification. (Completed)
        c. Once user clicks on send notification, system should see the current date and
        compare with all pending items with status open which are past due date. Take
        email ID from each of these pending items and send email which contains
        Sample Text -> {
                            “Hello, Good day.
                            The item XXX with due date XXX is pending follow up and responsibly for this
                            item is XXX. Please follow up or update the due date in the item to stop
                            receiving notification.
                            Thanks,
                            Automated System CrimsonLogic.”
                       }
        Pseudo ->   Grab current date (completed)
                    Connect to DB (completed)
                    Query all items where status = open and due_date<current_date (completed)
                    Grab Emails form the results of the query (completed)
                    Using String Manipulation extract emails (completed)
                    USing JAVAMAIL API send emails

        d. Note: The above template with XXX are the dynamic data that must be pulled by
        your system before sending the email.
        e. For example: if 3 pending items are present with title A. Design document B.
        Testing Document. C. Requirement Document. With A having email
        XXX@gmail.com, B having email YYY@gmail.com and C having emails
        AAA@gmail.com plus BBB@gmail.com then 4 emails will be triggered 1 for A, 1
        for B and 2 for C.
        f. Optional: If you have time then this notification can be made automated which
        will check and send updates at 9 am every day rather than user having to click on
        send notification button.
        Pseudo ->   Same as Above with the added step at start
                    Create a timed task
                    On trigger of the task execute the above pseudo
     */

    @FXML
    private void SendNotification() {

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
            String SQL = "SELECT item_title, due_date, notif_email FROM data_entry WHERE stat = ? AND due_date < ? ";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, 1); //Since 1 in the table denotes an open item
            pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            rs = pstmt.executeQuery();

            String itemTitle = null;
            String notif_email = null;

            Date dueDate = null;
            while (rs.next()) {
                itemTitle = rs.getString("item_title");
                dueDate = rs.getDate("due_date");
                notif_email = rs.getString("notif_email");

                String[] emails = notif_email.split(";");
                EmailDetails project = new EmailDetails(itemTitle,dueDate,emails);
                for(int i = 0; i < emails.length; i++){
                    System.out.println(project.item_title);
                    System.out.println(project.due_date);
                    System.out.println((project.emails[i]));}
            }



        } catch (SQLException |
                IOException e) {
            e.printStackTrace();
        }
    }
}
