package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.time.LocalDate;
import java.util.List;

public class main {

    public static void main(String[] args){

//test find
        try {
            List<Client> clients = ClientService.getInstance().findAll();
            System.out.println(clients);
            List<Vehicle> vehicules = VehicleService.getInstance().findAll();
            System.out.println(vehicules);
            Vehicle vehicule = VehicleService.getInstance().findById(3);
            System.out.println(vehicule);
            Client client = ClientService.getInstance().findById(3);
            System.out.println(client);
            List<Reservation> toutesResa= ReservationService.getInstance().findAll();
            System.out.println(toutesResa);
            List<Reservation> ClientResa= ReservationService.getInstance().findResaByClientId(3);
            System.out.println(ClientResa);
            List<Reservation> VoitureResa= ReservationService.getInstance().findResaByVehicleId(3);
            System.out.println(VoitureResa);


           /* jeanpierre.setPrenom("jeanpierre");
            jeanpierre.setNom("Jaanjean");
            jeanpierre.setEmail("abc@epf");
            jeanpierre.setNaissance(LocalDate.parse("2002-02-02"));
            long idJP=ClientService.getInstance().create(jeanpierre);*/

            /*Client Unclient= new Client();
            Unclient.setClient_id(5);
            ClientService.getInstance().delete(Unclient);
            List<Client> lesclients = ClientService.getInstance().findAll();
            System.out.println(lesclients);*/

        }
        catch (ServiceException e){
            e.printStackTrace();
        }







    }
}
