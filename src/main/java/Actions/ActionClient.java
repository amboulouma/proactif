/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cflorant
 */
public abstract class ActionClient extends Action{
    public static final String SESSION_CLIENT_FIELD = "client";
    

    protected ServicesClient servicesClient;

    public ActionClient(ServicesClient servicesClient) {
        this.servicesClient = servicesClient;
    }


    protected boolean isClient(HttpServletRequest req, HttpServletResponse res)
            throws ServletException {

        HttpSession session = req.getSession(true);

        Client user = (Client) session.getAttribute(SESSION_CLIENT_FIELD);

        return user != null;
    }
}