/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Actions.*;
import fr.insalyon.dasi.proactif.dao.JpaUtil;
import fr.insalyon.dasi.proactif.entities.Client;
import fr.insalyon.dasi.proactif.services.ServicesClient;
import fr.insalyon.dasi.proactif.services.ServicesEmployee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String todo = request.getParameter("todo");
        ServicesClient servicesClient = new ServicesClient();
        ServicesEmployee servicesEmployee = new ServicesEmployee();
        Action action;
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        Gson gson;
        JsonObject json;
        
        
        switch(todo)
        {
            case "inscriptionClient":
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String civilite = request.getParameter("civilite");
                String adresse = request.getParameter("adresse");
                String tel = request.getParameter("tel");
                String tempDate = request.getParameter("date");
                Date date = new Date();
                try{
                    date = new SimpleDateFormat("dd-MM-yyyy").parse(tempDate);
                }catch(ParseException pe){}
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String cpassword = request.getParameter("cpassword");
                Client client = new Client(nom, prenom, civilite, date, adresse, tel, email, password);
                action = new InscriptionClientAction(client, servicesClient);
                gson = new GsonBuilder().setPrettyPrinting().create();
                json = new JsonObject();
                boolean test = action.execute();
                System.out.println(test);
                json.addProperty("inscrit", test);
                out.println(gson.toJson(json));
                break;
                
                
            case "connexionClient":
                String login = request.getParameter("login");
                password = request.getParameter("password");
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                action = new ConnexionClientAction(login, password, servicesClient);
                gson = new GsonBuilder().setPrettyPrinting().create();
                json = new JsonObject();
                json.addProperty("connected", action.execute());
                out.println(gson.toJson(json));
                break;
            case "historiqueClient":
                
                break;
            case "demandeInterventionClient":
                
                break;
            case "nombreInterventionsClient":
                
                break;
            case "connexionEmployee":
                login = request.getParameter("login");
                password = request.getParameter("password");
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                action = new ConnexionEmployeeAction(login, password, servicesEmployee);
                gson = new GsonBuilder().setPrettyPrinting().create();
                json = new JsonObject();
                json.addProperty("connected", action.execute());
                out.println(gson.toJson(json));
                break;
            case "consulterInterventionEmploye":
                
                break;
            case "conclureInterventionEmploye":
                
                break;
            case "tableauDeBordEmploye":
                
                break;
            case "deconnexion":
                
                break;
            default :
                
                break;
        }
        /*response.setContentType("text/html;charset=UTF-8");
        // TODO output your page here. You may use following sample code. 
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ActionServlet</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
        out.println("</body>");
        out.println("</html>");
        */
        
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
