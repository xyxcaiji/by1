package com.sy.by.bean;


import java.util.Arrays;
import java.util.Objects;

public class Wen_answer {
   private String sex;
   private String a;
   private String b;
   private String c;
   private String d;
   private String e;
   private String suggestion;
   private String[] e1;
   private String user_name;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String[] getE1() {
        return e1;
    }

    public void setE1(String[] e1) {
        this.e1 = e1;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wen_answer)) return false;
        Wen_answer that = (Wen_answer) o;
        return Objects.equals(getSex(), that.getSex()) &&
                Objects.equals(getA(), that.getA()) &&
                Objects.equals(getB(), that.getB()) &&
                Objects.equals(getC(), that.getC()) &&
                Objects.equals(getD(), that.getD()) &&
                Objects.equals(getE(), that.getE()) &&
                Objects.equals(getSuggestion(), that.getSuggestion()) &&
                Arrays.equals(getE1(), that.getE1()) &&
                Objects.equals(getUser_name(), that.getUser_name());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSex(), getA(), getB(), getC(), getD(), getE(), getSuggestion(), getUser_name());
        result = 31 * result + Arrays.hashCode(getE1());
        return result;
    }

    @Override
    public String toString() {
        return "Wen_answer{" +
                "sex='" + sex + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", e1=" + Arrays.toString(e1) +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
