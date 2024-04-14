package com.ccifuentes.equifax.security.service.implement;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ccifuentes.equifax.security.models.dto.NewUser;
import com.ccifuentes.equifax.security.models.entity.User;
import com.ccifuentes.equifax.security.repository.IUserRepository;
import com.ccifuentes.equifax.security.service.interfaces.IUserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository usuarioRep;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User save(User obj) {
		return usuarioRep.save(obj);
	}

	@Override
	public boolean existsByMail(String email) {
		return usuarioRep.existsByMail(email);
	}

	@Override
	@Transactional
	public User createNewUser(NewUser newUser) {

		User user = new User();
		user.setMail(newUser.getEmail());
		user.setName(newUser.getName());
		user.setPassword(passwordEncoder.encode(newUser.getPassword()));
		user.setCreationDate(LocalDateTime.now());
		User registeredUser = save(user);
		return registeredUser;
	}

	@Override
	public Optional<User> getUserByMail(String mail) {
		return usuarioRep.findByMail(mail);
	}

	
}
