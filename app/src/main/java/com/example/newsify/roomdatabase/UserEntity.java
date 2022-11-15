package com.example.newsify.roomdatabase;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity(int userid, String password, String mobileno,String name,String gender) {
        this.userid = userid;
        this.password = password;
        this.mobileno = mobileno;
        this.name=name;
        this.gender=gender;
    }
@Ignore
    public UserEntity( String password, String mobileno,String name,String gender) {
        this.password = password;
        this.mobileno = mobileno;
        this.name=name;
        this.gender=gender;
    }

    private String password;
    private String mobileno;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

}
