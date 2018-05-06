package com.pwn.pwngrup.controllers;


import com.pwn.pwngrup.models.Kursant;
import com.pwn.pwngrup.repositories.KursantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private KursantRepo kursantRepo;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("kursantregister", new Kursant());
        return "kursantRegister";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Kursant kursantregister, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "error-view";
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            kursantregister.setPaswd(passwordEncoder.encode(kursantregister.getPaswd()));
            kursantRepo.save(kursantregister);
            return "redirect:/";
        }
    }
}