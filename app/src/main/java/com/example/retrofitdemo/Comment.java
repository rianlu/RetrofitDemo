package com.example.retrofitdemo;

public class Comment {

    private int userId;
    private int id;
    private String email;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
