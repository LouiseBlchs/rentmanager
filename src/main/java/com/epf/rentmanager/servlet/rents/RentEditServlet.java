package com.epf.rentmanager.servlet.rents;

import com.epf.rentmanager.checker.ReservationCheckers;
import com.epf.rentmanager.exception.AvailableException;
import com.epf.rentmanager.exception.SameUserException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/rents/edit")
public class RentEditServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

    @Autowired

    ClientService clientService;
    @Autowired

    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {
       try {
           long id = Long.parseLong(request.getParameter("id"));
           request.setAttribute("id", id);
           Reservation reservation = this.reservationService.findResaById(id);
           request.setAttribute("reservation", reservation);
        request.setAttribute("listUsers",this.clientService.findAll());
        request.setAttribute("listVehicles",this.vehicleService.findAll());
           request.setAttribute("client1",reservation.getClient_id());
           request.setAttribute("car1",reservation.getVehicle_id());
           request.setAttribute("begin1",reservation.getDebut());
           request.setAttribute("end1",reservation.getFin());
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request,response);}
       catch (ServiceException ex){
           ex.printStackTrace();
       }
    }

    protected void doPost(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {

        try {

            long client_id= Long.parseLong(request.getParameter("client"));
            long vehicle_id= Long.parseLong(request.getParameter("car"));
            LocalDate debut= LocalDate.parse(request.getParameter("begin"));
            LocalDate fin= LocalDate.parse(request.getParameter("end"));
            Long id = Long.parseLong(request.getParameter("id"));

            Reservation reservation= new Reservation(id,client_id,vehicle_id,debut,fin);

           if (ReservationCheckers.AvailableCheck(reservation, reservationService.findResaByVehicleId(vehicle_id))){
               throw new AvailableException("Une voiture ne peut pas être réservée deux fois le même jour.");
           }
            if (ReservationCheckers.SameUserCheck(reservation)){
                throw new SameUserException("Un utilisateur ne peut pas réserver une voiture plus de 7 jours de suite.");
            }
            reservationService.edit(reservation);

        } catch (ServiceException e){
            e.printStackTrace();

        }
        catch (AvailableException e) {
            request.setAttribute("erreur", "Une voiture ne peut pas être réservée deux fois le même jour.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);

        }
        catch (SameUserException e) {
            request.setAttribute("erreur", "Un utilisateur ne peut pas réserver une voiture plus de 7 jours de suite.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);

        }
        response.sendRedirect("/rentmanager/rents");
    }


}
