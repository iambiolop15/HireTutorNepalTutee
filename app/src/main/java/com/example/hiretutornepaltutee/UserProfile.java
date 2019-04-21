package com.example.hiretutornepaltutee;

public class UserProfile {
    public String uname;
    public String uemail;
    public String uage;
    public String uphone;

    public String ugender;

    public UserProfile() {

    }

    public UserProfile(String username, String email, String age, String phone, String gender) {
        this.uname = username;
        this.uemail = email;
        this.uage = age;
        this.uphone = phone;
        ugender = gender;
    }
}
