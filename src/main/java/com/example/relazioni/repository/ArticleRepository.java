package com.example.relazioni.repository;

import com.example.relazioni.entity.ArticlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/* Qui sto creando il repository estendendolo con la JPA(Le Java Persistence API,
talvolta riferite come JPA, sono un framework per il linguaggio di programmazione Java che si occupa della gestione della persistenza dei dati di un DBMS relazionale nelle applicazioni
che usano le piattaforme Java Platform, Standard Edition e Java Enterprise Edition.)*/
public interface ArticleRepository extends JpaRepository<ArticlesEntity, Long> {
}
