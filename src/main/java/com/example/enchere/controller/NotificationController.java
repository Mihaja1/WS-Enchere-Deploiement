package com.example.enchere.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enchere.exeption.RessourceException;
import com.example.enchere.modele.Enchere;
import com.example.enchere.modele.EnchereVendu;
import com.example.enchere.modele.Notifications;
import com.example.enchere.modele.Offre;
import com.example.enchere.modele.Token;
import com.example.enchere.repository.EnchereRepository;
import com.example.enchere.repository.EnchereVenduRepository;
import com.example.enchere.repository.NotificationsRepository;
import com.example.enchere.repository.OffreRepository;
import com.example.enchere.repository.TokenRepository;
import com.example.enchere.retour.ErrorRetour;
import com.example.enchere.service.NotificationService;
import com.example.enchere.service.ServiceEnchere;

@RestController
@RequestMapping("/pushnotification")
public class NotificationController {

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private NotificationsRepository notificationsRepository;

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EnchereVenduRepository enchereVenduRepository;

    @Autowired
    private ServiceEnchere services;

    @Autowired
    private TokenRepository tokenRepository;

    public void isTokenExipered(String tokenValues) throws Exception {
        try{
            if( tokenValues.equals("null")){
                throw new RessourceException(new ErrorRetour("Vous devez vous connecter",HttpStatus.BAD_REQUEST.value()));
            }
            Token token = tokenRepository.getToken(tokenValues);
            token.bearerToken(token);
        }
        catch(Exception e){
            throw e;
        }
    }

    @PostMapping("/sendNotification/{tokenValues}")
    public void sendNotification(@PathVariable String tokenValues)throws Exception
    {
        try{
            isTokenExipered(tokenValues);
            Token t = tokenRepository.getToken(tokenValues);
            services.finEnchere(t.getIdUtilisateur());
        }
        catch(Exception e){
            throw e;
        }
    }

    

}

