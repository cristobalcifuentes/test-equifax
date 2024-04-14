package com.ccifuentes.equifax.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ccifuentes.equifax.security.models.dto.JwtDto;
import com.ccifuentes.equifax.security.models.dto.MessageDTO;
import com.ccifuentes.equifax.security.models.dto.NewUser;
import com.ccifuentes.equifax.security.models.dto.UserLogin;
import com.ccifuentes.equifax.security.jwt.JwtProvider;
import com.ccifuentes.equifax.security.service.interfaces.IUserService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private IUserService usuarioService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/new_user")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<MessageDTO>(new MessageDTO("campos mal puestos o email inválido"),
					HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByMail(nuevoUsuario.getEmail()))
			return new ResponseEntity<MessageDTO>(new MessageDTO("ese email ya existe"), HttpStatus.BAD_REQUEST);
		usuarioService.createNewUser(nuevoUsuario);
		return new ResponseEntity<MessageDTO>(new MessageDTO("usuario guardado"), HttpStatus.CREATED);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new MessageDTO("campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginUsuario.getNombreUsuario().toLowerCase(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
}
