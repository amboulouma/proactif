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
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cflorant
 */
public class ConnexionEmployeeAction extends ActionEmployee{
    
    Gson gson;
    JsonObject json;

    public ConnexionEmployeeAction(ServicesEmployee servicesEmployee) {
        super(servicesEmployee);
    }
    
    
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        gson = new GsonBuilder().setPrettyPrinting().create();
        json = new JsonObject();
        PrintWriter out = response.getWriter();
        Employee e = servicesEmployee.connection(login, password);
        if (e != null) {
            session.setAttribute("employe", e);
            json.addProperty("connected", true);
        }else{
            json.addProperty("connected", false);
        }
        out.println(gson.toJson(json));
    }
}

