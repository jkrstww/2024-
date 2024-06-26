package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.dto.PageDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.VisitRecordService;
import com.example.mybatisplus.model.domain.VisitRecord;

import java.util.List;

@RestController
@RequestMapping("/api/visitRecord")
public class VisitRecordController {
    @Autowired
    private VisitRecordService visitRecordService;

    // 管理员查看所有记录
    @RequestMapping("/getAll")
    public JsonResponse getAll() {
        List<VisitRecord> list = visitRecordService.list();
        return JsonResponse.success(list);
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
    @GetMapping("/getMyList")
    public JsonResponse getMyList(Integer id) {
        QueryWrapper<VisitRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("s_id", id);
        return JsonResponse.success(visitRecordService.list(queryWrapper));
    }

    // 管理员分页查询
    @GetMapping("/pageList")
    public JsonResponse pageList(PageDTO pageDTO) {
        Page<VisitRecord> page = visitRecordService.pageList(pageDTO);
        return JsonResponse.success(page);
    }

    // 撤销预约
    @RequestMapping("/cancleRecord")
    public JsonResponse cancleRecord(Integer id) {
        // 记录的id
        if (visitRecordService.cancleAbled(id)) {
          visitRecordService.removeById(id);
          return JsonResponse.success("撤销成功");
        } else {
            return JsonResponse.failure("撤销失败");
        }
    }
}

