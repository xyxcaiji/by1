package com.sy.by.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 *
 * 试卷暂存表
 */
public class Test_paper {
    private int test_id;
    private String topics_id;
    private String answers;
    private String r_and_w;
    private int user_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date test_date;
    private String subject_name;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public String getTopics_id() {
        return topics_id;
    }

    public void setTopics_id(String topics_id) {
        this.topics_id = topics_id;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getR_and_w() {
        return r_and_w;
    }

    public void setR_and_w(String r_and_w) {
        this.r_and_w = r_and_w;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test_paper)) return false;
        Test_paper that = (Test_paper) o;
        return getTest_id() == that.getTest_id() &&
                getUser_id() == that.getUser_id() &&
                Objects.equals(getTopics_id(), that.getTopics_id()) &&
                Objects.equals(getAnswers(), that.getAnswers()) &&
                Objects.equals(getR_and_w(), that.getR_and_w()) &&
                Objects.equals(getTest_date(), that.getTest_date()) &&
                Objects.equals(getSubject_name(), that.getSubject_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTest_id(), getTopics_id(), getAnswers(), getR_and_w(), getUser_id(), getTest_date(), getSubject_name());
    }


    @Override
    public String toString() {
        return "Test_paper{" +
                "test_id=" + test_id +
                ", topics_id='" + topics_id + '\'' +
                ", answers='" + answers + '\'' +
                ", r_and_w='" + r_and_w + '\'' +
                ", user_id=" + user_id +
                ", test_date=" + test_date +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }
}
