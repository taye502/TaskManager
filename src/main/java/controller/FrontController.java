package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.HelloWorldAction;

@WebServlet("/app/*")
public class FrontController extends HttpServlet {
    
    
    private Map<String, Action> actionMap;

    @Override
    public void init() throws ServletException {
        try {
            actionMap = new HashMap<>();
            actionMap.put("hello", new HelloWorldAction());
            actionMap.put("login", new action.LoginAction());
            actionMap.put("logout", new action.LogoutAction());
            actionMap.put("task/list", new action.TaskListAction());
            
            System.out.println("FrontController initialized with " + actionMap.size() + " actions");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Failed to initialize FrontController", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String pathInfo = request.getPathInfo();
        String actionKey = normalizePathInfo(pathInfo);
        
        Action action = actionMap.get(actionKey);

        if (action == null) {
            
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not found: " + actionKey);
            return;
        }

        
        try {
            String result = action.execute(request, response);
            
            if (result.startsWith("redirect:")) {
                
                String redirectUrl = result.substring("redirect:".length());
                response.sendRedirect(request.getContextPath() + redirectUrl);
            } else {
                
                RequestDispatcher dispatcher = request.getRequestDispatcher(result);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Action execution failed", e);
        }
    }

    
    private String normalizePathInfo(String pathInfo) {
        if (pathInfo == null || pathInfo.equals("/")) {
            return "hello"; // デフォルトアクション
        }
        if (pathInfo.startsWith("/")) {
            return pathInfo.substring(1); // 先頭の"/"を除去
        }
        return pathInfo;
    }
}