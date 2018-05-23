/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Exceptions.DeconnexionException;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kyobi
 */
public class DeconnexionEmployee extends ActionEmployee{
    public DeconnexionEmployee(ServicesEmployee servicesEmployee){
        super(servicesEmployee);
    }
    
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IllegalStateException, DeconnexionException {
        if (this.isEmployee(req, res)){
            HttpSession session = req.getSession(true);
            session.invalidate();
        }
        else{
            throw new DeconnexionException();
        }
    }
}
