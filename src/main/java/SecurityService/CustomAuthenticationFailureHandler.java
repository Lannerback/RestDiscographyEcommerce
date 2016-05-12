/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SecurityService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 *
 * @author adriano
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {           
        super.onAuthenticationFailure(request, response, exception);


        if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
            logger.warn(exception.getMessage());
        } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
            logger.warn(exception.getMessage());
        } else if (exception.getClass().isAssignableFrom(BadCredentialsException.class)){
            logger.warn(exception.getMessage());
        }
          else if (exception.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)){
            logger.warn(exception.getMessage());
        }         
        //saveException(request, exception);
    }   

}
