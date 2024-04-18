package com.example.scoremanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scoremanage.model.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByLoginIdAndPassword(String loginId, String password);
}