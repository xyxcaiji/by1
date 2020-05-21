package com.sy.by.bean;

import java.util.Objects;

public class Wen_juan {
    private int wen_juan_id;
    private String juan_content;
    private String juan_zhuti;

    public int getWen_juan_id() {
        return wen_juan_id;
    }

    public void setWen_juan_id(int wen_juan_id) {
        this.wen_juan_id = wen_juan_id;
    }

    public String getJuan_content() {
        return juan_content;
    }

    public void setJuan_content(String juan_content) {
        this.juan_content = juan_content;
    }


    public String getJuan_zhuti() {
        return juan_zhuti;
    }

    public void setJuan_zhuti(String juan_zhuti) {
        this.juan_zhuti = juan_zhuti;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wen_juan)) return false;
        Wen_juan wen_juan = (Wen_juan) o;
        return getWen_juan_id() == wen_juan.getWen_juan_id() &&
                Objects.equals(getJuan_content(), wen_juan.getJuan_content()) &&
                Objects.equals(getJuan_zhuti(), wen_juan.getJuan_zhuti());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWen_juan_id(), getJuan_content(), getJuan_zhuti());
    }

    @Override
    public String toString() {
        return "Wen_juan{" +
                "wen_juan_id=" + wen_juan_id +
                ", juan_content='" + juan_content + '\'' +
                ", juan_zhuti='" + juan_zhuti + '\'' +
                '}';
    }
}
