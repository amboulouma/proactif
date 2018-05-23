/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kyobi
 */
public class ConsulterTableauDeBordEmployeeAction extends ActionEmployee{
    public ConsulterTableauDeBordEmployeeAction(ServicesEmployee servicesEmployee){
        super(servicesEmployee);
    }
    
    public void execute (HttpServletRequest req, HttpServletResponse res){
        req.setAttribute(RESULTS_FIELD, servicesEmployee.getTodayInterventions());
    }
}
