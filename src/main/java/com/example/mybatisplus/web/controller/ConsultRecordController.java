package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.ConsultRequest;
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
import java.util.List;


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

    /**
    * 描述：根据Id 查询
    *
    */
    @PostMapping(value = "/getById")
    public JsonResponse getById()throws Exception {
        String id = (String)request.getSession().getAttribute("sn");
        ConsultRecord  consultRecord =  consultRecordService.getById(id);
        return JsonResponse.success(consultRecord);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @PostMapping(value = "/deleteById")
    public JsonResponse deleteById() throws Exception {
        String id = (String)request.getSession().getAttribute("sn");
        consultRecordService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @PostMapping(value = "/updateConsultRecord")
    public JsonResponse updateConsultRecord(@RequestBody ConsultRecord  consultRecord) throws Exception {
        consultRecordService.updateById(consultRecord);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ConsultRecord
    *
    */
    @PostMapping(value = "/create")
    public JsonResponse create(@RequestBody ConsultRecord  consultRecord) throws Exception {
        consultRecordService.save(consultRecord);
        return JsonResponse.success(null);
    }

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

}

