/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

package Actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exception.ClientNullException;
import exception.ConnectionFailException;
import exception.IncompatibleTypeException;
import exception.InfoClientUpdateException;
import exception.MissingInformationException;
import exception.NotLoggedException;
import exception.NullAvailableProductException;
import exception.SignUpException;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConsulterInterventionEmployeeAction extends ActionEmployee{
    
    Gson gson;
    JsonObject json;
    
    public ConsulterInterventionEmployeeAction(ServicesEmployee servicesEmployee){
        super(servicesEmployee);
    }
    
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, NotLoggedException, SignUpException, 
            NullAvailableProductException, ClientNullException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException, InfoClientUpdateException, 
            ParseException{
        gson = new GsonBuilder().setPrettyPrinting().create();
        json = new JsonObject();
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterInterventionEmployeeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        Employee e = (Employee) req.getSession().getAttribute(SESSION_EMPLOYEE_FIELD);
        Intervention i = this.servicesEmployee.getPendingIntervention(e);
        json.addProperty("firstNameEmployee", e.getFirstName());
        json.addProperty("nameClient", i.getClient().getName());
        json.addProperty("firstNameClient", i.getClient().getFirstName());
        json.addProperty("description", i.getDescription());
        json.addProperty("date", i.getCreationDate().toString());
        json.addProperty("type", i.getType());
        out.println(gson.toJson(json));
    }
}

*/