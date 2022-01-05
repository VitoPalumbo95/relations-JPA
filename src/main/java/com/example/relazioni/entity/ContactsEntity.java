package com.example.relazioni.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class ContactsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContact;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 15)
    private String telephone_number;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    private UserEntity userEntity;

    public ContactsEntity() {}

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
