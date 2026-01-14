package action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;


public class HelloWorldAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        
        request.setAttribute("message", "FrontController Pattern Works!");
        request.setAttribute("currentTime", new Date());
        request.setAttribute("pathInfo", request.getPathInfo());
        request.setAttribute("userAgent", request.getHeader("User-Agent"));
        request.setAttribute("remoteAddr", request.getRemoteAddr());

        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            request.setAttribute("message", "Hello, " + name + "!");
        }
        
        return "/WEB-INF/views/hello.jsp";
    }
}