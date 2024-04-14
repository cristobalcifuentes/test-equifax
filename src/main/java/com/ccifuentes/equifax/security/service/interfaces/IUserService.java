package com.ccifuentes.equifax.security.service.interfaces;

import java.util.Optional;

import com.ccifuentes.equifax.security.models.dto.NewUser;
import com.ccifuentes.equifax.security.models.entity.User;

public interface IUserService {

	public Optional<User> getUserByMail(String mail);

	public boolean existsByMail(String email);

	public User createNewUser(NewUser newUser);

	User save(User user);

}
