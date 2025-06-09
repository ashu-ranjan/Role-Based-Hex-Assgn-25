package com.hexJune.CodingTest.dto;

public class MedicalHistoryDTO {
    private String illness;
    private int numberOfYears;
    private String currentMedication;

    public MedicalHistoryDTO(String illness, int numberOfYears, String currentMedication) {
        this.illness = illness;
        this.numberOfYears = numberOfYears;
        this.currentMedication = currentMedication;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }
}
