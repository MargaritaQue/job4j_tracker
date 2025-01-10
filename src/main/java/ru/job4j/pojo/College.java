package ru.job4j.pojo;

import java.util.Date;

public class College {

    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Korol Margarita D.");
        student.setGroup("FMFI-b19");
        student.setDatOfAdmission(new Date());

        System.out.println(student.getFio() + ", " + student.getGroup() + ", " + student.getDatOfAdmission());
    }
}
