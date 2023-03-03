package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {

	private VehicleService vehicleService= VehicleService.getInstance();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			request.setAttribute("vehicles", this.vehicleService.findAll());
		} catch (ServiceException e){
			e.printStackTrace();
		}



		this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request,response);
		

	}

}
