package com.sy.by.bean;

import java.util.Objects;

/**
 * 科目表
 */
public class Section {
    private int section_id;
    private String section_name;
    private int subject_id;
    private String subject_name;

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
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
        if (!(o instanceof Section)) return false;
        Section section = (Section) o;
        return getSection_id() == section.getSection_id() &&
                getSubject_id() == section.getSubject_id() &&
                Objects.equals(getSection_name(), section.getSection_name()) &&
                Objects.equals(getSubject_name(), section.getSubject_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSection_id(), getSection_name(), getSubject_id(), getSubject_name());
    }

    @Override
    public String toString() {
        return "Section{" +
                "section_id=" + section_id +
                ", section_name='" + section_name + '\'' +
                ", subject_id=" + subject_id +
                ", subject_name='" + subject_name + '\'' +
                '}';
    }
}
