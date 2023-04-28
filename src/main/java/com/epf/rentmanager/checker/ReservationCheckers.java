package com.epf.rentmanager.checker;


import com.epf.rentmanager.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ReservationCheckers {


    public static boolean AvailableCheck(Reservation reservation, List<Reservation> allReservations) {
        for (Reservation allReservation : allReservations) {
            if ((reservation.getFin().isAfter(allReservation.getDebut()) && reservation.getDebut().isBefore(allReservation.getFin())) || (allReservation.getFin().isAfter(reservation.getDebut()) && allReservation.getDebut().isBefore(reservation.getFin()))) {
                return true;
            }
        }
        return false;
    }

    public static boolean SameUserCheck(Reservation reservation) {
        return reservation.getFin().toEpochDay() - reservation.getDebut().toEpochDay() > 7;
    }


}