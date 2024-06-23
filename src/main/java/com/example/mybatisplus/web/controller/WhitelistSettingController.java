package com.example.mybatisplus.web.controller;

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

    @RequestMapping("regist")
    public JsonResponse regist(){
        return JsonResponse.success(1);
    }

    @RequestMapping("registzsy")
    public JsonResponse registzsy(){
        return JsonResponse.success(1);
    }
}



