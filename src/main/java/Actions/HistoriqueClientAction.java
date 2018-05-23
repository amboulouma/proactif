/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.services.ServicesClient;
import Exceptions.*;

/**
 *
 * @author Amine M. Boulouma, Clément Florant
 */
import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.util.Filter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class HistoriqueClientAction extends ActionClient{
    
    public HistoriqueClientAction(ServicesClient servicesClient) {
        super(servicesClient);
    }
    
    
    public void execute(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException {
        HttpSession session = req.getSession(true);
        Client client = (Client) session.getAttribute(ActionClient.SESSION_CLIENT_FIELD);
        Filter filter = Filter.getFilter(0);
        String filtre = req.getParameter("filtre");
        switch(filtre) {
            case "toutes":
                filter = Filter.getFilter(0);
                break;
                
            case "livraisons":
                filter = Filter.getFilter(3);
                break;
                
            case "incidents":
                filter = Filter.getFilter(1);
                break;
                
            case "animeaux":
                filter = Filter.getFilter(2);
                break;
        }
        List<Intervention> interventions = this.servicesClient.getInterventions(client, filter);
        req.setAttribute(RESULTS_FIELD, interventions);
    }
}