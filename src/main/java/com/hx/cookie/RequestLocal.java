package com.hx.cookie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hx.domain.Account;
import com.hx.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangch on 2019/8/9 16:35
 * description:
 *
 * @since JDK 1.6
 */
public class RequestLocal {

    private Logger logger = LoggerFactory.getLogger(RequestLocal.class);

    private static final int DAY_OF_MILLISECOND = 86400000;

    private static ThreadLocal<RequestLocal> local = new ThreadLocal<>();

    private Long accountId;

    private String accountName;

    private Account account;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private RequestLocal() {
    }

    public static RequestLocal get() {
        RequestLocal rl = local.get();
        if (rl == null) {
            rl = new RequestLocal();
            local.set(rl);
        }
        return rl;
    }


    public static void clear() {
        local.set(null);
    }


    private String getCookie(String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    String value = cookie.getValue();
                    if (!StringUtils.isEmpty(value)) {
                        return value;
                    }
                }
            }
        }

        return null;
    }


    public Account getAccountInfo() {
        if (account == null) {
            try {
                // 获取账号信息
                String cookieJson = getCookie(LocalCookie.ACCOUNT_KEY);
                if (cookieJson != null) {
                    JSONObject jsonObject = JSON.parseObject(cookieJson);
                    if (System.currentTimeMillis() - jsonObject.getLong(LocalCookie.LOGIN_TIME) < DAY_OF_MILLISECOND) {
                        account = JSON.parseObject(Base64.decode(jsonObject.getString(LocalCookie.ACCOUNT_KEY)), Account.class);
                    } else {
                        // 24小时过期, 删除cookie
                        LocalCookie.deleteCookie(LocalCookie.ACCOUNT_KEY);
                    }
                }
            } catch (Exception e) {
                LocalCookie.deleteCookie(LocalCookie.ACCOUNT_KEY);
                logger.error("parse cookie happen error", e);
            }
        }
        return account;
    }

    public Long getAccountId() {
        if (accountId == null && getAccountInfo() != null) {
            accountId = getAccountInfo().getId();
        }
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        if (accountName == null && getAccountInfo() != null) {
            accountName = getAccountInfo().getAccountName();
        }
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
