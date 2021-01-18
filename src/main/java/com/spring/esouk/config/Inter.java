package com.spring.esouk.config;

public interface Inter {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
