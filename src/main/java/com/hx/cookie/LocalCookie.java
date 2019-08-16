package com.hx.cookie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hx.domain.Account;
import com.hx.util.Base64;

import javax.servlet.http.Cookie;

/**
 * Created by huangch on 2019/8/9 16:35
 * description:
 *
 * @since JDK 1.6
 */
public class LocalCookie {

    private LocalCookie() {
    }

    public static final int COOKIE_EXPIRE = 60 * 60 * 24;

    public static final String ACCOUNT_KEY = "hx_account_xx";

    public static final String ACCOUNT_ID = "accountId";

    public static final String ACCOUNT_NAME = "accountName";

    public static final String LOGIN_TIME = "loginTime";

    public static void deleteCookie(String name) {
        Cookie cookie = createCookie(name, null);
        cookie.setMaxAge(0);
        RequestLocal.get().getResponse().addCookie(cookie);
    }

    public static void deleteLogoutCookie() {
        Cookie cookieTime = createCookie(LocalCookie.LOGIN_TIME, null);
        cookieTime.setMaxAge(0);
        RequestLocal.get().getResponse().addCookie(cookieTime);

        Cookie cookieId = createCookie(LocalCookie.ACCOUNT_ID, null);
        cookieId.setMaxAge(0);
        RequestLocal.get().getResponse().addCookie(cookieId);

        Cookie cookieName = createCookie(LocalCookie.ACCOUNT_NAME, null);
        cookieName.setMaxAge(0);
        RequestLocal.get().getResponse().addCookie(cookieName);

        Cookie cookieAccount = createCookie(LocalCookie.ACCOUNT_KEY, null);
        cookieAccount.setMaxAge(0);
        RequestLocal.get().getResponse().addCookie(cookieAccount);
    }

    public static Cookie createCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_EXPIRE);
        setDomain(cookie);
        return cookie;
    }

    public static void createLoginCookie(Account account) {
        // 登陆时间
        RequestLocal.get().getResponse().addCookie(createCookie(LocalCookie.LOGIN_TIME, System.currentTimeMillis() + ""));
        // 账号ID
        RequestLocal.get().getResponse().addCookie(createCookie(LocalCookie.ACCOUNT_ID, account.getId() + ""));
        //用户名
        RequestLocal.get().getResponse().addCookie(createCookie(LocalCookie.ACCOUNT_NAME, account.getAccountName()));
        //用户信息
        RequestLocal.get().getResponse().addCookie(createCookie(LocalCookie.ACCOUNT_KEY, Base64.encode(JSON.toJSONString(account)).replace("\n", "")));
    }

    private static void setDomain(Cookie cookie) {
        cookie.setDomain("www.hx.com");
    }
}
