package org.example.model;



public class Milestone {
    private int id;

    public Milestone(int id, String milestonename, String milestonedescription, String milestonedate, String createdate, int projectId) {
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMilestoneDescription() {
        return milestoneDescription;
    }

    public void setMilestoneDescription(String milestoneDescription) {
        this.milestoneDescription = milestoneDescription;
    }

    public String getMilestoneDate() {
        return milestoneDate;
    }

    public void setMilestoneDate(String milestoneDate) {
        this.milestoneDate = milestoneDate;
    }

    public String getCreatedAt() {
        return createddate;
    }

    public void setCreatedAt(String createdAt) {
        this.createddate = createdAt;
    }

    private String milestoneName;
    private String milestoneDescription;
    private String milestoneDate;
    private String createddate;

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    private int project_id;

}