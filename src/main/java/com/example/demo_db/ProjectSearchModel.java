package com.example.demo_db;

import java.sql.Date;

public class ProjectSearchModel {
    String projectName;
    String itemTitle;
    String summary;
    String description;
    String responsibility;
    String createdBy;
    Date createdDate;
    Date dueDate;
    String emails;
    String status;

    public ProjectSearchModel(String projectName, String itemTitle, String summary, String description, String responsibility, String createdBy, Date createdDate, Date dueDate, String emails, String status) {
        this.projectName = projectName;
        this.itemTitle = itemTitle;
        this.summary = summary;
        this.description = description;
        this.responsibility = responsibility;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.emails = emails;
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getEmails() {
        return emails;
    }

    public String getStatus() {
        return status;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
