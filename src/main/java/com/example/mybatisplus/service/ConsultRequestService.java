package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ConsultRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-06-24
 */
public interface ConsultRequestService extends IService<ConsultRequest> {
    List<ConsultRequest> getAllPendingRequests();

    void rejectRequest(String id);

    void completeRequest(String id);

    void acceptRequest(String id);

    void insertnewRequest(ConsultRequest consultRequest);
}
