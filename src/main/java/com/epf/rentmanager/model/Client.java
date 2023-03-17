package com.epf.rentmanager.model;

import org.testng.annotations.Optional;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.Objects;

public class Client implements Optional {

    private String nom;
    private String prenom;
    private long client_id;
    private String email;
    private LocalDate naissance;


    public Client(long client_id, String nom, String prenom,  String email, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.client_id = client_id;
        this.email = email;
        this.naissance = naissance;
    }

    public Client(String nom, String prenom,  String email, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;

        this.email = email;
        this.naissance = naissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }


    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", id=" + client_id +
                ", mail='" + email + '\'' +
                ", dateNaissance=" + naissance +
                '}';
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return client_id == client.client_id && nom.equals(client.nom) && prenom.equals(client.prenom) && email.equals(client.email) && naissance.equals(client.naissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id,nom, prenom, email, naissance);
    }

    @Override
    public String value() {
        return null;
    }
}
