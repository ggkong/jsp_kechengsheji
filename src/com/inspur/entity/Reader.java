package com.inspur.entity;

public class Reader {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String tel;
    private String cardid;
    private String gender;

    public Reader(Integer id, String username, String password, String name, String tel, String cardid, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.cardid = cardid;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getCardid() {
        return cardid;
    }

    public String getGender() {
        return gender;
    }
}
