/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import Actions.*;
import Exceptions.*;

import fr.insalyon.dasi.proactif.dao.JpaUtil;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;

import com.google.gson.Gson;
import fr.insalyon.dasi.proactif.entities.Client;

import java.io.IOException;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amine Boulouma, Clément Florant
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    private ServicesClient servicesClient;
    private ServicesEmployee servicesEmployee;
    private JSONHandler jsonHandler;

    
    public ActionServlet() {
        this.servicesClient = new ServicesClient();
        this.servicesEmployee = new ServicesEmployee();
        this.jsonHandler = new JSONHandler();
    }
    
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
        //servicesEmployee.createEmployees(); // Ajouter cette ligne pour créer les employés dans la base de donnees
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
                action = new HistoriqueClientAction(servicesClient);
                break;
                
            case "demandeInterventionClient":
                action = new DemandeInterventionClientAction(servicesClient);
                break;
                
            case "nombreInterventionsClient":
                action = new NombreInterventionsClientAction(servicesClient);
                break;
                
            case "deconnexionClient":
                action = new DeconnexionClient(servicesClient);
                break;
                
            case "connexionEmployee":
                action = new ConnexionEmployeeAction(servicesEmployee);
                break;
                
            case "consulterInterventionEmploye":
                action = new ConsulterInterventionEmployeeAction(servicesEmployee);
                break;
                
            case "conclureInterventionEmploye":
                action = new ConclureInterventionEmployee(servicesEmployee);
                break;
                
            case "tableauDeBordEmploye":
                action = new ConsulterTableauDeBordEmployeeAction(servicesEmployee);
                break;
                
            case "deconnexionEmployee":
                action = new DeconnexionEmployee(servicesEmployee);
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
        } catch (MissingInformationException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InfoClientUpdateException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncompatibleTypeException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoResultException ex){
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex){
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DeconnexionException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        switch(todo)
        {
            case "inscriptionClient":
                jsonHandler.sendClient(req, res);
                break;
                
            case "connexionClient":
                jsonHandler.sendClient(req, res);
                break;
                
            case "historiqueClient":
                jsonHandler.sendHistory(req, res);
                break;
                
            case "demandeInterventionClient":
                jsonHandler.sendInterventionEnregistree(req, res);
                break;
                
            case "nombreInterventionsClient":
                jsonHandler.sendNbInterventions(req, res);
                break;
                
            case "deconnexionClient":
                jsonHandler.sendDeconnexionClient(req, res);
                break;
                
            case "connexionEmployee":
                jsonHandler.sendEmployee(req, res);
                break;
                
            case "consulterInterventionEmploye":
                jsonHandler.sendInfosIntervention(req, res);
                break;
                
            case "conclureInterventionEmploye":
                jsonHandler.sendResolutionIntervention(req, res);
                break;
                
            case "tableauDeBordEmploye":
                jsonHandler.sendInterventions(req, res);
                break;
                
            case "deconnexionEmployee":
                jsonHandler.sendDeconnexionEmployee(req, res);
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
