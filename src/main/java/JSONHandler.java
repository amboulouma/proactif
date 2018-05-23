
import Actions.Action;
import Actions.ActionClient;
import Actions.ActionEmployee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.entities.Employee;
import fr.insalyon.dasi.proactif.entities.Intervention;
import fr.insalyon.dasi.proactif.entities.InterventionIncident;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aboulouma
 */
public class JSONHandler {
    
    Gson gson;
    JsonObject json;
    
    public JSONHandler() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        json = new JsonObject();
    }
    
    
    public void sendClient(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Client user = (Client) req.getAttribute(Action.RESULTS_FIELD);
        res.getWriter().print(gson.toJson(user));
    }
    
    
    public void sendNbInterventions(HttpServletRequest req, HttpServletResponse res) throws IOException{
        HttpSession session = req.getSession(true);
        Client user = (Client) session.getAttribute(ActionClient.SESSION_CLIENT_FIELD);
        json.addProperty("clientFirstName", user.getFirstName());
        json.addProperty("nbIntervention", req.getAttribute(Action.RESULTS_FIELD).toString());
        res.getWriter().print(json);
    }
    
    
    public void sendInterventionEnregistree(HttpServletRequest req, HttpServletResponse res) throws IOException{
        boolean interventionEnregistree = (boolean) req.getAttribute(Action.RESULTS_FIELD);
        if (interventionEnregistree){
            json.addProperty("interventionEnregistree", Boolean.TRUE);
        }
        else{
            json.addProperty("interventionEnregistree", Boolean.FALSE);
        }
        res.getWriter().print(json);
    }
    
    
    public void sendDeconnexionClient(HttpServletRequest req, HttpServletResponse res) throws IOException{
        if (req.getSession(true) == null){
            json.addProperty("deconnexion", Boolean.TRUE);
        }
        else{
            json.addProperty("deconnexion", Boolean.FALSE);
        }
        res.getWriter().print(json);
    }
    
    
    public void sendEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Employee user = (Employee) req.getAttribute(Action.RESULTS_FIELD);
        res.getWriter().print(gson.toJson(user));
    }
    
    
    public void sendInfosIntervention(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Intervention i = (Intervention) req.getAttribute(Action.RESULTS_FIELD);
        res.getWriter().print(gson.toJson(i));
    }
    
    
    public void sendResolutionIntervention(HttpServletRequest req, HttpServletResponse res) throws IOException{
        boolean interventionTerminee = (boolean) req.getAttribute(Action.RESULTS_FIELD);
        if (interventionTerminee){
            json.addProperty("interventionTerminee", Boolean.TRUE);
        }
        else{
            json.addProperty("interventionTerminee", Boolean.FALSE);
        }
        res.getWriter().print(json);
    }
    
    
    public void sendDeconnexionEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException{
        if (req.getSession(true) == null){
            json.addProperty("deconnexion", Boolean.TRUE);
        }
        else{
            json.addProperty("deconnexion", Boolean.FALSE);
        }
        res.getWriter().print(json);
    }
    
    
    public void sendInterventions(HttpServletRequest req, HttpServletResponse res) throws IOException{
        List<Intervention> interventions = (List<Intervention>)req.getAttribute(Action.RESULTS_FIELD);
        res.getWriter().print(gson.toJson(interventions));
    }

    
    public void sendHistory(HttpServletRequest req, HttpServletResponse res) throws IOException{
        List<Intervention> interventions = (List<Intervention>)req.getAttribute(Action.RESULTS_FIELD);
        res.getWriter().print(gson.toJson(interventions));
    }
        
}
