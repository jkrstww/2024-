package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.service.impl.VisitRequestServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.VisitRequestService;
import com.example.mybatisplus.model.domain.VisitRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-06-23
 * @version v1.0
 */
@RestController
@RequestMapping("/api/visitRequest")
public class VisitRequestController {
    @Autowired
    private VisitRequestService visitRequestService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/insert")
    public JsonResponse insert(@RequestBody VisitRequest visitRequest) {
        // System.out.println(visitRequest);
        String sId = (String)request.getSession().getAttribute("sn");
        visitRequest.setSId(sId);
        visitRequestService.save(visitRequest);
        return JsonResponse.success("成功记录");
    }

    // 所有修改操作
    @PostMapping("/update")
    public JsonResponse finishTime(@RequestBody VisitRequest visitRequest) {
        visitRequestService.updateById(visitRequest);
        return JsonResponse.success("修改成功");
    }

    // 学生查看自己的预约记录
    @GetMapping("/getMyList")
    public JsonResponse getMyList(Integer id) {
        QueryWrapper<VisitRequest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", id);
        return JsonResponse.success(visitRequestService.list(queryWrapper));
    }

    // 全部分页
    @GetMapping("/pageList")
    public JsonResponse pagelist(PageDTO pageDTO) {
        Page<VisitRequest> page = visitRequestService.pageList(pageDTO);
        return JsonResponse.success(page);
    }
    // 只显示紧急分页
    @GetMapping("/riskPageList")
    public JsonResponse riskPageList(PageDTO pageDTO) {
        Page<VisitRequest> page = visitRequestService.riskPageList(pageDTO);
        return JsonResponse.success(page);
    }
    // 紧急优先排序+分页
    @GetMapping("/pageSortList")
    public JsonResponse pageSortList(PageDTO pageDTO) {
        Page<VisitRequest> page = visitRequestService.pageSortList(pageDTO);
        return JsonResponse.success(page);
    }

    // 批准一个
    @PostMapping("/approve")
    public JsonResponse approve(Integer id) {
        UpdateWrapper<VisitRequest> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("is_approved", 1).set("is_handle", 1);
        visitRequestService.update(updateWrapper);
        return JsonResponse.success("批准成功");
    }

    // 批量批准
    @PostMapping("/approveBatch")
    public JsonResponse approveBatch(@RequestBody List<Integer> ids) {
        UpdateWrapper<VisitRequest> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("is_approved", 1).set("is_handle", 1);
        visitRequestService.update(updateWrapper);
        return JsonResponse.success("批准成功");
    }

    // 拒绝一个
    @PostMapping("/reject")
    public JsonResponse reject(Integer id) {
        UpdateWrapper<VisitRequest> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("is_approved", 0).set("is_handle", 1);
        visitRequestService.update(updateWrapper);
        return JsonResponse.success("已拒绝");
    }

    // 拒绝批准
    @PostMapping("/rejectBatch")
    public JsonResponse rejectBatch(@RequestBody List<Integer> ids) {
        UpdateWrapper<VisitRequest> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("id", ids).set("is_approved", 0).set("is_handle", 1);
        visitRequestService.update(updateWrapper);
        return JsonResponse.success("已拒绝");
    }
}

