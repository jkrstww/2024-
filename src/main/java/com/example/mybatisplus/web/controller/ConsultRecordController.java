package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.ConsultRequest;
import com.example.mybatisplus.model.domain.WhitelistSetting;
import com.example.mybatisplus.model.dto.*;
import com.example.mybatisplus.service.WhitelistSettingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultRecordService;
import com.example.mybatisplus.model.domain.ConsultRecord;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-06-24
 * @version v1.0
 */
@RestController
@RequestMapping("/api/consultRecord")
public class ConsultRecordController {

    private final Logger logger = LoggerFactory.getLogger( ConsultRecordController.class );

    @Autowired
    private ConsultRecordService consultRecordService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private WhitelistSettingService whitelistSettingService;


    // 改约时间
    @PostMapping(value = "/changetime")
    public JsonResponse changetime(@RequestBody ConsultRecord consultRecord) throws  Exception {
        consultRecordService.changetime(consultRecord);
        return JsonResponse.success("已改约时间");
    }

    // 改约地点
    @PostMapping(value = "/changelocation")
    public JsonResponse changelocation(@RequestBody ConsultRecord consultRecord) throws  Exception {
        consultRecordService.changelocation(consultRecord);
        return JsonResponse.success("已改约地点");
    }

    @PostMapping(value = "/changeteacher")
    public JsonResponse changeteacher(@RequestBody ConsultRecord consultRecord) throws  Exception {
        consultRecordService.changeteacher(consultRecord);
        return JsonResponse.success("已改约老师");
    }

    /**
     * 描述: 获取所有未处理的咨询申请
     */
    @PostMapping(value = "/getAllPendingRecord")
    public JsonResponse getAllPendingRecord() throws Exception{
        List<ConsultRecord> consultRecords = consultRecordService.list();
        return JsonResponse.success(consultRecords);
    }

    /**
     * 匹配学生和老师
     */
    @PostMapping(value = "/matchTacherandStudent")
    public JsonResponse matchTacherandStudent(@RequestBody ConsultRecord consultRecord) throws  Exception{
        System.out.println(consultRecord);
        int flag=consultRecordService.matchTacherandStudent(consultRecord);
        if(flag==1)
            return JsonResponse.success("成功匹配");
        else
            return JsonResponse.success("匹配冲突");

    }

    @PostMapping("/getConsultTimeEcharts")
    public JsonResponse getConsultTimeEcharts() throws  Exception {
        List<ConsultTeacherDTO> list = consultRecordService.getConsultTimeEcharts();
        list.forEach(s -> {
            s.setSumTime(s.getTimes()*2);
        });

        List<String> teacher = new ArrayList<>();
        List<Integer> times = new ArrayList<>();
        List<Integer> sumTime = new ArrayList<>();

        for (ConsultTeacherDTO consultTeacherDTO : list) {
            teacher.add(consultTeacherDTO.getName());
            times.add(consultTeacherDTO.getTimes());
            sumTime.add(consultTeacherDTO.getSumTime());
        }

        Map<Object, Object> map = new HashMap<Object, Object>() {{
            put("consultTeacher", teacher);
            put("times", times);
            put("sumTime", sumTime);
        }};

        return JsonResponse.success(map);
    }

    @PostMapping("deleteConsultRecordById")
    public JsonResponse deleteConsultRecordById(@RequestBody ConsultRecord consultRecord) throws  Exception {
        consultRecordService.removeById(consultRecord.getId());

        return JsonResponse.success(null);
    }

    @PostMapping("/getMyConsultRecords")
    public JsonResponse getMyConsultRecords(@RequestBody PageDTO pageDTO) throws  Exception {
        String sn = SessionUtils.getCurrentUserInfo().getSn();

        Page<ConsultRecord> page = consultRecordService.getMyListPage(sn, pageDTO);
        return JsonResponse.success(page);
    }

    @PostMapping("/consultRequest")
    public JsonResponse consultRequest() throws  Exception {
        String sn = SessionUtils.getCurrentUserInfo().getSn();

        WhitelistSetting whitelistSetting = whitelistSettingService.getBySn(sn);
        System.out.println(new ConsultRecord().setSId(sn).setPhoneNumber(whitelistSetting.getPhoneNumber()));
        consultRecordService.save(new ConsultRecord().setSId(sn).setPhoneNumber(whitelistSetting.getPhoneNumber()));
        return JsonResponse.success(null);
    }

    @PostMapping("/getRequest")
    public JsonResponse getRequest(@RequestBody PageDTO pageDTO) throws  Exception {
        Page<ConsultRecord> page = consultRecordService.getRequest(pageDTO);

        return JsonResponse.success(page);
    }

    @PostMapping("/reject")
    public JsonResponse reject(@RequestBody ConsultRecord consultRecord) throws  Exception {
        UpdateWrapper<ConsultRecord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", consultRecord.getId()).set("approved_status", "已拒绝");
        consultRecordService.update(consultRecord, updateWrapper);

        return JsonResponse.success(null);
    }

    @PostMapping("/queryPage")
    public JsonResponse queryPage(@RequestBody ConsultRecordPageDTO consultRecordPageDTO) throws  Exception {
        ConsultRecord consultRecord = consultRecordPageDTO.getConsultRecord();
        PageDTO pageDTO = consultRecordPageDTO.getPageDTO();

        Page<ConsultRecord> page = consultRecordService.queryPage(consultRecord, pageDTO);

        return JsonResponse.success(page);
    }

    @PostMapping("/updateById")
    public JsonResponse updateById(@RequestBody ConsultRecord consultRecord) throws  Exception {
        String sId = consultRecord.getSId();
        if (!whitelistSettingService.existUser(new WhitelistSetting().setSn(sId))) {
            return JsonResponse.failure("该学生不存在");
        };

        if (consultRecordService.locationConflict(consultRecord))
            return JsonResponse.failure("地点被占用");
        if (consultRecordService.teacherConflict(consultRecord))
            return JsonResponse.failure("咨询师忙碌");

        consultRecordService.updateById(consultRecord);
        return JsonResponse.success(null);
    }

    @PostMapping("/addRecord")
    public JsonResponse addRecord(@RequestBody ConsultRecord consultRecord) throws  Exception {
        String sId = consultRecord.getSId();
        if (!whitelistSettingService.existUser(new WhitelistSetting().setSn(sId))) {
            return JsonResponse.failure("该学生不存在");
        };

        if (consultRecordService.locationConflict(consultRecord))
            return JsonResponse.failure("地点被占用");
        if (consultRecordService.teacherConflict(consultRecord))
            return JsonResponse.failure("咨询师忙碌");

        consultRecord.setApprovedStatus("已批准");
        consultRecordService.save(consultRecord);
        return JsonResponse.success(null);
    }

    // 咨询员查看未添加咨询状态的记录
    @PostMapping("/consultantCheckRecord")
    public JsonResponse consultantCheckRecord(@RequestBody ConsultRecordSearchDTO consultRecordSearchDTO) throws  Exception {
        String user = SessionUtils.getCurrentUserInfo().getName();
        consultRecordSearchDTO.setConsultant(user);
        System.out.println(consultRecordSearchDTO);

        List<ConsultRecordDTO> list = consultRecordService.consultantCheckRecord(consultRecordSearchDTO);
        Integer total = consultRecordService.consultantCheckRecordTotal(consultRecordSearchDTO);
        return JsonResponse.success(new HashMap<Object, Object>() {{
            put("records", list);
            put("total", total);
        }});
    }

    // 咨询员选取已添加咨询状态的记录
    @PostMapping("/consultantQueryRecord")
    public JsonResponse consultantQueryRecord(@RequestBody ConsultRecordSearchDTO consultRecordSearchDTO) throws  Exception {
        String user = SessionUtils.getCurrentUserInfo().getName();
        consultRecordSearchDTO.setConsultant(user);
        System.out.println(consultRecordSearchDTO);

        List<ConsultRecordDTO> list = consultRecordService.consultantQueryRecord(consultRecordSearchDTO);
        Integer total = consultRecordService.consultantQueryRecordTotal(consultRecordSearchDTO);
        return JsonResponse.success(new HashMap<Object, Object>() {{
            put("records", list);
            put("total", total);
        }});
    }

    @PostMapping("/addStatus")
    public JsonResponse addStatus(@RequestBody ConsultRecord consultRecord) throws  Exception {
        consultRecordService.updateById(consultRecord);

        return JsonResponse.success(null);
    }

    @GetMapping("sendNotification")
    public JsonResponse sendNotification(NotificationDTO notificationDTO, ConsultRecord consultRecord) {
        consultRecordService.sendNotification(notificationDTO, consultRecord);
        return JsonResponse.success(null);
    }
}

