package com.epf.rentmanager.checker;


import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ClientCheckers {



public static boolean MajorCheck (Client client){

    return client.getNaissance().isAfter(ChronoLocalDate.from(LocalDateTime.now().minus(18, ChronoUnit.YEARS)));

}


public static boolean MailCheck (Client client, List<Client> allClients){

        for(Client allClient: allClients){

            if (client.getEmail().toUpperCase().equals(allClient.getEmail().toUpperCase()))
            {
                return true;
            }
    }

        return false;

    }

    public static boolean NameCheck (Client client){

    return client.getNom().length()<3 || client.getPrenom().length()<3;

    }



}
