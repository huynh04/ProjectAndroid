package com.example.supporthireapp.Model;

public class Candidate {
    String stt,fullname,address,skill,date,experience,field,uid;

    public Candidate(String stt, String fullname, String address, String skill, String date, String experience, String field, String uid) {
        this.stt = stt;
        this.fullname = fullname;
        this.address = address;
        this.skill = skill;
        this.date = date;
        this.experience = experience;
        this.field = field;
        this.uid = uid;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
