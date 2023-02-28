package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private long reservation_id;
    private Client client_id;
    private Vehicle vehicle_id;
    private LocalDate debut;
    private LocalDate fin;


    public Reservation(long reservation_id, Client client_id, Vehicle vehicle_id, LocalDate debut, LocalDate fin) {
        this.reservation_id = reservation_id;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    public long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public long getClient_id() {
        return client_id.getClient_id();
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public long getVehicle_id() {
        return vehicle_id.getVehicle_id();
    }

    public void setVehicle_id(Vehicle vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservation_id == that.reservation_id && Objects.equals(client_id, that.client_id) && Objects.equals(vehicle_id, that.vehicle_id) && Objects.equals(debut, that.debut) && Objects.equals(fin, that.fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation_id, client_id, vehicle_id, debut, fin);
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "reservation_id=" + reservation_id +
                ", client_id=" + client_id +
                ", vehicle_id=" + vehicle_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}
