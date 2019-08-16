package com.hx.mapper;

import com.hx.domain.SeoConfig;


public interface SeoConfigMapper {

    int insertHxSeoConfig(SeoConfig object);

    int updateHxSeoConfig(SeoConfig object);

    SeoConfig selectSeoConfig();
}