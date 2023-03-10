package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

import com.epf.rentmanager.service.ClientService;


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

    protected void doGet(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {


        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {

        try {
            String nom= request.getParameter("last_name");
            String prenom= request.getParameter("first_name");
            String email=request.getParameter("email");
            LocalDate birthDate= LocalDate.parse(request.getParameter("birth_date"));
            Client client= new Client (nom,prenom,email,birthDate);
            ClientService.getInstance().create(client);
        } catch (ServiceException e){
            e.printStackTrace();

        }
        this.doGet(request,response);

    }


}
