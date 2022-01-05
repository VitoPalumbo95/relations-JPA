package com.example.relazioni.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "articles")
/* Il modulo JPA abbiamo questa annotazione sulla classe di implementazione che supporta il proxy
(SimpleJpaRepository). Questo per due motivi: in primo luogo,
la persistenza e l'eliminazione degli oggetti richiede una transazione in JPA. Pertanto,
dobbiamo assicurarci che una transazione sia in esecuzione,
cosa che facciamo annotando il metodo con @Transactional.*/
@Transactional
public class ArticlesEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long article_id;

    @Column(nullable = false, length = 100)
    private String title;

    /* viene utilizzato per ignorare la proprietà logica utilizzata nella serializzazione e deserializzazione.
     @JsonIgnore può essere utilizzato su setter, getter o field.*/
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_article", joinColumns = {
            @JoinColumn(name = "article_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> userEntity = new ArrayList<>();

    public ArticlesEntity() {
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(List<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }
}
