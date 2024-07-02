package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.ReportPageDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-26
 */
public interface ReportService extends IService<Report> {

    Page<Report> getReportPage(PageDTO pageDTO);

    Page<Report> queryReport(ReportPageDTO reportPageDTO);

    // 查询进行中的咨询记录
    Integer recordTimes(String sn, String consultant);

    void generate(Report report);
}
