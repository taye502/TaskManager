package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

public class SessionUtil {
    
    private static final String LOGIN_USER_KEY = "loginUser";

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getLoginUser(request) != null;
    }

    public static User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        try {
            return (User) session.getAttribute(LOGIN_USER_KEY);
        } catch (ClassCastException e) {
            session.removeAttribute(LOGIN_USER_KEY);
            return null;
        }
    }

    public static void login(HttpServletRequest request, User user) {

        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        HttpSession newSession = request.getSession(true);
        

        newSession.setAttribute(LOGIN_USER_KEY, user);
    }


    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
    }
}