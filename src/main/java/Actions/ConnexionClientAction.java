/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;

/**
 *
 * @author cflorant
 */
public class ConnexionClientAction extends Action {
    String login;
    String password;
    ServicesClient servicesClient;

    public ConnexionClientAction(String login, String password, ServicesClient servicesClient) {
        super();
        this.login = login;
        this.password = password;
        this.servicesClient = servicesClient;
    }
    
    @Override
    public boolean execute() {
        
        if (servicesClient.connection(login, password) != null) {
            return true;
        }else{
            return false;
        }
    }
}
