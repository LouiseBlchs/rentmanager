package com.epf.rentmanager.servlet.rents;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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


@WebServlet("/rents/create")
public class RentCreateServlet extends HttpServlet {



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
        request.setAttribute("listUsers",this.clientService.findAll());
        request.setAttribute("listVehicles",this.vehicleService.findAll());
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request,response);}
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
            Reservation reservation= new Reservation(client_id,vehicle_id,debut,fin);
            reservationService.create(reservation);
        } catch (ServiceException e){
            e.printStackTrace();

        }
        this.doGet(request,response);

    }


}