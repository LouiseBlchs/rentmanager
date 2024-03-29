package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.NameException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.UsedMailException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.exception.MajorException;
import com.epf.rentmanager.checker.ClientCheckers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    @Autowired

    ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {


            String nom = request.getParameter("last_name");
            String prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birth_date"));
            Client client = new Client(nom, prenom, email, birthDate);


            if (ClientCheckers.MajorCheck(client)) {
                throw new MajorException("Le client doit être majeur");
            }
            if (ClientCheckers.NameCheck(client)) {
                throw new NameException("Le prénom et le nom du client doivent contenir plus de 3 caractères.");
            }

            if (ClientCheckers.MailCheck(client, clientService.findAll())) {
                throw new UsedMailException("Un client est déjà enregistré dans la base de données avec cette adresse mail.");
            }
            clientService.create(client);
            response.sendRedirect("/rentmanager/users");


        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (UsedMailException e) {
            request.setAttribute("erreur", "Un client est déjà enregistré dans la base de données avec cette adresse mail.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        } catch (NameException e) {
            request.setAttribute("erreur", "Le prénom et le nom du client doivent contenir plus de 3 caractères.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

        } catch (MajorException e) {
            request.setAttribute("erreur", "Le client doit être majeur.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);

        }


    }


}
