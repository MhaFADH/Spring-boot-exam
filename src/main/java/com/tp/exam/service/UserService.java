package com.tp.exam.service;

import com.tp.exam.model.UserModel;
import com.tp.exam.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public UserModel findById(Integer id) {
        return userRepo.findById(id).get();
    }

    public boolean existsByEmail (String email){
        return userRepo.existsByEmail(email);
    }
    public void save(UserModel userInfos) {
        this.userRepo.save(userInfos);
    }

    public UserModel getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user =  userRepo.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


}
