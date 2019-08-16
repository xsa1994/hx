package com.hx.mapper;

import com.hx.domain.WebArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface WebArticleMapper {

    int insertHxWebArticle(WebArticle object);

    int updateHxWebArticle(WebArticle object);

    Integer deleteByIds(@Param("ids") List<Long> ids);

    Integer countAll();

    List<WebArticle> pageQueryAll(@Param("start") int start, @Param("limit") int limit);
}