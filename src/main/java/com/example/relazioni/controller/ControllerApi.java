package com.example.relazioni.controller;

import com.example.relazioni.entity.ArticlesEntity;
import com.example.relazioni.entity.UserEntity;
import com.example.relazioni.repository.ArticleRepository;
import com.example.relazioni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ControllerApi {

    /* L'annotazione @Autowired ti consente di spostarti
     altrove alle configurazioni di cosa iniettare e lo fa solo per te*/
    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    /* Un metodo POST dovrebbe essere utilizzato quando è necessario creare, aggiornare o eliminare
    dati sul lato server. Effettuare la stessa richiesta POST più volte potrebbe non essere sicuro
     e potrebbe causare dati incoerenti. Il contenuto di una richiesta POST viene inviato nel
     corpo della richiesta. Quindi, non vedi i parametri nel tuo browser.
    */
    @PostMapping("/api/v1/adduser")
    public void addUser(@RequestBody UserEntity entity) {
        UserEntity userSaved = userRepository.save(entity);
        for (ArticlesEntity articlesEntity : entity.getArticlesEntity()) {
            ArrayList<UserEntity> users = new ArrayList<>();
            users.add(userSaved);
            articlesEntity.setUserEntity(users);
            articleRepository.save(articlesEntity);
        }

    }

    /* È necessario utilizzare un metodo GET per recuperare i dati dal server.
    Più richieste get allo stesso URL dovrebbero essere valide e nessun dato dovrebbe
    essere modificato sul lato server.*/
    @GetMapping("/api/v1/users/{id}")
    UserEntity getUser(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/api/v1/getallusers")
    public ArrayList<UserEntity> allUsers() {
        return (ArrayList<UserEntity>) userRepository.findAll();
    }

}
