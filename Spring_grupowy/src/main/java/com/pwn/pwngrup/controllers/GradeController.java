package com.pwn.pwngrup.controllers;


import com.pwn.pwngrup.models.Kursant;
import com.pwn.pwngrup.repositories.KursantRepo;
import com.pwn.pwngrup.service.GrupUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

    @Autowired
    GrupUserDetailsService guds; //guds=GrupUserDetailsService,

    @Autowired
    private KursantRepo kursantRepo; //kursantrepo umozliwia dostęp do danych po mailu

    @GetMapping("/grade")
    public String register(Model model){

        //KursantRepo kursantRepo;// kursant umozliwia dostęp do danych po mailu
        Kursant kursant = kursantRepo.getByEmail(guds.userDetails.getUsername()); //wyciągnięcie danych konkretnego uzytkownika
        model.addAttribute("kursantregister", kursant);//new Kursant()); //tworzy pusty obiekt, na początek kursant to wcześniej stworzony obiekt
        return "gradeView";//<--ten widok widzi tylko s:kursantregister
    }

    @PostMapping("/grade")
    public String register(@ModelAttribute Kursant kursantregister){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        kursantregister.setPaswd(passwordEncoder.encode(kursantregister.getPaswd()));
        kursantRepo.save(kursantregister);
        return "redirect:/";
    }
}
