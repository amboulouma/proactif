/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cflorant
 */
public abstract class Action {
    
    public static final String RESULTS_FIELD = "results";
    
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
