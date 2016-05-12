/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityService;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

public class CustomAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
private String accessDeniedUrl;

    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        javax.swing.JOptionPane.showMessageDialog(null, "qua");
        //request.getSession().setAttribute("CustomSessionAttribute", "value here");
        RequestDispatcher r = request.getRequestDispatcher(accessDeniedUrl);
        r.forward(request, response);                
    }

    public String getAccessDeniedUrl() {
        return accessDeniedUrl;
    }

    public void setAccessDeniedUrl(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }

    
}
