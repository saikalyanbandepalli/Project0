package org.example.model;

public class Task {
    private int id;

    public int getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(int completion_time) {
        this.completion_time = completion_time;
    }

    public String getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

    private int completion_time;

    public Task(int id, String taskdescription, String taskstatus, int pId) {
    }

    public Task() {

    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return taskstatus;
    }

    public void setStatus(String status) {
        this.taskstatus = status;
    }

    private String taskDescription;
    private String taskstatus;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    private int p_id;


}