package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Report;
import com.example.mybatisplus.mapper.ReportMapper;
import com.example.mybatisplus.model.dto.PageDTO;
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
}
