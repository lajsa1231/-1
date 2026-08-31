package com.lhy.pojo;

public class Logininfo {
    private Integer id;
    private String name;
    private String username;
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Logininfo(Integer id, String name, String username, String token) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
