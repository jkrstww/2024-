package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.dto.AddRequestDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AddRequestService;
import com.example.mybatisplus.model.domain.AddRequest;

import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2024-07-02
 * @version v1.0
 */
@RestController
@RequestMapping("/api/addRequest")
public class AddRequestController {
    @Autowired
    private AddRequestService addRequestService;

    @PostMapping("/addRequest")
    public JsonResponse addRequest(@RequestBody AddRequestDTO addRequestDTO) {
        if (addRequestService.isRequested(addRequestDTO)) {
            return JsonResponse.failure("已经咨询");
        } else {
            addRequestService.addRequest(addRequestDTO);
            return JsonResponse.success(null);
        }
    }

    // 批准
    @PostMapping("/approve")
    public JsonResponse approve(@RequestBody AddRequestDTO addRequestDTO) {
        addRequestService.approve(addRequestDTO);

        return JsonResponse.success(null);
    }

    // 拒绝
    @PostMapping("reject")
    public JsonResponse reject(@RequestBody AddRequestDTO addRequestDTO) {
        addRequestService.reject(addRequestDTO);

        return JsonResponse.success(null);
    }

    @PostMapping("getList")
    public JsonResponse getList(@RequestBody AddRequestDTO addRequestDTO) {
        String consultant = SessionUtils.getCurrentUserInfo().getName();
        addRequestDTO.setConsultant(consultant);
        List<AddRequestDTO> list = addRequestService.getList(addRequestDTO);

        return JsonResponse.success(list);
    }
}

