package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.OnJobService;
import com.example.mybatisplus.model.domain.OnJob;


/**
 * 前端控制器
 *
 * @author lxp
 * @version v1.0
 * @since 2024-06-23
 */
@RestController
@RequestMapping("/api/onJob")
public class OnJobController {
    @Autowired
    private OnJobService onJobService;

    // 添加值班内容
    @PostMapping("/insert")
    public JsonResponse insert(@RequestBody OnJob onJob) {
        System.out.println(onJob);
        // 判断是否冲突
        if (onJobService.locationConflicted(onJob)) {
            return JsonResponse.failure("当前时刻该地点被占用");
        } else {
            Integer roleId = onJob.getRoleId();
            if (onJobService.personConflicted(onJob)) {
                if (roleId == 0) {
                    return JsonResponse.failure("当前时刻该初访员忙碌");
                } else {
                    return JsonResponse.failure("当前时刻该咨询师忙碌");
                }
            }
        }

        onJobService.save(onJob);
        return JsonResponse.success(onJob);
    }

    // 获取值班表
    @RequestMapping("/getAll")
    public JsonResponse getAll() {
        return JsonResponse.success(onJobService.list());
    }

    // 删除某个时间段的值班内容
    @RequestMapping("/deleteById")
    public JsonResponse deleteById(Integer id) {
        return JsonResponse.success(onJobService.removeById(id));
    }

    // 激活值班
    @RequestMapping("/statusUp")
    public JsonResponse statusUp(Integer id) {
        UpdateWrapper<OnJob> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("status", 1);
        onJobService.update(updateWrapper);
        return JsonResponse.success("修改成功");
    }

    // 关闭值班
    @RequestMapping("/statusDown")
    public JsonResponse statusDown(Integer id) {
        UpdateWrapper<OnJob> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("status", 0);
        onJobService.update(updateWrapper);
        return JsonResponse.success("修改成功");
    }
}

