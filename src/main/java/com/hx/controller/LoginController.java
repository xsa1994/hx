package com.hx.controller;

import com.hx.cookie.LocalCookie;
import com.hx.domain.Account;
import com.hx.mapper.AccountMapper;
import com.hx.util.HxException;
import com.hx.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huangch on 2019/7/23 09:58
 * description:
 *
 * @since JDK 1.6
 */
@Api(tags = {"用户管理"})
@RestController
@RequestMapping("")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountMapper accountMapper;

    @ApiOperation(value = "用户登录", httpMethod = "POST", notes = "用户登录", response = Boolean.class)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Account login(@ModelAttribute @RequestBody Account account) throws HxException {
        if (account == null || StringUtils.isBlank(account.getAccountName())) {
            logger.error("login account name is null");
            throw new HxException("用户名不能为空");
        }

        if (account.getPassword() == null) {
            logger.error("login account password is null");
            throw new HxException("用户密码不能空");
        }

        Account accountInfo = accountMapper.selectByName(account.getAccountName());
        if (accountInfo == null) {
            throw new HxException("用户不存在");
        }

        if (!MD5.md5(account.getPassword()).equals(accountInfo.getPassword())) {
            throw new HxException("用户密码不正确");
        }

        LocalCookie.createLoginCookie(accountInfo);

        return accountInfo;
    }

    @ApiOperation(value = "登出用户", httpMethod = "POST", notes = "登出用户", response = Boolean.class)
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Boolean logout(@ModelAttribute @RequestBody Account account) throws HxException {
        if (account == null || account.getId() == null) {
            throw new HxException("用户ID不能为空");
        }
        LocalCookie.deleteLogoutCookie();
        return true;
    }

}
