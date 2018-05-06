package com.pwn.pwngrup.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/login") // "/login" bo tak nazwany w spreang seciurity
    public String login(){
        return "registerFormView";
//    zmiana z index na register w celu wyswietlenia registerVie
    }

    //mapowanie errorView
    @GetMapping("/error-view")
    public String errorViewHandler(){ //przechwytywanie błędu
        return "error-view";
    }
}