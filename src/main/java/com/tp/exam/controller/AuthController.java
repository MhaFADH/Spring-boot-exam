package com.tp.exam.controller;

import com.tp.exam.exception.WsException;
import com.tp.exam.manager.Verification;
import com.tp.exam.model.RoleModel;
import com.tp.exam.model.UserModel;
import com.tp.exam.service.RoleService;
import com.tp.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userInfos", new UserModel());

        return "register";
    }



    @PostMapping("/register")
    public String  register(@ModelAttribute UserModel userInfos) throws WsException {
        if (!Verification.isValidEmailAddress(userInfos.getEmail())){
            throw new WsException(HttpStatus.BAD_REQUEST, "L'email est invalide");
        }
        if (this.userService.existsByEmail(userInfos.getEmail()) & userInfos.getId() == null){
            throw new WsException(HttpStatus.BAD_REQUEST, "L'email existe déja");
        }

        if (!Verification.isValidPassword(userInfos.getPassword())){
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe est vide");
        }

        if(userInfos.getId() != null){

            UserModel currentUser = this.userService.findById(userInfos.getId());
            currentUser.setNom(userInfos.getNom());
            currentUser.setPrenom(userInfos.getPrenom());
            currentUser.setEmail(userInfos.getEmail());
            currentUser.setPassword(passwordEncoder.encode(userInfos.getPassword()));
            currentUser.setTelephone(userInfos.getTelephone());
            currentUser.setAdresse(userInfos.getAdresse());
            this.userService.save(currentUser);

            return "redirect:/logout";
        }

        userInfos.setPassword(passwordEncoder.encode(userInfos.getPassword()));

        RoleModel role = this.roleService.findByNom("USER");
        if (role == null){
            role = this.roleService.save("USER");
        }

        userInfos.setRole(role);
        userInfos.setActif(true);

        this.userService.save(userInfos);



        return "redirect:/login";
    }
}
