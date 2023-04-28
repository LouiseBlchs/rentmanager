package com.epf.rentmanager.servlet.cars;

import com.epf.rentmanager.checker.VehicleCheckers;
import com.epf.rentmanager.exception.CharacteristicsException;
import com.epf.rentmanager.exception.SeatsException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/vehicles/edit")
public class VehicleEditServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    @Autowired
    VehicleService vehicleService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);
            Vehicle vehicle = this.vehicleService.findById(id);
            request.setAttribute("manufacturer1", vehicle.getConstructeur());
            request.setAttribute("model1", vehicle.getModele());
            request.setAttribute("places1", vehicle.getNb_places());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String brand = request.getParameter("manufacturer");
            String modele = request.getParameter("modele");
            int nb_places = Integer.parseInt(request.getParameter("seats"));
            Long id = Long.parseLong(request.getParameter("id"));
            Vehicle vehicle = new Vehicle(id, brand, modele, nb_places);
            if (VehicleCheckers.CharacteristicsCheck(vehicle)) {
                throw new CharacteristicsException("Le constructeur et le modèle doivent être indiqués.");
            }
            if (VehicleCheckers.SeatsCheck(vehicle)) {
                throw new SeatsException("Le nombre de places doit être compris entre 2 et 9.");
            }
            vehicleService.edit(vehicle);
            response.sendRedirect("/rentmanager/vehicles");
        } catch (ServiceException e) {
            e.printStackTrace();

        } catch (CharacteristicsException e) {
            request.setAttribute("erreur", "Le constructeur et le modèle doivent être indiqués.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
        } catch (SeatsException e) {
            request.setAttribute("erreur", "Le nombre de places doit être compris entre 2 et 9.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
        }


    }


}
