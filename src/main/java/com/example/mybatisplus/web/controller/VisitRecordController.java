package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.dto.PageDTO;
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
import java.util.List;

@RestController
@RequestMapping("/api/visitRecord")
public class VisitRecordController {
    @Autowired
    private VisitRecordService visitRecordService;
    @Autowired
    private HttpServletRequest request;

    // 管理员查看所有记录
    @RequestMapping("/getAll")
    public JsonResponse getAll() {
        List<VisitRecord> list = visitRecordService.list();
        return JsonResponse.success(list);
    }

    @PostMapping("reserveRequest")
    public JsonResponse reserveRequest(@RequestBody VisitRecord visitRecord) {
        String sId = (String) request.getSession().getAttribute("sn");
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

    // 学生查看自己的预约记录
    @PostMapping("/getMyList")
    public JsonResponse getMyList(@RequestBody PageDTO pageDTO) {
        String id = (String)request.getSession().getAttribute("sn");
        Page<VisitRecord> page = visitRecordService.myPageList(id, pageDTO);
        return JsonResponse.success(page);
    }

    // 学生查看自己的已批准的预约记录
    @PostMapping("/getMyApprovedList")
    public JsonResponse getMyApprovedList(@RequestBody PageDTO pageDTO) {
        String id = (String)request.getSession().getAttribute("sn");
        Page<VisitRecord> page = visitRecordService.myApprovedPageList(id, pageDTO);
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
}

