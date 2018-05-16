/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import exception.*;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Amine Boulouma, Clément Florant
 */
public abstract class Action {
    public static final String RESULTS_FIELD = "results";
    
    public Action() {
        
    }
    

    public abstract void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, NotLoggedException, SignUpException, 
            NullAvailableProductException, ClientNullException, ConnectionFailException, 
            IncompatibleTypeException, MissingInformationException, InfoClientUpdateException, 
            ParseException;
}
