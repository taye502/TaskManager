package util;

import javax.servlet.http.HttpServletRequest;

import model.User;

public class AuthUtil {

    /**
     * ログイン状態かどうかを判定する
     */
    public static boolean isLoggedIn(HttpServletRequest request) {
        return SessionUtil.getLoginUser(request) != null;
    }

    /**
     * ログインユーザー情報を取得する
     */
    public static User getLoginUser(HttpServletRequest request) {
        return SessionUtil.getLoginUser(request);
    }
}