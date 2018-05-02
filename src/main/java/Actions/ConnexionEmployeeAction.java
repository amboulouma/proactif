/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.services.ServicesClient;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;

/**
 *
 * @author cflorant
 */
public class ConnexionEmployeeAction extends Action{
    String login;
    String password;
    ServicesEmployee servicesEmployee;

    public ConnexionEmployeeAction(String login, String password, ServicesEmployee servicesEmployee) {
        super();
        this.login = login;
        this.password = password;
        this.servicesEmployee = servicesEmployee;
    }
    
    public boolean execute() {
        if (servicesEmployee.connection(this.login, this.password) != null) {
            return true;
        }else{
            return false;
        }
    }
}

