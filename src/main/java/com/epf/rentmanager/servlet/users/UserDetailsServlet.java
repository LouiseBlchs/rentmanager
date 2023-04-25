package com.epf.rentmanager.servlet.users;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {

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

            request.setAttribute("clients", this.clientService.findById(Long.parseLong(request.getParameter("id"))));

        } catch (ServiceException e){
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request,response);
    }


}
