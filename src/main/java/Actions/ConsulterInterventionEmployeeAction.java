/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cflorant
 */
public class ConsulterInterventionEmployeeAction extends ActionEmployee{
    
    public ConsulterInterventionEmployeeAction(ServicesEmployee servicesEmployee){
        super(servicesEmployee);
    }
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, NoResultException{
        HttpSession session = req.getSession(true);
        Employee e = (Employee) session.getAttribute(ActionEmployee.SESSION_EMPLOYEE_FIELD);
        Intervention i = this.servicesEmployee.getPendingIntervention(e);
        if (i != null)
        {
            req.setAttribute(RESULTS_FIELD, i);
        }
    }
}
