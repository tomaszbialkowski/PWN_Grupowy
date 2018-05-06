package com.pwn.pwngrup.repositories;
import com.pwn.pwngrup.models.Kursant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KursantRepo extends CrudRepository<Kursant, Long> {
    Kursant getByEmail(String email);

}