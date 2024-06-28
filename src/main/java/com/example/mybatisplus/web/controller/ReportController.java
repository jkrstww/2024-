package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.dto.PageDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ReportService;
import com.example.mybatisplus.model.domain.Report;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-06-26
 * @version v1.0
 */
@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("insert")
    public JsonResponse insert(@RequestBody Report report) {
        reportService.save(report);
        return JsonResponse.success("添加成功");
    }

    @PostMapping("update")
    public JsonResponse update(@RequestBody Report report) {
        reportService.updateById(report);
        return JsonResponse.success("修改成功");
    }

    @PostMapping("getReport")
    public JsonResponse getReport(@RequestBody PageDTO pageDTO) {
        Page<Report> page = reportService.getReportPage(pageDTO);
        return JsonResponse.success(page);
    }
}

