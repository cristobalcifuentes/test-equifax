package com.ccifuentes.equifax.security.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ccifuentes.equifax.security.models.entity.MainUser;
import com.ccifuentes.equifax.security.models.entity.User;
import com.ccifuentes.equifax.security.service.interfaces.IUserService;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService usuarioSer;
    

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        User user = usuarioSer.getUserByMail(nombreUsuario.toLowerCase()).get();
        return MainUser.build(user);
    }
}
