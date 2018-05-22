/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Actions.*;
import Exceptions.*;
import fr.insalyon.dasi.proactif.dao.JpaUtil;
import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;

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

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author Amine Boulouma, Clément Florant
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    private ServicesClient servicesClient;
    private ServicesEmployee servicesEmploye;

    
    public ActionServlet() {
        this.servicesClient = new ServicesClient();
        this.servicesEmploye = new ServicesEmployee();
    }
    
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    
    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.destroy();
    }

    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        
        String todo = req.getParameter("todo");
         
        if(todo == null) {
            // Handle todo null
        }
        
        Action action = null;        
        
        switch(todo)
        {
            case "inscriptionClient":
                action = new InscriptionClientAction(servicesClient);
                break;
                
            case "connexionClient":
                action = new ConnexionClientAction(servicesClient);
                break;
                
            case "historiqueClient":
                //action = new HistoriqueClientAction(servicesClient);
                break;
                
            case "demandeInterventionClient":
                //action = new DemandeInterventionClientAction(servicesClient);
                break;
                
            case "nombreInterventionsClient":
                //action = new NombreInterventionClient(servicesClient);
                break;
                
            case "connexionEmployee":
                //action = new ConnexionEmployeActions(servicesEmploye);
                /*
                login = request.getParameter("login");
                password = request.getParameter("password");
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                action = new ConnexionEmployeeAction(login, password, servicesEmployee);
                gson = new GsonBuilder().setPrettyPrinting().create();
                json = new JsonObject();
                json.addProperty("connected", action.execute());
                out.println(gson.toJson(json));
                */
                break;
                
            case "consulterInterventionEmploye":
                //action = new ConsulterInterventionEmployeAction(servicesEmploye);
                break;
                
            case "conclureInterventionEmploye":
                //action = new ConclureInterventionEmployeAction(servicesEmploye);
                break;
                
            case "tableauDeBordEmploye":
                //action = new TableauDeBordEmployeAction(servicesEmploye);
                break;
                
            case "deconnexionClient":
                //action = new DeconnexionActionClient(servicesClient);
                break;
                
            case "deconnexionEmploye":
                //action = new DeconnexionActionEmploye(servicesEmploye);
                break;
                
            default :
                // add not found action handler
                break;
        }
        
        boolean executed = false;
        
        try {
            action.execute(req, res);
            executed = true;
        } catch (NotLoggedException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignUpException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullAvailableProductException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientNullException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionFailException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncompatibleTypeException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingInformationException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InfoClientUpdateException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        switch(todo)
        {
            case "inscriptionClient":
                Client clientInscrit = (Client) req.getAttribute(Action.RESULTS_FIELD);
                res.getWriter().print(new Gson().toJson(clientInscrit));
                
                break;
            case "connexionClient":
                Client client = (Client) req.getAttribute(Action.RESULTS_FIELD);
                res.getWriter().print(new Gson().toJson(client));
                break;
                
            case "historiqueClient":
                
                break;
            case "demandeInterventionClient":
                
                break;
            case "nombreInterventionsClient":
                
                break;
            case "connexionEmployee":
                
                break;
            case "consulterInterventionEmploye":
                
                break;
            case "conclureInterventionEmploye":
                
                break;
            case "tableauDeBordEmploye":
                
                break;
            case "deconnexion":
                
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
