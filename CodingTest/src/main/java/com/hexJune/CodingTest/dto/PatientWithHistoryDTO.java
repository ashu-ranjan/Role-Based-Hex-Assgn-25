package com.hexJune.CodingTest.dto;

import java.util.List;

public class PatientWithHistoryDTO {
    private int id;
    private String name;
    private int age;
    private String username;
    private List<MedicalHistoryDTO> medicalHistories;

    public PatientWithHistoryDTO(int id, String name, int age, String username, List<MedicalHistoryDTO> medicalHistories) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.medicalHistories = medicalHistories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<MedicalHistoryDTO> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<MedicalHistoryDTO> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }
}
