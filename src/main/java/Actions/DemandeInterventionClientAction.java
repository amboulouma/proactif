/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.entities.InterventionDelivery;
import fr.insalyon.dasi.proactif.entities.InterventionIncident;
import fr.insalyon.dasi.proactif.entities.InterventionPet;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kyobi
 */
public class DemandeInterventionClientAction extends ActionClient {
    public DemandeInterventionClientAction(ServicesClient servicesClient) {
        super(servicesClient);
    }
    
    public void execute(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(true);
        Client c = (Client) session.getAttribute(ActionClient.SESSION_CLIENT_FIELD);
        System.out.println(c);
        String livraison = req.getParameter("livraison");
        String incident = req.getParameter("incident");
        String animal = req.getParameter("animal");
        String objet = req.getParameter("objet");
        String entreprise = req.getParameter("entreprise");
        String animalName = req.getParameter("animalName");
        String description = req.getParameter("description");
        Intervention i;
        if (livraison.equals("true")){
            i = new InterventionDelivery(c, description, objet, entreprise);
        }
        else if (incident.equals("true")){
            i = new InterventionIncident(c, description);
        }
        else{
            i = new InterventionPet(c, description, animalName);
        }
        System.out.println(i);
        i = servicesClient.interventionRequest(i);
        if (i == null)
        {
            req.setAttribute(RESULTS_FIELD, false);
            System.out.println("failed");
        }
        else
        {
            req.setAttribute(RESULTS_FIELD, true);
            System.out.println("success");
        }
    }
}
