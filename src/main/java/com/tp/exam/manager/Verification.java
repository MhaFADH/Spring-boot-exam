package com.tp.exam.manager;
import com.tp.exam.exception.WsException;
import org.springframework.http.HttpStatus;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Verification {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public static boolean isValidPassword(String password){
        if (password == null || password.isEmpty()){
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 6 caractères");
        }

        if (password.length() < 6){
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 6 caractères");
        }

        int nbrMaj = 0;
        int nbrMin = 0;
        int nbrNum = 0;
        for (int i = 0; i < password.length(); i++) {
            char caractere = password.charAt(i);
            if (caractere == ' '){
                throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe ne doit pas contenir d'espace");
            }
            if (caractere >= 'A' && caractere <= 'Z'){
                nbrMaj++;
            }
            if (caractere >= 'a' && caractere <= 'z'){
                nbrMin++;
            }
            if (caractere >= '0' && caractere <= '9'){
                nbrNum++;
            }
        }

        if (nbrMaj < 1){
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 2 majuscules");
        }
        if (nbrMin < 2){
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 2 minuscules");
        }
        if (nbrNum < 2){
            throw new WsException(HttpStatus.BAD_REQUEST, "Le mot de passe doit contenir au moins 2 chiffres");
        }
        return true;
    }

}