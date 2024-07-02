package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.example.mybatisplus.service.WhitelistSettingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.StudentService;
import com.example.mybatisplus.model.domain.Student;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-06-30
 * @version v1.0
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private WhitelistSettingService whitelistSettingService;

    @PostMapping("/getInfo")
    public JsonResponse getInfo() {
        Long id = SessionUtils.getCurrentUserInfo().getId();
        Student student = studentService.getById(id);

        return JsonResponse.success(student);
    }

    @PostMapping("/updateInfo")
    public JsonResponse updateInfo(@RequestBody Student student) {
        Long id = SessionUtils.getCurrentUserInfo().getId();

        student.setId(id);

        studentService.updateById(student);

        UpdateWrapper<WhitelistSetting> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("phone_number", student.getPhoneNumber());
        whitelistSettingService.update(updateWrapper);

        return JsonResponse.success(student);
    }

    @PostMapping("/getInfoBySn")
    public JsonResponse getInfoBySn(@RequestBody Student student) {
        String sn = student.getSn();

        Student ret = studentService.getBySn(sn);
        return JsonResponse.success(ret);
    }
}

