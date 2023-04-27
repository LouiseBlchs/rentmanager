package com.epf.rentmanager.util;

import com.epf.rentmanager.model.Client;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;

public class Users {
    /**
     * Renvoie true si l’utilisateur passé en paramètre a un age >= 18 ans
     * @param user L'instance d’utilisateur à tester
     * @return Résultat du test (>= 18 ans)
     */
    public static boolean isLegal(Client user) {
        return
                user.getNaissance().isAfter(ChronoLocalDate.from(LocalDateTime.now().minus(18, ChronoUnit.YEARS)));
    }

    public static boolean isValidName(Client user) {
                 return user.getPrenom().length()>3 && user.getNom().length()>3;
    }
}

