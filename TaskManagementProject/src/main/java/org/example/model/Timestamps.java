package org.example.model;


public class Timestamps {
    private int id;
    private String stampdescription;
    private String stamptime;

    public String getStampdescription() {
        return stampdescription;
    }

    public Timestamps(int id, String stampdescription, String stamptime, int t_id) {
        this.id = id;
        this.stampdescription = stampdescription;
        this.stamptime = stamptime;
        this.t_id = t_id;
    }

    public void setStampdescription(String stampdescription) {
        this.stampdescription = stampdescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStamptime() {
        return stamptime;
    }

    public void setStamptime(String stamptime) {
        this.stamptime = stamptime;
    }


    private int t_id;



}

