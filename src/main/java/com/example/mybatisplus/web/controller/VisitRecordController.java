package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.mapper.WhitelistSettingMapper;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.example.mybatisplus.model.dto.FirstVisitNotificationDTO;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.VisitConclusionDTO;
import com.example.mybatisplus.model.dto.VisitConclusionSearchDTO;
import com.example.mybatisplus.service.WhitelistSettingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.VisitRecordService;
import com.example.mybatisplus.model.domain.VisitRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/visitRecord")
public class VisitRecordController {
    @Autowired
    private VisitRecordService visitRecordService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private WhitelistSettingMapper whitelistSettingMapper;

    // 管理员查看所有记录
    @RequestMapping("/getAll")
    public JsonResponse getAll() {
        List<VisitRecord> list = visitRecordService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("reserveRequest")
    public JsonResponse reserveRequest(@RequestBody VisitRecord visitRecord) {
        Long id = SecurityUtils.getUserInfo().getId();
        String sId = whitelistSettingMapper.selectById(id).getSn();
        visitRecord.setSId(sId);

        visitRecordService.save(visitRecord);
        return JsonResponse.success(visitRecord);
    }

    @PostMapping("/insert")
    public JsonResponse insert(@RequestBody VisitRecord visitRecord) {
        if (visitRecordService .teacherConflict(visitRecord)) {
            return JsonResponse.failure("初访员忙碌");
        } else if (visitRecordService.locationConflict(visitRecord)) {
            return JsonResponse.failure("地点被占用");
        } else {
            visitRecordService.save(visitRecord);
            return JsonResponse.success("匹配成功");
        }
    }

    @PostMapping("/update")
    public JsonResponse update(@RequestBody VisitRecord visitRecord) {
        if (visitRecordService.teacherConflict(visitRecord)) {
            return JsonResponse.failure("初访员忙碌");
        } else if (visitRecordService.locationConflict(visitRecord)) {
            return JsonResponse.failure("地点被占用");
        } else {
            visitRecordService.updateById(visitRecord);
            return JsonResponse.success("修改成功");
        }
    }

    @PostMapping("/updateById")
    public JsonResponse updateById(@RequestBody VisitRecord visitRecord) {
        if (visitRecordService.teacherConflict(visitRecord)) {
            return JsonResponse.failure("初访员忙碌");
        } else if (visitRecordService.locationConflict(visitRecord)) {
            return JsonResponse.failure("地点被占用");
        } else {
            visitRecordService.updateById(visitRecord);
            return JsonResponse.success("修改成功");
        }
    }

    // 学生查看自己的预约记录
    @PostMapping("/getMyList")
    public JsonResponse getMyList(@RequestBody PageDTO pageDTO) {
        Long id = SecurityUtils.getUserInfo().getId();
        String sId = whitelistSettingMapper.selectById(id).getSn();
        Page<VisitRecord> page = visitRecordService.myPageList(sId, pageDTO);
        return JsonResponse.success(page);
    }

    // 学生查看自己的已批准的预约记录
    @PostMapping("/getMyApprovedList")
    public JsonResponse getMyApprovedList(@RequestBody PageDTO pageDTO) {
        Long id = SecurityUtils.getUserInfo().getId();
        String sId = whitelistSettingMapper.selectById(id).getSn();
        Page<VisitRecord> page = visitRecordService.myApprovedPageList(sId, pageDTO);
        return JsonResponse.success(page);
    }

    // 管理员分页查询
    @PostMapping("/pageList")
    public JsonResponse pageList(@RequestBody PageDTO pageDTO) {
        Page<VisitRecord> page = visitRecordService.pageList(pageDTO);
        return JsonResponse.success(page);
    }

    // 撤销预约
    @PostMapping("/cancleRecord")
    public JsonResponse cancleRecord(@RequestBody VisitRecord visitRecord) {
        // 记录的id
        Long id = visitRecord.getId();
        if (visitRecordService.cancleAbled(id)) {
          visitRecordService.removeById(id);
          return JsonResponse.success("撤销成功");
        } else {
            return JsonResponse.failure("撤销失败");
        }
    }

    // 根据时间查看未完成的预约记录
    @PostMapping("/getUnfinishedVisitRecordsByTime")
    public JsonResponse getUnfinishedVisitRecordsByTime(@RequestBody PageDTO pageDTO) {
        Page<VisitRecord> page = visitRecordService.unfinishedPageListByTime(pageDTO);
        return JsonResponse.success(page);
    }

    // 根据风险等级查看未完成的预约记录
    @PostMapping("/getUnfinishedVisitRecordsByRisk")
    public JsonResponse getUnfinishedVisitRecordsByRisk(@RequestBody PageDTO pageDTO) {
        Page<VisitRecord> page = visitRecordService.unfinishedPageListByRisk(pageDTO);
        return JsonResponse.success(page);
    }

    // 获取已批准的未完成的预约记录
    @PostMapping("/getAwaitUpdateVisitRecords")
    public JsonResponse getAwaitUpdateVisitRecords(@RequestBody PageDTO pageDTO) {
        Page<VisitRecord> page = visitRecordService.awaitUpdatePageList(pageDTO);
        return JsonResponse.success(page);
    }

    // 拒绝申请
    @PostMapping("/rejectVisitRequest")
    public JsonResponse rejectVisitRequest(@RequestBody VisitRecord visitRecord) {
        System.out.println(visitRecord);
        VisitRecord newVisitRecord = visitRecord.setStatus("已拒绝");
        visitRecordService.updateById(newVisitRecord);
        return JsonResponse.success(newVisitRecord);
    }

    // 初访员根据查询条件获取初访记录
    @PostMapping("/visitorQueryRecords")
    public JsonResponse visitorQueryRecords(@RequestBody VisitConclusionSearchDTO visitConclusionSearchDTO) {
        List<VisitConclusionDTO> list = visitRecordService.visitorQueryRecords(visitConclusionSearchDTO);
        Integer total = visitRecordService.visitorQueryRecordsTotal(visitConclusionSearchDTO);
        return JsonResponse.success(new HashMap<Object, Object>() {{
            put("total", total);
            put("records", list);
        }});
    }

    @GetMapping("sendVisitNoitification")
    public JsonResponse sendVisitNotification(FirstVisitNotificationDTO firstVisitNotificationDTO, VisitRecord visitRecord) {
        visitRecordService.sendVisitNotification(firstVisitNotificationDTO, visitRecord);
        return JsonResponse.success(null);
    }
}

