package com.pwn.pwngrup.service;




import com.pwn.pwngrup.models.Kursant;
import com.pwn.pwngrup.repositories.KursantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GrupUserDetailsService implements UserDetailsService { //nasz własny sposób uwierzytelniania, odwołanie

    public UserDetails userDetails; //wyciągnięcie na góre do wykorzstania
    // z ostatniej linijki do wykorzystania w wyswietlaniu danych z bazy Grade controller

    @Autowired
    private KursantRepo kursantRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //tu z automatu przekazywany login z formularza
        System.out.println("IN");
        Kursant activeUserInfo                   = kursantRepo.getByEmail(email);

        if(activeUserInfo==null){
            throw new UsernameNotFoundException("User not found");
        }
        GrantedAuthority authority            = new SimpleGrantedAuthority("ROLE_USER");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //BCrypt do szyfrowania haseł
        String userEmail                    = activeUserInfo.getEmail();
        String userPass                     = passwordEncoder.encode(activeUserInfo.getPaswd());
//passwordEncoder.encode(activeUserInfo.getPassword()); wstawic powyzej po = active, zeby hasła w bazie były nieszyfrowane
        userDetails = new org.springframework.security.core.userdetails.User(userEmail, userPass, Arrays.asList(authority));
        return userDetails;
    }
}