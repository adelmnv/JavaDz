package kz.itstep2.model;

import java.sql.Date;

public class User {
    private Long id;
    private String login;
    private String password;
    private Date creation_date;

    public User(Long id, String login, String password, Date creation_date) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.creation_date = creation_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }


}
