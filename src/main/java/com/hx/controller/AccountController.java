package com.hx.controller;

import com.google.common.collect.Lists;
import com.hx.domain.Account;
import com.hx.mapper.AccountMapper;
import com.hx.util.DateUtils;
import com.hx.util.HxException;
import com.hx.util.MD5;
import com.hx.util.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by huangch on 2019/7/23 09:58
 * description:
 *
 * @since JDK 1.6
 */
@Api(tags = {"用户管理"})
@RestController
@RequestMapping("account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountMapper accountMapper;

    @ApiOperation(value = "新增用户", httpMethod = "POST", notes = "新增用户", response = Boolean.class)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Boolean create(@RequestBody Account account) throws HxException {
        if (account == null || StringUtils.isBlank(account.getAccountName())) {
            logger.error("create account name is null");
            throw new HxException("用户名不能为空");
        }
        Account exitAccount = accountMapper.selectByName(account.getAccountName());
        if (exitAccount != null) {
            throw new HxException("用户名已存在");
        }
        if (account.getPassword() != null) {
            account.setPassword(MD5.md5(account.getPassword()));
        }
        return accountMapper.insertHxAccount(account) > 0;
    }

    @ApiOperation(value = "更新用户", httpMethod = "POST", notes = "更新用户", response = Boolean.class)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Boolean update(@RequestBody Account account) throws HxException {
        if (account == null || account.getId() == null) {
            throw new HxException("用户ID不能为空");
        }
        if (account.getPassword() != null) {
            account.setPassword(MD5.md5(account.getPassword()));
        }
        account.setGmtModified(DateUtils.getSecondStr(new Date()));
        accountMapper.updateHxAccount(account);
        return true;
    }

    @ApiOperation(value = "删除用户(可批量)", httpMethod = "POST", notes = "删除用户", response = Boolean.class)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "ids", required = true, value = "账号ID列表(字符串数组)", dataType = "array", paramType = "query")})
    public Boolean delete(@RequestParam List<Long> ids) throws HxException {
        if (CollectionUtils.isEmpty(ids)) {
            throw new HxException("用户IDS不能为空");
        }
        return accountMapper.deleteByIds(ids) > 0;
    }

    @ApiOperation(value = "用户列表查询(可查询所有)", httpMethod = "GET", notes = "用户列表查询", response = Account.class)
    @RequestMapping(value = "pageQuery", method = RequestMethod.GET)
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "currentPage", required = true, value = "当前页(默认1)", dataType = "integer", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "每页大小(默认20)，若想查询所有则该字段传入-1", dataType = "integer", paramType = "query", example = "1")})
    public PageResult<Account> pageQuery(@RequestParam Integer currentPage, @ModelAttribute @RequestParam Integer pageSize) throws HxException {
        if (currentPage == null) {
            throw new HxException("分页参数不能为空");
        }
        if (pageSize == null) {
            pageSize = 20;
        }

        int start = (currentPage - 1) * pageSize;
        int limit = pageSize;

        Integer count = accountMapper.countAll();
        if (count == 0) {
            return new PageResult(count, Lists.newArrayList(), pageSize);
        }

        List<Account> accountList = accountMapper.pageQueryAll(start, limit);

        return new PageResult(count, accountList, pageSize);
    }

    @ApiOperation(value = "查询用户详情", httpMethod = "GET", notes = "查询用户详情", response = Account.class)
    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "id", required = true, value = "账号ID", dataType = "long", paramType = "query", example = "1")})
    public Account queryById(@RequestParam Long id) throws HxException {
        if (id == null) {
            throw new HxException("查询的用户ID不能为空");
        }

        return accountMapper.selectById(id);
    }
}
