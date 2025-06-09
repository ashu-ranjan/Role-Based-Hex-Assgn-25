package com.hexJune.CodingTest.dto;

import java.util.Objects;

public class GetPatientDTO {
    private String name;
    private int age;
    private String username;

    public GetPatientDTO(String name, int age, String username) {
        this.name = name;
        this.age = age;
        this.username = username;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GetPatientDTO that = (GetPatientDTO) o;
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, username);
    }
}
