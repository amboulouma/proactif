/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cflorant
 */
public abstract class ActionEmployee extends Action{
    
    public static final String SESSION_EMPLOYEE_FIELD = "employe";
    
    ServicesEmployee servicesEmployee;
    
    public ActionEmployee(ServicesEmployee servicesEmployee)
    {
        this.servicesEmployee = servicesEmployee;
    }
    
    public boolean isEmployee(HttpServletRequest request, HttpServletResponse response)
    {
        HttpSession session = request.getSession(true);

        Employee user = (Employee) session.getAttribute(SESSION_EMPLOYEE_FIELD);

        return user != null;
    }
}
