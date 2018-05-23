/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.entities.InterventionIncident;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kyobi
 */
public class ConclureInterventionEmployee extends ActionEmployee{
    public ConclureInterventionEmployee(ServicesEmployee servicesEmployee){
        super(servicesEmployee);
    }
    
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException, NoResultException{
        String dateFinIntervention = req.getParameter("dateFinIntervention");
        String heureFinIntervention = req.getParameter("heureFinIntervention");
        String issueTerminee = req.getParameter("issueTerminee");
        String issueProbleme = req.getParameter("issueProbleme");
        String commentaireIntervention = req.getParameter("commentaireIntervention");
        HttpSession session = req.getSession(true);
        Employee e = (Employee) session.getAttribute(ActionEmployee.SESSION_EMPLOYEE_FIELD);
        Intervention i = servicesEmployee.getPendingIntervention(e);
        if (issueTerminee.equals("true"))
            servicesEmployee.closeIntervention(i, issueTerminee+" "+commentaireIntervention);
        else
            servicesEmployee.closeIntervention(i, issueProbleme+" "+commentaireIntervention);
        req.setAttribute(RESULTS_FIELD, true);
    }
}
