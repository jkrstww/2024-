package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Report;
import com.example.mybatisplus.mapper.ReportMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.ReportPageDTO;
import com.example.mybatisplus.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxp
 * @since 2024-06-26
 */

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Page<Report> getReportPage(PageDTO pageDTO) {
        Page<Report> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        return reportMapper.selectPage(page, null);
    }

    @Override
    public Page<Report> queryReport(ReportPageDTO reportPageDTO) {
        Report report = reportPageDTO.getReport();
        PageDTO pageDTO = reportPageDTO.getPageDTO();

        Page<Report> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        if (report.getSId() != null && !report.getSId().isEmpty()) {
            queryWrapper.eq("s_id", report.getSId());
        }
        if (report.getSName() != null && !report.getSName().isEmpty()) {
            queryWrapper.like("s_name", report.getSName());
        }
        queryWrapper.eq("consult_teacher", report.getConsultTeacher());

        return reportMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Integer recordTimes(String sn, String consultant) {
        return reportMapper.recordTimes(sn, consultant);
    }

    @Override
    public void generate(Report report) {
        reportMapper.insert(report);

        String sn = report.getSId();
        String consultant = report.getConsultTeacher();
        reportMapper.finishRecord(sn, consultant);
    }
}
