package com.sy.by.bean;

import java.util.Objects;

/**
 * 错题表
 */
public class Errorr {
    private int error_id;
    private int topic_id;
    private int subject_id;
    private int user_id;
    private String subject_name;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }


    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }


    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Errorr)) return false;
        Errorr errorr = (Errorr) o;
        return getError_id() == errorr.getError_id() &&
                getTopic_id() == errorr.getTopic_id() &&
                getSubject_id() == errorr.getSubject_id() &&
                getUser_id() == errorr.getUser_id() &&
                Objects.equals(getSubject_name(), errorr.getSubject_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getError_id(), getTopic_id(), getSubject_id(), getUser_id(), getSubject_name());
    }

    @Override
    public String toString() {
        return "Errorr{" +
                "error_id=" + error_id +
                ", topic_id=" + topic_id +
                ", subject_id=" + subject_id +
                ", user_id=" + user_id +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }
}
