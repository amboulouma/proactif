/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import static Actions.Action.RESULTS_FIELD;
import static Actions.ActionClient.SESSION_CLIENT_FIELD;
import Exceptions.ConnectionFailException;
import Exceptions.IncompatibleTypeException;
import Exceptions.MissingInformationException;
import Exceptions.SignUpException;
import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amine Boulouma, Clément Florant
 */
public class InscriptionClientAction extends ActionClient {
 
    public InscriptionClientAction(ServicesClient servicesClient) {
        super(servicesClient);
    }
    
    public void execute(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException, SignUpException {
        
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String civilite = req.getParameter("civilite");
        String adresse = req.getParameter("adresse");
        String tel = req.getParameter("tel");
        String tempDate = req.getParameter("date");
        Date date = new Date();
        try{
            date = new SimpleDateFormat("dd-MM-yyyy").parse(tempDate);
        }catch(ParseException pe){}
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String cpassword = req.getParameter("cpassword");
        
        Client client = null;
        
        if (nom == null || prenom == null || email == null || adresse == null) 
            throw new MissingInformationException();
        else {
            client = new Client(nom, prenom, civilite, date, adresse, 
                     tel, email, password);
            if (servicesClient.createClient(client) == null) 
                throw new SignUpException();
        }
        
        req.setAttribute(RESULTS_FIELD, client);
    }
}