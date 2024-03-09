package com.tp.exam.security;

import com.tp.exam.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, IOException {
        UserModel user = (UserModel) authentication.getPrincipal();

        System.out.println("IN THIS LOOP " + user.getRole().getNom());
        String redirectUrl = switch (user.getRole().getNom()) {
            case "ADMIN" -> "/dashboard";
            case "USER" -> "/client";
            default -> "/login";
        };


        response.sendRedirect(redirectUrl);
    }
}