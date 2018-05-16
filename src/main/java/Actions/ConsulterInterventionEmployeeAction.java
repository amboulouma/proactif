/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cflorant
 */
public class ConsulterInterventionEmployeeAction extends ActionEmployee{
    
    Gson gson;
    JsonObject json;
    
    public ConsulterInterventionEmployeeAction(ServicesEmployee servicesEmployee){
        super(servicesEmployee);
    }
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
        gson = new GsonBuilder().setPrettyPrinting().create();
        json = new JsonObject();
        PrintWriter out = response.getWriter();
        Employee e = (Employee) request.getSession().getAttribute(SESSION_EMPLOYEE_FIELD);
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
