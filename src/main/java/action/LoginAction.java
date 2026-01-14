package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import model.User;
import repository.UserRepository;
import util.SessionUtil;

public class LoginAction implements Action {

    private UserRepository userRepository = new UserRepository();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // GETメソッド
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/WEB-INF/views/login.jsp";
        }

        // POSTメソッド
        return processLogin(request);
    }

    private String processLogin(HttpServletRequest request) {
        // パラメータの取得
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //入力チェック
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "ユーザー名とパスワードを入力してください");
            request.setAttribute("username", username); // 入力値を保持 [cite: 1149]
            return "/WEB-INF/views/login.jsp";
        }

        //ユーザー検索とパスワード確認
        User user = userRepository.findByUsername(username);
        
       
        if (user != null && user.getPassword().equals(password)) {
            // 4. ログイン成功
            SessionUtil.login(request, user);
            return "redirect:/app/hello"; 
        } else {
            //ログイン失敗
            request.setAttribute("errorMessage", "ユーザー名またはパスワードが間違っています");
            request.setAttribute("username", username);
            return "/WEB-INF/views/login.jsp";
        }
    }
}