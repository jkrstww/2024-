package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.AddRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.AddRequestDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxp
 * @since 2024-07-02
 */
public interface AddRequestService extends IService<AddRequest> {

    boolean isRequested(AddRequestDTO addRequestDTO);

    boolean exitRecord(AddRequestDTO addRequestDTO);

    void addRequest(AddRequestDTO addRequestDTO);

    void approve(AddRequestDTO addRequestDTO);

    void reject(AddRequestDTO addRequestDTO);

    List<AddRequestDTO> getList(AddRequestDTO addRequestDTO);
}
