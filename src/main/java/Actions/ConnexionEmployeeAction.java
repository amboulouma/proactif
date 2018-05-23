/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Exceptions.IncompatibleTypeException;
import Exceptions.*;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cflorant
 */

public class ConnexionEmployeeAction extends ActionEmployee{

    public ConnexionEmployeeAction(ServicesEmployee servicesEmployee) {
        super(servicesEmployee);
    }
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, NotLoggedException, SignUpException, 
            NullAvailableProductException, ClientNullException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException, InfoClientUpdateException, 
            ParseException, IOException
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        Employee e = servicesEmployee.connection(login, password);
        if (e == null) {
            throw new ConnectionFailException();
        }
        session.setAttribute(ActionEmployee.SESSION_EMPLOYEE_FIELD, e);
        req.setAttribute(RESULTS_FIELD, e);
    }
}


