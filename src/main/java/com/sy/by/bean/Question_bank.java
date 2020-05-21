package com.sy.by.bean;

import java.util.Objects;

/**
 *  题库表对应的bean
 *
 */
public class Question_bank {
    private int id;
    private String subject;
    private String brief;
    private String show;
    private String subject_pic;


    public String getSubject_pic() {
        return subject_pic;
    }

    public void setSubject_pic(String subject_pic) {
        this.subject_pic = subject_pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }


    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question_bank)) return false;
        Question_bank that = (Question_bank) o;
        return getId() == that.getId() &&
                Objects.equals(getSubject(), that.getSubject()) &&
                Objects.equals(getBrief(), that.getBrief()) &&
                Objects.equals(getShow(), that.getShow()) &&
                Objects.equals(getSubject_pic(), that.getSubject_pic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSubject(), getBrief(), getShow(), getSubject_pic());
    }

    @Override
    public String toString() {
        return "Question_bank{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", brief='" + brief + '\'' +
                ", show='" + show + '\'' +
                ", subject_pic='" + subject_pic + '\'' +
                '}';
    }
}
