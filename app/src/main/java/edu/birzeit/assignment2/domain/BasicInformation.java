package edu.birzeit.assignment2.domain;

import java.util.ArrayList;

public class BasicInformation {
    private String name;
    private String nickname;
    private char gender;
    private String Email;
    private String hobby;

    public BasicInformation() {
    }

    public BasicInformation(String name, String nickname, char gender, String email, String hobby) {
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        Email = email;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
