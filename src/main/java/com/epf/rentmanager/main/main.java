package com.epf.rentmanager.main;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class main {

    public static void main(String[] args) throws DaoException {

//test find
        /* List<Client> clients = ClientService.getInstance().findAll();
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
         System.out.println(VoitureResa);*/


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

      // Reservation res=new Reservation(1,1,1, LocalDate.parse("2022-12-12"),LocalDate.parse("2022-02-01"));


        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);

    }
}
