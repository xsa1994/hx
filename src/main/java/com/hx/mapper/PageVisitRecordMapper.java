package com.hx.mapper;

import com.hx.domain.PageVisitRecord;
import com.hx.domain.PageVisitStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface PageVisitRecordMapper {

    int insertHxPageVisitRecord(PageVisitRecord object);

    int updateHxPageVisitRecord(PageVisitRecord object);

    List<PageVisitStatistics> sumLatestWeekData(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}