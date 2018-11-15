package com.enlink.de.src.main.java.com.enlink.dao;


import com.enlink.de.src.main.java.com.enlink.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsernameAndPassword(String username, String password);
}
