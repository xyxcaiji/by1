package com.sy.by.bean;

import java.util.Objects;

public class Topic {
    private int id;
    private String topic_type;
    private String topic_content;
    private String A;
    private String B;
    private String C;
    private String D;
    private char answer;
    private String difficulty;
    //所属的课程id
    private int question_id;
    //所属的章节id
    private int section_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public char getAnswer() {
        return answer;
    }

    public void setAnswer(char answer) {
        this.answer = answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return getId() == topic.getId() &&
                getAnswer() == topic.getAnswer() &&
                getQuestion_id() == topic.getQuestion_id() &&
                getSection_id() == topic.getSection_id() &&
                Objects.equals(getTopic_type(), topic.getTopic_type()) &&
                Objects.equals(getTopic_content(), topic.getTopic_content()) &&
                Objects.equals(getA(), topic.getA()) &&
                Objects.equals(getB(), topic.getB()) &&
                Objects.equals(getC(), topic.getC()) &&
                Objects.equals(getD(), topic.getD()) &&
                Objects.equals(getDifficulty(), topic.getDifficulty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopic_type(), getTopic_content(), getA(), getB(), getC(), getD(), getAnswer(), getDifficulty(), getQuestion_id(), getSection_id());
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topic_type='" + topic_type + '\'' +
                ", topic_content='" + topic_content + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                ", D='" + D + '\'' +
                ", answer=" + answer +
                ", difficulty='" + difficulty + '\'' +
                ", question_id=" + question_id +
                ", section_id=" + section_id +
                '}';
    }
}
