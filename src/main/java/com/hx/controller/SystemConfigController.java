package com.hx.controller;

import com.hx.domain.SeoConfig;
import com.hx.domain.WebsiteConfig;
import com.hx.mapper.SeoConfigMapper;
import com.hx.mapper.WebsiteConfigMapper;
import com.hx.util.DateUtils;
import com.hx.util.HxException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by huangch on 2019/7/23 09:58
 * description:
 *
 * @since JDK 1.6
 */
@Api(tags = {"系统设置"})
@RestController
@RequestMapping("system")
public class SystemConfigController {

    @Autowired
    private SeoConfigMapper seoConfigMapper;

    @Autowired
    private WebsiteConfigMapper websiteConfigMapper;

    @ApiOperation(value = "站点配置", httpMethod = "POST", notes = "站点配置", response = Boolean.class)
    @RequestMapping(value = "webSiteConfig", method = RequestMethod.POST)
    public Boolean webSiteConfig(@RequestBody WebsiteConfig websiteConfig) throws HxException {
        if (websiteConfig == null || StringUtils.isBlank(websiteConfig.getSiteName())|| StringUtils.isBlank(websiteConfig.getSiteDomain())) {
            throw new HxException("参数不能为空");
        }

        WebsiteConfig exitWebSiteConfig = websiteConfigMapper.selectWebSiteConfig();

        if (exitWebSiteConfig == null) {
            websiteConfigMapper.insertHxWebsiteConfig(websiteConfig);
        } else {
            websiteConfig.setId(exitWebSiteConfig.getId());
            websiteConfig.setGmtModified(DateUtils.getSecondStr(new Date()));
            websiteConfigMapper.updateHxWebsiteConfig(websiteConfig);
        }
        return true;
    }

    @ApiOperation(value = "站点查询", httpMethod = "GET", notes = "站点查询", response = WebsiteConfig.class)
    @RequestMapping(value = "queryWebSite", method = RequestMethod.GET)
    public WebsiteConfig queryWebSite() throws HxException {
        return websiteConfigMapper.selectWebSiteConfig();
    }

    @ApiOperation(value = "SEO配置", httpMethod = "POST", notes = "SEO配置", response = Boolean.class)
    @RequestMapping(value = "seoConfig", method = RequestMethod.POST)
    public Boolean seoConfig(@RequestBody SeoConfig seoConfig) throws HxException {
        if (seoConfig == null || StringUtils.isBlank(seoConfig.getSeoTitle())) {
            throw new HxException("参数不能为空");
        }

        SeoConfig exitSeoConfig = seoConfigMapper.selectSeoConfig();

        if (exitSeoConfig == null) {
            seoConfigMapper.insertHxSeoConfig(seoConfig);
        } else {
            seoConfig.setId(exitSeoConfig.getId());
            seoConfig.setGmtModified(DateUtils.getSecondStr(new Date()));
            seoConfigMapper.updateHxSeoConfig(seoConfig);
        }
        return true;
    }

    @ApiOperation(value = "SEO查询", httpMethod = "GET", notes = "SEO查询", response = SeoConfig.class)
    @RequestMapping(value = "querySeo", method = RequestMethod.GET)
    public SeoConfig querySeo() throws HxException {
        return seoConfigMapper.selectSeoConfig();
    }
}
