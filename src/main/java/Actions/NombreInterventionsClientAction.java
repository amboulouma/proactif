/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kyobi
 */
public class NombreInterventionsClientAction extends ActionClient{
    public NombreInterventionsClientAction(ServicesClient servicesClient){
        super(servicesClient);
    }
    
    public void execute(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(true);
        Client user = (Client) session.getAttribute(SESSION_CLIENT_FIELD);
        long nbInterventions = servicesClient.getNumberOfActiveRequests(user);
        req.setAttribute(RESULTS_FIELD, nbInterventions);
    }
}
