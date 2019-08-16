package com.hx.mapper;

import com.hx.domain.WebsiteConfig;


public interface WebsiteConfigMapper {

    int insertHxWebsiteConfig(WebsiteConfig object);

    int updateHxWebsiteConfig(WebsiteConfig object);

    WebsiteConfig selectWebSiteConfig();
}