/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exception.*;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
    
    /**
     *
     * @param req
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws exception.NotLoggedException
     * @throws exception.SignUpException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, NotLoggedException, SignUpException, 
            NullAvailableProductException, ClientNullException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException, InfoClientUpdateException, 
            ParseException{
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        gson = new GsonBuilder().setPrettyPrinting().create();
        json = new JsonObject();
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionEmployeeAction.class.getName()).log(Level.SEVERE, null, ex);
        }
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


