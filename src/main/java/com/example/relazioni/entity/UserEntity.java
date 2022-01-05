package com.example.relazioni.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Sto creando le entità e a ogni entità aggiungo un'annotation table che serve per assegnare
un nome alla tabella che farò nel database*/

@Entity
@Table(name = "user")
@Transactional
public class UserEntity {


    /*Sto assegnando un'id alla classe UserEntity*/
    @Id

    /* Questa annotazione indica che la chiave primaria viene generata automaticamente
    durante l'inserimento nel database. Senza questa annotazione,
    il valore dell'identificatore della chiave primaria deve essere assegnato prima
    dell'inserimento nel database.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    /* Questa annotation serve per indicare le caratteristiche della colonna name,
    in questo caso non può essere null e non può avere una lunghezza maggiore di 200 caratteri */
    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 200)
    private String surname;

    @Column(nullable = false, length = 100)
    private int code;

    /* Questa annotation indica il riferimento al quale sta facendo l'associazione la classe UserEntity per
    la classe base*/
    @JsonManagedReference

    /* Sto creando una relazione uno a uno: In una relazione uno a uno,
    un record in una tabella è associato a uno e un solo record in un'altra tabella*/
    @OneToOne(mappedBy = "userEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private ContactsEntity contactsEntity;

    @JsonManagedReference
    /* Una relazione uno-a-molti tra due entità è una relazione in cui ogni istanza dell'entità padre corrisponde
    a zero o più istanze dell'entità figlio, ma ogni istanza dell'entità
    figlio può corrispondere solo a esattamente un'istanza dell'entità padre.*/
    @OneToMany(mappedBy = "userEntity",
            /* Cascade = Le relazioni tra entità spesso dipendono dall'esistenza di un'altra entità,
            ad esempio la relazione Persona-Indirizzo. Senza la Persona,
            l'entità Indirizzo non ha alcun significato proprio. Quando eliminiamo l'entità Persona,
            anche la nostra entità Indirizzo dovrebbe essere eliminata.*/
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<CarsEntity> carsEntity = new HashSet<>();

    /* Una relazione molti a molti si verifica quando più record
    in una tabella sono associati a più record in un'altra tabella*/
    @ManyToMany(mappedBy = "userEntity",
            fetch = FetchType.LAZY)
    private List<ArticlesEntity> articlesEntity = new ArrayList<>();

    public UserEntity() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ContactsEntity getContactsEntity() {
        return contactsEntity;
    }

    public void setContactsEntity(ContactsEntity contactsEntity) {
        this.contactsEntity = contactsEntity;
    }

    public Set<CarsEntity> getCarsEntity() {
        return carsEntity;
    }

    public void setCarsEntity(Set<CarsEntity> carsEntity) {
        this.carsEntity = carsEntity;
    }

    public List<ArticlesEntity> getArticlesEntity() {
        return articlesEntity;
    }

    public void setArticlesEntity(List<ArticlesEntity> articlesEntity) {
        this.articlesEntity = articlesEntity;
    }
}
