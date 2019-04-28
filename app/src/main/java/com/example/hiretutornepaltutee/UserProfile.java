package com.example.hiretutornepaltutee;

public class UserProfile {
    public String uname;
    public String uemail;
    public String uage;
    public String uphone;

    public String ugender;

    public  UserProfile() {

    }

    public UserProfile(String username, String email, String age, String phone, String gender) {
        this.uname = username;
        this.uemail = email;
        this.uage = age;
        this.uphone = phone;
        ugender = gender;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUage() {
        return uage;
    }

    public void setUage(String uage) {
        this.uage = uage;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }
}
