package com.epf.rentmanager.servlet;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
/*import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/users")
public class UserCreateServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			VehicleService vehicleService=VehicleService.getInstance();
			ClientService clientService=ClientService.getInstance();
			ReservationService reservationService=ReservationService.getInstance();

			request.setAttribute("nbClients",clientService.CountClient());
			request.setAttribute("nbVehicles",vehicleService.CountVehicle());
			request.setAttribute("nbReservations",reservationService.CountReservation());
			request.getRequestDispatcher("./WEB-INF/views/users.jsp").forward(request, response);

		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

}*/
