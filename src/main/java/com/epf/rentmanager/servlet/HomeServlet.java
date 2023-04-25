package com.epf.rentmanager.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@Repository
@WebServlet("/home")


public class HomeServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			request.setAttribute("nbClients",this.clientService.CountClient());
			request.setAttribute("nbVehicles",this.vehicleService.CountVehicle());
			request.setAttribute("nbReservations",this.reservationService.CountReservation());
			request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		}


	}

}
