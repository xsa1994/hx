package com.hx.controller;

import com.google.common.collect.Lists;
import com.hx.domain.MenuPage;
import com.hx.mapper.MenuPageMapper;
import com.hx.util.DateUtils;
import com.hx.util.HxException;
import com.hx.util.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@Api(tags = {"单页管理"})
@RestController
@RequestMapping("menuPage")
public class MenuPageController {

    @Autowired
    private MenuPageMapper menuPageMapper;

    @ApiOperation(value = "添加单页", httpMethod = "POST", notes = "添加单页", response = Boolean.class)
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Boolean create(@ModelAttribute MenuPage menuPage) throws HxException {
        if (menuPage == null || menuPage.getKindId() == null || menuPage.getPageName() == null) {
            throw new HxException("参数为空");
        }

        return menuPageMapper.insertHxMenuPage(menuPage) > 0;
    }

    @ApiOperation(value = "修改单页(包括修改排序)", httpMethod = "POST", notes = "修改单页", response = Boolean.class)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Boolean update(@ModelAttribute MenuPage menuPage) throws HxException {
        if (menuPage == null || menuPage.getId() == null) {
            throw new HxException("ID不能为空");
        }
        menuPage.setGmtModified(DateUtils.getSecondStr(new Date()));
        menuPageMapper.updateHxMenuPage(menuPage);
        return true;
    }

    @ApiOperation(value = "删除单页(可批量)", httpMethod = "POST", notes = "删除单页", response = Boolean.class)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "ids", required = true, value = "页面ID列表(字符串数组)", dataType = "array", paramType = "query")})
    public Boolean delete(@ModelAttribute @RequestParam List<Long> ids) throws HxException {
        if (CollectionUtils.isEmpty(ids)) {
            throw new HxException("删除IDS不能为空");
        }
        return menuPageMapper.deleteByIds(ids) > 0;
    }

    @ApiOperation(value = "单页列表查询(可查询所有)", httpMethod = "GET", notes = "单页列表查询", response = MenuPage.class)
    @RequestMapping(value = "pageQuery", method = RequestMethod.GET)
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "currentPage", required = true, value = "当前页(默认1)", dataType = "integer", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", required = true, value = "每页大小(默认20，若想查询所有则该字段传入-1)", dataType = "integer", paramType = "query", example = "1")})
    public PageResult<MenuPage> pageQuery(@ModelAttribute @RequestParam Integer currentPage, @ModelAttribute @RequestParam Integer pageSize) throws HxException {
        if (currentPage == null) {
            throw new HxException("分页参数不能为空");
        }
        if (pageSize == null) {
            pageSize = 20;
        }

        int start = (currentPage - 1) * pageSize;
        int limit = pageSize;

        Integer count = menuPageMapper.countAll();
        if (count == 0) {
            return new PageResult(count, Lists.newArrayList(), pageSize);
        }

        List<MenuPage> menuPages = menuPageMapper.pageQueryAll(start, limit);
        return new PageResult(count, menuPages, pageSize);
    }

    @ApiOperation(value = "查询页面详情", httpMethod = "GET", notes = "查询页面详情", response = MenuPage.class)
    @RequestMapping(value = "queryById", method = RequestMethod.GET)
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "id", required = true, value = "页面ID", dataType = "long", paramType = "query", example = "1")})
    public MenuPage queryById(@ModelAttribute @RequestParam Long id) throws HxException {
        if (id == null) {
            throw new HxException("查询ID不能为空");
        }
        return menuPageMapper.selectById(id);
    }
}
