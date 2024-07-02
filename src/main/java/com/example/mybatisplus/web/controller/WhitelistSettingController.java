package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.dto.TeacherDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.WhitelistSettingService;
import com.example.mybatisplus.model.domain.WhitelistSetting;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-06-22
 * @version v1.0
 */
@RestController
@RequestMapping("/api/whitelistSetting")
public class WhitelistSettingController {
    @Autowired
    private WhitelistSettingService whitelistSettingService;

    @RequestMapping("login")
    public JsonResponse login(@RequestBody WhitelistSetting whitelistSetting) {
        WhitelistSetting user = whitelistSettingService.login(whitelistSetting);
        return JsonResponse.success(user);
    }

    @PostMapping("/queryAll")
    public JsonResponse queryAll(@RequestBody WhitelistSetting whitelistSetting) {
        return JsonResponse.success(whitelistSettingService.queryAll(whitelistSetting));
    }

    @GetMapping("/getAll")
    public JsonResponse getAll() {
        return JsonResponse.success(whitelistSettingService.list());
    }

    @GetMapping("/pageList")
    public JsonResponse getUserList(@RequestBody PageDTO pageDTO) {
        Page<WhitelistSetting> page = whitelistSettingService.getUserList(pageDTO);
        return JsonResponse.success(page);
    }

    @PostMapping("/teacherPageList")
    public JsonResponse getTeacherList(@RequestBody TeacherDTO teacherDTO) {
        Page<WhitelistSetting> page = whitelistSettingService.getTeacherList(teacherDTO);
        return JsonResponse.success(page);
    }

    @PostMapping("/add")
    public JsonResponse add(@RequestBody WhitelistSetting whitelistSetting) {
        if (whitelistSettingService.existUser(whitelistSetting)) return JsonResponse.failure("该用户存在");

        whitelistSettingService.save(whitelistSetting);
        return JsonResponse.success(whitelistSetting);
    }

    @PostMapping("/deleteById")
    public JsonResponse deleteById(@RequestBody WhitelistSetting whitelistSetting) {
        whitelistSettingService.removeById(whitelistSetting.getId());

        return JsonResponse.success(whitelistSetting);
    }

    // 更新
    @PostMapping("/updateById")
    public JsonResponse updateUser(@RequestBody WhitelistSetting whitelistSetting) {
        System.out.println(whitelistSetting);
        whitelistSettingService.updateById(whitelistSetting);
        return JsonResponse.success(whitelistSetting);
    }

    // 注册
    @PostMapping("/register")
    public JsonResponse register(@RequestBody WhitelistSetting whitelistSetting) {
        if (whitelistSettingService.existUser(whitelistSetting)) {
            return JsonResponse.failure("用户已存在");
        } else {
            whitelistSettingService.save(whitelistSetting);
            return JsonResponse.success(whitelistSetting);
        }
    }
}



