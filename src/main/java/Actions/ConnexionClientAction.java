/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import exception.ConnectionFailException;
import exception.IncompatibleTypeException;
import exception.MissingInformationException;
import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Amine Boulouma, Clément Florant
 */
public class ConnexionClientAction extends ActionClient {
 
    public ConnexionClientAction(ServicesClient servicesClient) {
        super(servicesClient);
    }
    
    public void execute(HttpServletRequest req, HttpServletResponse res)
             throws ServletException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException {
        
        HttpSession session = req.getSession(true);
        
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        
        if (login == null || password == null) throw new MissingInformationException();
        
         try {

            Client user = this.servicesClient.connection(login, password);
            if (user == null) {
                throw new ConnectionFailException();
            }

            session.setAttribute(SESSION_CLIENT_FIELD, user);
            req.setAttribute(RESULTS_FIELD, user);
        } catch (Exception e) {
            throw new IncompatibleTypeException();
        }
    }
}
