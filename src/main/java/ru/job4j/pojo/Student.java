package ru.job4j.pojo;

import java.util.Date;

public class Student {
    private String fio;
    private String group;
    private Date datOfAdmission;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getDatOfAdmission() {
        return datOfAdmission;
    }

    public void setDatOfAdmission(Date datOfAdmission) {
        this.datOfAdmission = datOfAdmission;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
