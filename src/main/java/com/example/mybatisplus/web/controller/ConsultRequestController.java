package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultRequestService;
import com.example.mybatisplus.model.domain.ConsultRequest;

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
@RequestMapping("/api/consultRequest")
public class ConsultRequestController {
    @Autowired
    private HttpServletRequest request;

    private final Logger logger = LoggerFactory.getLogger( ConsultRequestController.class );

    @Autowired
    private ConsultRequestService consultRequestService;

    /**
    * 描述：根据Id 查询
    *
    */
    @PostMapping("/getById")
    public JsonResponse getById()throws Exception {
        String id = (String)request.getSession().getAttribute("sn");
        ConsultRequest  consultRequest =  consultRequestService.getById(id);
        return JsonResponse.success(consultRequest);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @PostMapping(value = "/deleteById")
    public JsonResponse deleteById() throws Exception {
        String id = (String)request.getSession().getAttribute("sn");
        consultRequestService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @PostMapping(value = "updateById")
    public JsonResponse updateConsultRequest(@RequestBody ConsultRequest  consultRequest) throws Exception {
        consultRequestService.updateById(consultRequest);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ConsultRequest
    *
    */
    @PostMapping(value = "create")
    public JsonResponse create(@RequestBody ConsultRequest  consultRequest) throws Exception {
        consultRequestService.save(consultRequest);
        return JsonResponse.success(null);
    }

    /**
     * 描述: 获取所有未处理的咨询申请
     */
    @PostMapping(value = "/getAllPendingRequests")
    public JsonResponse getAllPendingRequests() throws Exception{
        List<ConsultRequest> requests = consultRequestService.getAllPendingRequests();
        return JsonResponse.success(requests);
    }

    /**
     * 描述: 批准咨询申请
     */
    @PostMapping(value = "/acceptRequest")
    public JsonResponse acceptRequest() {
        String id = (String)request.getSession().getAttribute("sn");
        consultRequestService.acceptRequest(id);
        return JsonResponse.success("预约申请已被批准。");
    }

    /**
     * 描述: 拒绝咨询申请
     */
    @PostMapping(value = "/rejectRequest")
    public JsonResponse rejectRequest() {
        String id = (String)request.getSession().getAttribute("sn");
        consultRequestService.rejectRequest(id);
        return JsonResponse.success("预约申请已被拒绝。");
    }

    /**
     * 描述: 完成咨询申请
     */
    @PostMapping(value = "/completeRequest")
    public JsonResponse completeRequest() {
        String id = (String)request.getSession().getAttribute("sn");
        consultRequestService.completeRequest(id);
        return JsonResponse.success("已完成咨询");
    }

    /**
     * 新增预约
     */
    @PostMapping(value = "/insertnewRequest")
    public JsonResponse insertnewRequest(@RequestBody ConsultRequest consultRequest) throws Exception{
        consultRequestService.save(consultRequest);
        return JsonResponse.success("已完成新增");
    }

}

