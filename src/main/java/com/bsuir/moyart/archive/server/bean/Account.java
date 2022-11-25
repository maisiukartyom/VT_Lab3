package com.bsuir.moyart.archive.server.bean;

import java.util.Map;
import java.util.Objects;

public class Account {
    private String login;
    private String password;
    private Role role;


    public Account() {
        this.login = "login";
        this.password = "password";
        this.role = Role.GUEST;
    }

    public Account(Map<String, String> param) {
        setLogin(param.get("login"));
        setPassword(param.get("password"));
        setRole(Role.valueOf(param.get("role")));
    }

    public Account(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(login, account.login) && Objects.equals(password, account.password) && role == account.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
