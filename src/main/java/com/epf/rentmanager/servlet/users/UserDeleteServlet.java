package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.ServiceException;
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


@WebServlet("/users/delete")
public class UserDeleteServlet extends HttpServlet {



	private static final long serialVersionUID = 1L;

    @Autowired

    ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    protected void doGet(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {

        try {
            long id = Long.parseLong(request.getParameter("id"));
            clientService.delete(clientService.findById(id));
            response.sendRedirect("/rentmanager/users");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

    }




}
