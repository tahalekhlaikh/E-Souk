package com.spring.esouk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class TestController {
    @Autowired
    private HttpSession httpSession;


}
