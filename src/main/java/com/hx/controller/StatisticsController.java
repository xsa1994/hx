package com.hx.controller;

import com.hx.domain.PageVisitRecord;
import com.hx.domain.PageVisitStatistics;
import com.hx.mapper.PageVisitRecordMapper;
import com.hx.util.RequestTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by huangch on 2019/7/23 09:58
 * description:
 *
 * @since JDK 1.6
 */
@Api(tags = {"统计管理"})
@RestController
@RequestMapping("statistics")
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    private PageVisitRecordMapper pageVisitRecordMapper;

    @ApiOperation(value = "网站访问量统计查询", httpMethod = "GET", notes = "网站访问量统计查询", response = PageVisitStatistics.class)
    @RequestMapping(value = "pageVisit", method = RequestMethod.GET)
    public List<PageVisitStatistics> pageVisit() {
        DateTime dt = DateTime.now();
        Date startDate = dt.minusDays(7).withTimeAtStartOfDay().toDate();
        List<PageVisitStatistics> statDataList = pageVisitRecordMapper.sumLatestWeekData(startDate, dt.withTimeAtStartOfDay().toDate());

        return statDataList.stream().peek(x -> x.setWeekDays(DateTime.parse(x.getCurDate()).getDayOfWeek())).collect(Collectors.toList());
    }

    @ApiOperation(value = "新增网站访问PV", httpMethod = "POST", notes = "新增网站访问PV", response = Boolean.class)
    @RequestMapping(value = "addPv", method = RequestMethod.POST)
    public Boolean addPv(@ModelAttribute @RequestBody PageVisitRecord pageVisitRecord, HttpServletRequest request) {
        if (pageVisitRecord == null || pageVisitRecord.getUserId() == null) {
            logger.error("add user visit record param is null");
            return false;
        }
        pageVisitRecord.setVisitIp(RequestTool.getIpAddr(request));
        pageVisitRecord.setUserAgent(RequestTool.getUserAgent(request));
        return pageVisitRecordMapper.insertHxPageVisitRecord(pageVisitRecord) > 0;
    }

}
