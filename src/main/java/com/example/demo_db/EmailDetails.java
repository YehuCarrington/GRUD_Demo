package com.example.demo_db;

import java.sql.Date;

public class EmailDetails {

    String item_title;
    Date due_date;
    String[] emails;

    EmailDetails(String item_title, Date due_date, String[] emails){

        this.item_title = item_title;
        this.due_date = due_date;
        this.emails = emails;

    }

}
