package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.checker.ClientCheckers;
import com.epf.rentmanager.exception.MajorException;
import com.epf.rentmanager.exception.NameException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.UsedMailException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/users/edit")
public class UserEditServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    @Autowired

    ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("id", id);
            Client client = this.clientService.findById(id);
            request.setAttribute("nom1", client.getNom());
            request.setAttribute("prenom1", client.getPrenom());
            request.setAttribute("email1", client.getEmail());
            request.setAttribute("naissance1", client.getNaissance());


            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String nom = request.getParameter("last_name");
            String prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));
            Long id = Long.parseLong(request.getParameter("id"));
            Client client = new Client(id, nom, prenom, email, birthDate);

            if (ClientCheckers.MajorCheck(client)) {
                throw new MajorException("Le client doit être majeur");


            }
            if (ClientCheckers.NameCheck(client)) {
                throw new NameException("Le prénom et le nom du client doivent contenir plus de 3 caractères.");
            }

            clientService.edit(client);
            response.sendRedirect("/rentmanager/users");
        } catch (ServiceException e) {
            e.printStackTrace();

        } catch (NameException e) {
            request.setAttribute("erreur", "Le prénom et le nom du client doivent contenir plus de 3 caractères.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);

        } catch (MajorException e) {
            request.setAttribute("erreur", "Le client doit être majeur.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);

        }


    }


}
