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


    protected void doGet(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {
try {
    long id = Long.parseLong(request.getParameter("id"));
    request.setAttribute("id", id);
    request.setAttribute("client", this.clientService.findById(id));

    this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/edit.jsp").forward(request, response);

}catch (ServiceException ex){
    ex.printStackTrace();
        }
}

    protected void doPost(HttpServletRequest   request,   HttpServletResponse response) throws ServletException, IOException       {

        try {
            String nom= request.getParameter("last_name");
            String prenom= request.getParameter("first_name");
            String email=request.getParameter("email");
            LocalDate birthDate= LocalDate.parse(request.getParameter("birth_date"));
            Long id = Long.parseLong(request.getParameter("id"));
            Client client= new Client (id,nom,prenom,email,birthDate);
            clientService.edit(client);
        } catch (ServiceException e){
            e.printStackTrace();

        }
        response.sendRedirect("/rentmanager/users");

    }


}
