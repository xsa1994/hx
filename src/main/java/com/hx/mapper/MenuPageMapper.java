package com.hx.mapper;

import com.hx.domain.MenuPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MenuPageMapper {

    int insertHxMenuPage(MenuPage object);

    int updateHxMenuPage(MenuPage object);

    Integer deleteByIds(@Param("ids") List<Long> ids);

    Integer countAll();

    List<MenuPage> pageQueryAll(@Param("start") int start, @Param("limit") int limit);

    MenuPage selectById(@Param("id") Long id);
}