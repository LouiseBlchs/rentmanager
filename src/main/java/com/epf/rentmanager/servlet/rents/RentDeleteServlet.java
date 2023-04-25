package com.epf.rentmanager.servlet.rents;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/rents/delete")
public class RentDeleteServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

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
            reservationService.delete(reservationService.findResaById(id));
            response.sendRedirect("/rentmanager/rents");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }




}
