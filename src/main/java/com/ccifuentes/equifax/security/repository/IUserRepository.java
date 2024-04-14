package com.ccifuentes.equifax.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccifuentes.equifax.security.models.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	Optional<User> findByMail(String mail);

	boolean existsByMail(String email);
}
