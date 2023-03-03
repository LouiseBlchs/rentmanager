package com.epf.rentmanager.model;

import java.util.Objects;

public class Vehicle {
private long vehicle_id;
private String constructeur;
private String modele;
int nb_places;

    public Vehicle(long vehicle_id, String constructeur,String modele, int nb_places) {
        this.vehicle_id = vehicle_id;
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public String getModele() {
        return modele;
    }

   public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return vehicle_id == vehicle.vehicle_id && nb_places == vehicle.nb_places && Objects.equals(constructeur, vehicle.constructeur) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle_id, constructeur, nb_places);
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", constructeur='" + constructeur  + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }
}
