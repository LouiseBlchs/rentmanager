package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/vehicles/create")
public class VehicleCreateServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {


        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request,response);
        }

        protected void doPost(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {

try {
    String brand = request.getParameter("manufacturer");
    String modele = request.getParameter("modele");
    int nb_places=Integer.parseInt(request.getParameter("seats"));
Vehicle vehicle= new Vehicle (brand, modele, nb_places);
VehicleService.getInstance().create(vehicle);
} catch (ServiceException e){
                e.printStackTrace();

            }
            this.doGet(request,response);

        }




}
